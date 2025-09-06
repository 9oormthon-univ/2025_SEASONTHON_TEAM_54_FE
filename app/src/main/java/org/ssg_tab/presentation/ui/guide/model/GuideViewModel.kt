package org.ssg_tab.presentation.ui.guide.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ssg_tab.domain.repository.study.StudyRepository
import org.ssg_tab.presentation.ui.guide.state.GuideContract
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val studyRepository: StudyRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GuideContract())
    val state: StateFlow<GuideContract> = _state.asStateFlow()

    init {
        fetchLearningData()
    }

    fun fetchLearningData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            studyRepository.getLearning()
                .onSuccess { studyData ->
                    _state.value = _state.value.copy(
                        studyData = studyData,
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { exception ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = exception.message ?: "학습 데이터를 불러오는데 실패했습니다."
                    )
                }
        }
    }

    fun retry() {
        fetchLearningData()
    }
}