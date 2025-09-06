package org.ssg_tab.presentation.ui.guide.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ssg_tab.domain.model.entity.quiz.QuizEntity
import org.ssg_tab.domain.repository.quiz.QuizRepository
import org.ssg_tab.presentation.ui.guide.state.QuizContract
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
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
                Log.d(TAG, "Calling repository.getQuizList(categoryId=1, difficulty=Easy)")
                quizRepository.getQuizList(categoryId = 1, difficulty = "EASY")
                    .onSuccess { quizList ->
                        Log.d(TAG, "Quiz loading SUCCESS - received ${quizList.size} quizzes")
                        quizList.forEachIndexed { index, quiz ->
                            Log.d(TAG, "Quiz $index: id=${quiz.id}, question=${quiz.question.take(50)}...")
                        }
                        _state.value = _state.value.copy(
                            quizList = quizList,
                            isLoading = false
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
        val currentQuiz = _state.value.currentQuiz
        Log.d(TAG, "Current quiz: ${currentQuiz?.question?.take(30)}...")

        _state.value = _state.value.copy(selectedAnswer = answerIndex)
        Log.d(TAG, "Answer selected - answerIndex=$answerIndex")

        // 정답 여부 로깅
        currentQuiz?.let { quiz ->
            val isCorrect = answerIndex == quiz.correctAnswerIndex
            Log.d(TAG, "Answer is ${if (isCorrect) "CORRECT" else "INCORRECT"} - correctIndex=${quiz.correctAnswerIndex}")
        }
    }

    fun nextQuestion() {
        Log.d(TAG, "nextQuestion() called")
        val currentState = _state.value
        Log.d(TAG, "Current state - currentQuizIndex=${currentState.currentQuizIndex}, totalQuestions=${currentState.totalQuestions}")

        if (currentState.currentQuizIndex < currentState.totalQuestions - 1) {
            val newIndex = currentState.currentQuizIndex + 1
            _state.value = currentState.copy(
                currentQuizIndex = newIndex,
                selectedAnswer = -1
            )
            Log.d(TAG, "Moved to next question - newIndex=$newIndex")

            val nextQuiz = _state.value.currentQuiz
            Log.d(TAG, "Next quiz: ${nextQuiz?.question?.take(30)}...")
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
            _state.value = currentState.copy(
                currentQuizIndex = newIndex,
                selectedAnswer = -1
            )
            Log.d(TAG, "Moved to previous question - newIndex=$newIndex")

            val prevQuiz = _state.value.currentQuiz
            Log.d(TAG, "Previous quiz: ${prevQuiz?.question?.take(30)}...")
        } else {
            Log.d(TAG, "Cannot move to previous question - already at first question")
        }
    }

    fun resetQuiz() {
        Log.d(TAG, "resetQuiz() called")
        _state.value = _state.value.copy(
            currentQuizIndex = 0,
            selectedAnswer = -1
        )
        Log.d(TAG, "Quiz reset - currentQuizIndex=0, selectedAnswer=-1")
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