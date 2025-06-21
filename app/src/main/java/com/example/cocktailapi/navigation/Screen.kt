package com.example.cocktailapi.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home_screen")

    data object Detail : Screen("detail_screen/{cocktailId}") {

        fun createRoute(cocktailId: String) = "detail_screen/$cocktailId"
    }
    data object Search : Screen("search_screen")

}