package org.ssg_tab.presentation.ui.userinformation.model


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
    val onboardingComplete: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState = _uiState.asStateFlow()

    // 1단계: 관심사 선택
    fun selectInterest(interest: String) {
        _uiState.update { currentState ->
            val newSelection = currentState.selectedInterests.toMutableSet()
            if (newSelection.contains(interest)) newSelection.remove(interest)
            else newSelection.add(interest)
            currentState.copy(selectedInterests = newSelection)
        }
    }

    // 2단계: 연령대 및 지역 선택
    fun selectAgeGroup(ageGroup: String) {
        _uiState.update { it.copy(selectedAgeGroup = ageGroup) }
    }
    fun selectRegion(region: String) {
        _uiState.update { it.copy(selectedRegion = region) }
    }

    // 3단계: 직업 선택
    fun selectJob(job: String) {
        _uiState.update { it.copy(selectedJob = job) }
    }

    fun submitOnboardingData() {
        val currentState = _uiState.value

        // TODO: 카테고리 가져오기 API
        val categoryIds = mapInterestsToIds(currentState.selectedInterests)

        val ageBand = mapAgeGroupToEnum(currentState.selectedAgeGroup)

        val onboardingInfo = OnboardingEntity(
            ageBand = ageBand ?: return,
            region = currentState.selectedRegion ?: return,
            job = currentState.selectedJob ?: return,
            categoryIds = categoryIds
        )

        viewModelScope.launch {
            onboardingRepository.submitOnboardingInfo(onboardingInfo)
                .onSuccess {
                    _uiState.update { it.copy(onboardingComplete = true) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(error = error.message) }
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
        val map = mapOf("주식" to 1L, "취업" to 2L, "부동산" to 3L, "청약" to 4L, "암호화폐" to 5L)
        return interests.mapNotNull { map[it] }
    }
}