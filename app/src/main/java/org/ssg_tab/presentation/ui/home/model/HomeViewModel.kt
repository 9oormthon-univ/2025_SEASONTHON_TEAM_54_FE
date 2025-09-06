package org.ssg_tab.presentation.ui.home.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ssg_tab.data.dto.request.home.HomeLikeRequestDto
import org.ssg_tab.domain.repository.home.HomeFeedRepository
import org.ssg_tab.domain.repository.home.HomeLikeRepository
import org.ssg_tab.presentation.ui.home.state.HomeContract
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeFeedRepository: HomeFeedRepository,
    private val homeLikeRepository: HomeLikeRepository,
) : ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _state = MutableStateFlow(HomeContract())
    val state: StateFlow<HomeContract> = _state.asStateFlow()

    init {
        Log.d(TAG, "HomeViewModel initialized")
        loadHomeFeed()
    }

    fun loadHomeFeed() {
        Log.d(TAG, "loadHomeFeed() called")
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            homeFeedRepository.getHomeFeed(
                page = 0,
                size = 5,
                categoryId = 1
            ).onSuccess { homeFeed ->

                _state.value = _state.value.copy(
                    homeFeed = homeFeed,
                    isLoading = false,
                    error = null
                )
            }.onFailure { exception ->
                Log.e(TAG, "Home feed loading failed", exception)

                if (exception.message?.contains("500") == true ||
                    exception.message?.contains("not found") == true
                ) {
                    Log.w(TAG, "Server error, using mock data")

                } else {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = exception.message ?: "홈 피드를 불러오는데 실패했습니다."
                    )
                }
            }
        }
    }



    fun toggleLike(contentId: Long) {
        Log.d(TAG, "toggleLike() called with contentId: $contentId")

        val currentState = _state.value
        val isCurrentlyLiked = currentState.likedContentIds.contains(contentId)
        val isCurrentlyProcessing = currentState.isLikingContent.contains(contentId)

        if (isCurrentlyProcessing) {
            Log.d(TAG, "Already processing like for contentId: $contentId")
            return
        }

        viewModelScope.launch {
            val updatedLikedIds = if (isCurrentlyLiked) {
                currentState.likedContentIds - contentId
            } else {
                currentState.likedContentIds + contentId
            }

            val updatedProcessingIds = currentState.isLikingContent + contentId

            _state.value = currentState.copy(
                likedContentIds = updatedLikedIds,
                isLikingContent = updatedProcessingIds
            )

            Log.d(
                TAG,
                "UI updated optimistically - contentId: $contentId, liked: ${!isCurrentlyLiked}"
            )

            try {
                val request = HomeLikeRequestDto(contentsId = contentId)

                homeLikeRepository.postHomeLike(homeLikeRequestDto = request)
                    .onSuccess {
                        Log.d(TAG, "Like API call successful for contentId: $contentId")

                        _state.value = _state.value.copy(
                            isLikingContent = _state.value.isLikingContent - contentId
                        )
                    }
                    .onFailure { exception ->
                        Log.e(TAG, "Like API call failed for contentId: $contentId", exception)

                        _state.value = _state.value.copy(
                            likedContentIds = if (isCurrentlyLiked) {
                                _state.value.likedContentIds + contentId
                            } else {
                                _state.value.likedContentIds - contentId
                            },
                            isLikingContent = _state.value.isLikingContent - contentId,
                            error = "좋아요 처리 중 오류가 발생했습니다."
                        )
                    }
            } catch (e: Exception) {
                Log.e(TAG, "Unexpected error in toggleLike", e)

                _state.value = _state.value.copy(
                    likedContentIds = if (isCurrentlyLiked) {
                        _state.value.likedContentIds + contentId
                    } else {
                        _state.value.likedContentIds - contentId
                    },
                    isLikingContent = _state.value.isLikingContent - contentId,
                    error = "예상치 못한 오류가 발생했습니다."
                )
            }
        }
    }

    fun isContentLiked(contentId: Long): Boolean {
        return _state.value.likedContentIds.contains(contentId)
    }

    fun isContentBeingLiked(contentId: Long): Boolean {
        return _state.value.isLikingContent.contains(contentId)
    }

    fun clearError() {
        Log.d(TAG, "clearError() called")
        _state.value = _state.value.copy(error = null)
    }

    fun retry() {
        Log.d(TAG, "retry() called")
        loadHomeFeed()
    }

    fun refreshFeed() {
        Log.d(TAG, "refreshFeed() called")
        _state.value = _state.value.copy(
            likedContentIds = emptySet(),
            isLikingContent = emptySet()
        )
        loadHomeFeed()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "HomeViewModel onCleared() called")
    }
}