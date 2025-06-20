package com.example.cocktailapi.ui

import com.example.cocktailapi.model.CocktailBean

data class MainScreenState(
    val isLoading: Boolean = false,
    val cocktail: CocktailBean? = null,
    val error: String? = null
)