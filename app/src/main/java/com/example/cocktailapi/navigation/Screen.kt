package com.example.cocktailapi.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search

sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: androidx.compose.ui.graphics.vector.ImageVector? = null
) {
    // --- Onglets de la barre de navigation ---
    data object Home : Screen("home_screen", "Accueil", Icons.Default.Home)
    data object Search : Screen("search_screen", "Recherche", Icons.Default.Search)
    data object Browse : Screen("browse_screen", "Parcourir", Icons.Default.Menu)

    // --- Écrans accessibles depuis la navigation ---
    data object Detail : Screen("detail_screen/{cocktailId}") {
        fun createRoute(cocktailId: String) = "detail_screen/$cocktailId"
    }

    data object CategoryList : Screen("category_list_screen", "Catégories")
    data object CocktailList :
        Screen("cocktail_list_screen/{filterType}/{filterValue}", "Résultats") {
        fun createRoute(filterType: String, filterValue: String) =
            "cocktail_list_screen/$filterType/$filterValue"
    }
}