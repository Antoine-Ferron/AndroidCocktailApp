package com.example.cocktailapi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapi.model.CocktailRepository
import com.example.cocktailapi.model.SmallCocktailBean
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CocktailListState(
    val isLoading: Boolean = false,
    val cocktails: List<SmallCocktailBean> = emptyList(),
    val error: String? = null,
    val title: String = ""
)

class CocktailListViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CocktailListState())
    val uiState = _uiState.asStateFlow()

    private val filterType: String = checkNotNull(savedStateHandle["filterType"])
    private val filterValue: String = checkNotNull(savedStateHandle["filterValue"])

    init {
        _uiState.update { it.copy(title = filterValue) }
        fetchCocktails()
    }

    private fun fetchCocktails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val result = when (filterType) {
                    "category" -> CocktailRepository.getCocktailByCategory(filterValue)
                    else -> null
                }
                _uiState.update {
                    it.copy(isLoading = false, cocktails = result?.drinks ?: emptyList())
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}