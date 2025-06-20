package com.example.cocktailapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapi.model.CocktailRepository
import com.example.cocktailapi.ui.MainScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchRandomCocktail()
    }

    private fun fetchRandomCocktail() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val cocktailBean = CocktailRepository.getRandomCocktail()?.drinks?.firstOrNull()

                _uiState.update {
                    it.copy(isLoading = false, cocktail = cocktailBean, error = null)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = "Erreur: ${e.message}")
                }
            }
        }
    }
}