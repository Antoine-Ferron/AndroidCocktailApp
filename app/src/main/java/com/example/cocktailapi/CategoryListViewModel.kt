package com.example.cocktailapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapi.model.CategoryItemBean
import com.example.cocktailapi.model.ListItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<CategoryItemBean> = emptyList(),
    val error: String? = null
)

class CategoryListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CategoryListState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val categories = ListItemRepository.getListCategory()?.drinks ?: emptyList()
                _uiState.update { it.copy(isLoading = false, categories = categories) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}