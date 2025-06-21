package com.example.cocktailapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.emoji2.text.EmojiCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cocktailapi.navigation.Screen
import com.example.cocktailapi.ui.DetailScreen // <- Import du nouveau fichier
import com.example.cocktailapi.ui.HomeScreen    // <- Import du nouveau fichier
import com.example.cocktailapi.ui.SearchScreen
import com.example.cocktailapi.ui.theme.CocktailApiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EmojiCompat.init(this)
        setContent {
            CocktailApiTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(route = Screen.Home.route) {
                        val mainViewModel: MainViewModel = viewModel()
                        val state by mainViewModel.uiState.collectAsState()

                        HomeScreen(
                            state = state,
                            onCocktailClick = { cocktailId ->
                                navController.navigate(Screen.Detail.createRoute(cocktailId))
                            },
                            onSearchClick = {
                                navController.navigate(Screen.Search.route)
                            }
                        )
                    }

                    composable(
                        route = Screen.Detail.route,
                        arguments = listOf(navArgument("cocktailId") { type = NavType.StringType })
                    ) {
                        val detailViewModel: DetailViewModel = viewModel()
                        val state by detailViewModel.uiState.collectAsState()

                        DetailScreen(
                            state = state,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                    composable(route = Screen.Search.route) {
                        SearchScreen(
                            onNavigateBack = { navController.popBackStack() },
                            onResultClick = { cocktailId ->
                                navController.navigate(Screen.Detail.createRoute(cocktailId))
                            }
                        )
                    }
                }
            }
        }
    }
}