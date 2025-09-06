package org.ssg_tab.presentation.ui.home.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ssg_tab.domain.repository.home.HomeFeedRepository
import org.ssg_tab.presentation.ui.home.state.HomeContract
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeFeedRepository: HomeFeedRepository,
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
                Log.d(TAG, "Home feed loaded successfully")
                _state.value = _state.value.copy(
                    homeFeed = homeFeed,
                    isLoading = false,
                    error = null
                )
            }.onFailure { exception ->
                Log.e(TAG, "Home feed loading failed", exception)
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message ?: "홈 피드를 불러오는데 실패했습니다."
                )
            }
        }
    }

    fun retry() {
        Log.d(TAG, "retry() called")
        loadHomeFeed()
    }
}