package org.ssg_tab.presentation.ui.guide.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ssg_tab.domain.model.entity.quiz.QuizEntity
import org.ssg_tab.domain.repository.quiz.QuizCompleteRepository
import org.ssg_tab.domain.repository.quiz.QuizRepository
import org.ssg_tab.presentation.ui.guide.state.QuizContract
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val quizCompleteRepository: QuizCompleteRepository
) : ViewModel() {

    companion object {
        private const val TAG = "QuizViewModel"
    }

    private val _state = MutableStateFlow(QuizContract())
    val state: StateFlow<QuizContract> = _state.asStateFlow()

    init {
        Log.d(TAG, "QuizViewModel initialized")
        loadQuizzes()
    }

    fun loadQuizzes() {
        Log.d(TAG, "loadQuizzes() called")
        viewModelScope.launch {
            Log.d(TAG, "Starting quiz loading - setting loading state to true")
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {
                Log.d(TAG, "Calling repository.getQuizList(categoryId=1, difficulty=EASY)")
                quizRepository.getQuizList(categoryId = 1, difficulty = "EASY")
                    .onSuccess { quizList ->
                        Log.d(TAG, "Quiz loading SUCCESS - received ${quizList.size} quizzes")
                        quizList.forEachIndexed { index, quiz ->
                            Log.d(TAG, "Quiz $index: id=${quiz.id}, question=${quiz.question.take(50)}...")
                        }
                        _state.value = _state.value.copy(
                            quizList = quizList,
                            isLoading = false,
                            userAnswers = emptyMap(),
                            answeredQuestions = emptySet(),
                            currentQuestionResult = null
                        )
                        Log.d(TAG, "State updated - isLoading=false, quizList.size=${quizList.size}")
                    }
                    .onFailure { exception ->
                        Log.e(TAG, "Quiz loading FAILED", exception)
                        val errorMessage = exception.message ?: "퀴즈를 불러오는데 실패했습니다."
                        Log.e(TAG, "Error message: $errorMessage")
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = errorMessage
                        )
                        Log.d(TAG, "State updated - isLoading=false, error=$errorMessage")
                    }
            } catch (e: Exception) {
                Log.e(TAG, "Unexpected exception in loadQuizzes()", e)
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "예상치 못한 오류가 발생했습니다: ${e.message}"
                )
            }
        }
    }

    fun selectAnswer(answerIndex: Int) {
        Log.d(TAG, "selectAnswer() called with answerIndex=$answerIndex")
        val currentState = _state.value
        val currentQuiz = currentState.currentQuiz

        if (currentState.isCurrentQuestionAnswered) {
            Log.d(TAG, "Question already answered, ignoring selection")
            return
        }

        Log.d(TAG, "Current quiz: ${currentQuiz?.question?.take(30)}...")

        _state.value = currentState.copy(selectedAnswer = answerIndex)

        submitCurrentAnswer(answerIndex)
    }

    private fun submitCurrentAnswer(answerIndex: Int) {
        Log.d(TAG, "submitCurrentAnswer() called with answerIndex=$answerIndex")
        viewModelScope.launch {
            val currentState = _state.value
            val currentQuiz = currentState.currentQuiz ?: return@launch
            val questionIndex = currentState.currentQuizIndex

            _state.value = currentState.copy(isSubmittingAnswer = true)

            try {

                val isCorrect = answerIndex == currentQuiz.correctAnswerIndex
                Log.d(TAG, "Answer is ${if (isCorrect) "CORRECT" else "INCORRECT"} - correctIndex=${currentQuiz.correctAnswerIndex}")

                // API 호출 (실제 구현 시 수정 필요)
                /*
                val answerSubmitRequest = QuizAnswerSubmitDto(
                    quizId = currentQuiz.id,
                    selectedAnswer = answerIndex,
                    questionIndex = questionIndex
                )

                quizCompleteRepository.submitAnswer(answerSubmitRequest)
                    .onSuccess { response ->
                        Log.d(TAG, "Answer submission successful: ${response.isCorrect}")
                    }
                    .onFailure { exception ->
                        Log.e(TAG, "Answer submission failed", exception)
                    }
                */

                delay(500)

                val updatedAnswers = currentState.userAnswers.toMutableMap()
                updatedAnswers[questionIndex] = answerIndex

                val updatedAnsweredQuestions = currentState.answeredQuestions.toMutableSet()
                updatedAnsweredQuestions.add(questionIndex)

                _state.value = _state.value.copy(
                    userAnswers = updatedAnswers,
                    answeredQuestions = updatedAnsweredQuestions,
                    currentQuestionResult = isCorrect,
                    isSubmittingAnswer = false
                )

                Log.d(TAG, "Answer submitted and result shown - questionIndex=$questionIndex, isCorrect=$isCorrect")

                if (questionIndex < currentState.totalQuestions - 1) {
                    delay(2000)
                    autoMoveToNextQuestion()
                } else {
                    delay(3000)
                    completeQuiz()
                }

            } catch (e: Exception) {
                Log.e(TAG, "Error submitting answer", e)
                _state.value = _state.value.copy(
                    isSubmittingAnswer = false,
                    error = "답안 제출 중 오류가 발생했습니다."
                )
            }
        }
    }

    private fun autoMoveToNextQuestion() {
        Log.d(TAG, "autoMoveToNextQuestion() called")
        val currentState = _state.value
        if (currentState.currentQuizIndex < currentState.totalQuestions - 1) {
            val newIndex = currentState.currentQuizIndex + 1
            _state.value = currentState.copy(
                currentQuizIndex = newIndex,
                selectedAnswer = -1,
                currentQuestionResult = null
            )
            Log.d(TAG, "Auto moved to next question - newIndex=$newIndex")
        }
    }

    fun nextQuestion() {
        Log.d(TAG, "nextQuestion() called")
        val currentState = _state.value

        if (!currentState.isCurrentQuestionAnswered) {
            Log.d(TAG, "Cannot move to next question - current question not answered")
            return
        }

        Log.d(TAG, "Current state - currentQuizIndex=${currentState.currentQuizIndex}, totalQuestions=${currentState.totalQuestions}")

        if (currentState.currentQuizIndex < currentState.totalQuestions - 1) {
            val newIndex = currentState.currentQuizIndex + 1
            val previousAnswer = currentState.userAnswers[newIndex] ?: -1

            _state.value = currentState.copy(
                currentQuizIndex = newIndex,
                selectedAnswer = previousAnswer,
                currentQuestionResult = if (currentState.answeredQuestions.contains(newIndex)) {
                    val quiz = currentState.quizList.getOrNull(newIndex)
                    quiz?.let { previousAnswer == it.correctAnswerIndex }
                } else null
            )
            Log.d(TAG, "Moved to next question - newIndex=$newIndex")
        } else {
            Log.d(TAG, "Cannot move to next question - already at last question")
        }
    }

    fun previousQuestion() {
        Log.d(TAG, "previousQuestion() called")
        val currentState = _state.value
        Log.d(TAG, "Current state - currentQuizIndex=${currentState.currentQuizIndex}")

        if (currentState.currentQuizIndex > 0) {
            val newIndex = currentState.currentQuizIndex - 1
            val previousAnswer = currentState.userAnswers[newIndex] ?: -1

            _state.value = currentState.copy(
                currentQuizIndex = newIndex,
                selectedAnswer = previousAnswer,
                currentQuestionResult = if (currentState.answeredQuestions.contains(newIndex)) {
                    val quiz = currentState.quizList.getOrNull(newIndex)
                    quiz?.let { previousAnswer == it.correctAnswerIndex }
                } else null
            )
            Log.d(TAG, "Moved to previous question - newIndex=$newIndex")
        } else {
            Log.d(TAG, "Cannot move to previous question - already at first question")
        }
    }

    private fun completeQuiz() {
        Log.d(TAG, "completeQuiz() called")
        viewModelScope.launch {
            val currentState = _state.value
            val correctAnswers = currentState.correctAnswers
            val totalQuestions = currentState.totalQuestions
            val score = (correctAnswers.toFloat() / totalQuestions * 100).toInt()

            Log.d(TAG, "Quiz completed - correctAnswers=$correctAnswers out of $totalQuestions (${score}%)")

            try {
                delay(1000)
                Log.d(TAG, "Quiz completion API call simulated")

                Log.d(TAG, "Quiz fully completed!")

            } catch (e: Exception) {
                Log.e(TAG, "Error completing quiz", e)
            }
        }
    }

    fun resetQuiz() {
        Log.d(TAG, "resetQuiz() called")
        _state.value = _state.value.copy(
            currentQuizIndex = 0,
            selectedAnswer = -1,
            userAnswers = emptyMap(),
            answeredQuestions = emptySet(),
            currentQuestionResult = null
        )
        Log.d(TAG, "Quiz reset - all states cleared")
    }

    fun getCurrentQuiz(): QuizEntity? {
        val quiz = _state.value.currentQuiz
        Log.v(TAG, "getCurrentQuiz() - returning quiz: ${quiz?.question?.take(30)}...")
        return quiz
    }

    fun isLastQuestion(): Boolean {
        val result = _state.value.currentQuizIndex >= _state.value.totalQuestions - 1
        Log.v(TAG, "isLastQuestion() - $result")
        return result
    }

    fun isFirstQuestion(): Boolean {
        val result = _state.value.currentQuizIndex <= 0
        Log.v(TAG, "isFirstQuestion() - $result")
        return result
    }

    fun hasQuizzes(): Boolean {
        val result = _state.value.totalQuestions > 0
        Log.v(TAG, "hasQuizzes() - $result (totalQuestions=${_state.value.totalQuestions})")
        return result
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "QuizViewModel onCleared() called")
    }
}