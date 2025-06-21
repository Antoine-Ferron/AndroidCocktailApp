package com.example.cocktailapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapi.model.CocktailRepository
import com.example.cocktailapi.model.SmallCocktailBean
import com.example.cocktailapi.ui.SearchScreenState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenState())
    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(500)
            performSearch(newQuery)
        }
    }

    private fun performSearch(query: String) {
        if (query.isBlank()) {
            _uiState.update { it.copy(results = emptyList(), noResultsFound = false) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, noResultsFound = false) }
            try {
                val searchResults = CocktailRepository.getCocktailByName(query)?.drinks ?: emptyList()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        results = searchResults.map { cocktail ->
                            SmallCocktailBean(cocktail.strDrink, cocktail.strDrinkThumb, cocktail.idDrink)
                        },
                        noResultsFound = searchResults.isEmpty()
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Erreur: ${e.message}") }
            }
        }
    }
}