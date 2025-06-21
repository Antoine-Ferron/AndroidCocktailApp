package com.example.cocktailapi.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cocktailapi.R

sealed class Screen(
    val route: String, @StringRes val titleResId: Int? = null, val icon: ImageVector? = null
) {
    // --- Onglets de la barre de navigation ---
    data object Home : Screen("home_screen", R.string.home_title, Icons.Default.Home)
    data object Search : Screen("search_screen", R.string.search_title, Icons.Default.Search)
    data object Browse : Screen("browse_screen", R.string.browse_title, Icons.Default.Menu)

    // --- Écrans accessibles depuis la navigation ---
    data object Detail : Screen("detail_screen/{cocktailId}", R.string.details_title) {
        fun createRoute(cocktailId: String) = "detail_screen/$cocktailId"
    }

    data object CategoryList : Screen("category_list_screen", R.string.categories_title)

    data object CocktailList :
        Screen("cocktail_list_screen/{filterType}/{filterValue}", R.string.results_title) {
        fun createRoute(filterType: String, filterValue: String) =
            "cocktail_list_screen/$filterType/$filterValue"
    }
}