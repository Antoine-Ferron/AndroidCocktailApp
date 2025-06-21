package com.example.cocktailapi.ui

import com.example.cocktailapi.model.SmallCocktailBean

data class SearchScreenState(
    val query: String = "",
    val isLoading: Boolean = false,
    val results: List<SmallCocktailBean> = emptyList(),
    val error: String? = null,
    val noResultsFound: Boolean = false
)