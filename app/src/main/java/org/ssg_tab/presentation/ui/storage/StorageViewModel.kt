package org.ssg_tab.presentation.ui.storage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.ssg_tab.domain.repository.storage.StorageRepository
import org.ssg_tab.presentation.ui.storage.state.StorageContract
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val storageRepository: StorageRepository
) : ViewModel() {

    private val _state = mutableStateOf(StorageContract())
    val state: State<StorageContract> = _state

    init {
        fetchStorage()
    }

    fun fetchStorage(categoryId: Long = 1) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            storageRepository.getStorage(categoryId)
                .onSuccess { storageData ->
                    _state.value = _state.value.copy(
                        storageFeed = storageData,
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { exception ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = exception.message ?: "데이터를 불러오는데 실패했습니다."
                    )
                }
        }
    }

    fun updateSelectedCategory(category: String) {
        _state.value = _state.value.copy(selectedCategory = category)
        val categoryId = getCategoryId(category)
        fetchStorage(categoryId)
    }

    fun updateSelectedSortOption(sortOption: String) {
        _state.value = _state.value.copy(selectedSortOption = sortOption)
    }

    private fun getCategoryId(category: String): Long {
        return when (category) {
            "전체" -> 1
            "주식" -> 2
            "취업" -> 3
            "청약" -> 4
            "부동산" -> 5
            "암호화폐" -> 6
            "해외투자" -> 7
            else -> 1
        }
    }
}