package org.ssg_tab.presentation.ui.mypage.model

import org.ssg_tab.domain.model.entity.UserInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ssg_tab.domain.repository.UserRepository
import javax.inject.Inject

data class MyPageUiState(
    val isLoading: Boolean = true,
    val userInfo: UserInfo? = null,
    val error: String? = null
)

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchUserInfo()
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            userRepository.getUserInfo()
                .onSuccess { userInfo ->
                    _uiState.update { it.copy(isLoading = false, userInfo = userInfo) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }
}