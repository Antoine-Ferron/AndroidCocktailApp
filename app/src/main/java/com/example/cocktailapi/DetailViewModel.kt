package com.example.cocktailapi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapi.model.CocktailRepository
import com.example.cocktailapi.ui.MainScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    private val cocktailId: String = checkNotNull(savedStateHandle["cocktailId"])

    init {
        fetchCocktailDetails()
    }

    private fun fetchCocktailDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val cocktail = CocktailRepository.getCocktailById(cocktailId)?.drinks?.first()
                _uiState.update { it.copy(isLoading = false, cocktail = cocktail) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Erreur: ${e.message}") }
            }
        }
    }
}