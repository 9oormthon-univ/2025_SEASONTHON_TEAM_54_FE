package org.ssg_tab.presentation.ui.userinformation.model


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ssg_tab.domain.model.entity.OnboardingEntity
import org.ssg_tab.domain.repository.onboarding.OnboardingRepository
import javax.inject.Inject

data class OnboardingUiState(
    val selectedInterests: Set<String> = emptySet(),
    val selectedAgeGroup: String? = null,
    val selectedRegion: String? = null,
    val selectedJob: String? = null,
    val isLoading: Boolean = false,
    val onboardingComplete: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    companion object {
        private const val TAG = "OnboardingViewModel"
    }

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState = _uiState.asStateFlow()

    fun selectInterest(interest: String) {
        _uiState.update { currentState ->
            val newSelection = currentState.selectedInterests.toMutableSet()
            if (newSelection.contains(interest)) newSelection.remove(interest)
            else newSelection.add(interest)
            currentState.copy(selectedInterests = newSelection)
        }
    }


    fun selectAgeGroup(ageGroup: String) {
        _uiState.update { it.copy(selectedAgeGroup = ageGroup) }
    }

    fun selectRegion(region: String) {
        _uiState.update { it.copy(selectedRegion = region) }
    }

    fun selectJob(job: String) {
        _uiState.update { it.copy(selectedJob = job) }
    }

    fun submitOnboardingData() {
        val currentState = _uiState.value
        Log.d(TAG, "submitOnboardingData() called")
        Log.d(TAG, "Current state: $currentState")

        // 입력값 검증
        if (currentState.selectedAgeGroup == null) {
            _uiState.update { it.copy(error = "연령대를 선택해주세요.") }
            return
        }

        if (currentState.selectedRegion == null) {
            _uiState.update { it.copy(error = "지역을 선택해주세요.") }
            return
        }

        if (currentState.selectedJob == null) {
            _uiState.update { it.copy(error = "직업을 선택해주세요.") }
            return
        }

        val categoryIds = mapInterestsToIds(currentState.selectedInterests)
        val ageBand = mapAgeGroupToEnum(currentState.selectedAgeGroup)

        if (ageBand == null) {
            _uiState.update { it.copy(error = "올바른 연령대를 선택해주세요.") }
            return
        }

        val onboardingInfo = OnboardingEntity(
            ageBand = ageBand,
            region = currentState.selectedRegion!!,
            job = currentState.selectedJob!!,
            categoryIds = categoryIds
        )

        Log.d(TAG, "Submitting onboarding data: $onboardingInfo")

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            onboardingRepository.submitOnboardingInfo(onboardingInfo)
                .onSuccess {
                    Log.d(TAG, "Onboarding submission SUCCESS")
                    _uiState.update { it.copy(
                        isLoading = false,
                        onboardingComplete = true,
                        error = null
                    )}
                }
                .onFailure { error ->
                    Log.e(TAG, "Onboarding submission FAILED: ${error.message}")
                    _uiState.update { it.copy(
                        isLoading = false,
                        error = error.message ?: "온보딩 정보 저장에 실패했습니다."
                    )}
                }
        }
    }

    private fun mapAgeGroupToEnum(ageGroup: String?): String? {
        return when (ageGroup) {
            "20-24세" -> "AGE_20_EARLY"
            "25-29세" -> "AGE_20_LATE"
            "30-34세" -> "AGE_30_EARLY"
            "35-41세" -> "AGE_30_LATE"
            "기타" -> "ETC"
            else -> null
        }
    }

    private fun mapInterestsToIds(interests: Set<String>): List<Long> {
        val map = mapOf(
            "주식" to 1L,
            "취업" to 2L,
            "부동산" to 3L,
            "청약" to 4L,
            "암호화폐" to 5L
        )
        return interests.mapNotNull { map[it] }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}