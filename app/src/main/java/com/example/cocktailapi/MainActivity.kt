package com.example.cocktailapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cocktailapi.navigation.Screen
import com.example.cocktailapi.ui.CategoryListScreen
import com.example.cocktailapi.ui.CocktailListScreen
import com.example.cocktailapi.ui.DetailScreen
import com.example.cocktailapi.ui.HomeScreen
import com.example.cocktailapi.ui.SearchScreen
import com.example.cocktailapi.ui.theme.CocktailApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailApiTheme {
                val navController = rememberNavController()

                val bottomNavItems = listOf(Screen.Home, Screen.Search, Screen.Browse)

                Scaffold(
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route
                        if (currentRoute in bottomNavItems.map { it.route }) {
                            NavigationBar {
                                bottomNavItems.forEach { screen ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                screen.icon!!,
                                                contentDescription = stringResource(id = screen.titleResId!!)
                                            )
                                        },
                                        label = { Text(stringResource(id = screen.titleResId!!)) },
                                        selected = currentRoute == screen.route,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    // NavHost
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Onglet 1: Accueil
                        composable(Screen.Home.route) {
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

                        // Onglet 2: Recherche
                        composable(Screen.Search.route) {
                            val viewModel: SearchViewModel = viewModel()
                            val state by viewModel.uiState.collectAsState()
                            SearchScreen(
                                state = state,
                                onQueryChange = viewModel::onQueryChange,
                                onNavigateBack = { navController.popBackStack() },
                                onResultClick = { cocktailId ->
                                    navController.navigate(Screen.Detail.createRoute(cocktailId))
                                }
                            )
                        }

                        // Onglet 3: Parcourir
                        composable(Screen.Browse.route) {
                            val viewModel: CategoryListViewModel = viewModel()
                            val state by viewModel.uiState.collectAsState()
                            CategoryListScreen(
                                state = state,
                                onCategoryClick = { categoryName ->
                                    navController.navigate(
                                        Screen.CocktailList.createRoute(
                                            filterType = "category",
                                            filterValue = categoryName
                                        )
                                    )
                                }
                            )
                        }

                        // Écran de Détail
                        composable(
                            route = Screen.Detail.route,
                            arguments = listOf(navArgument("cocktailId") {
                                type = NavType.StringType
                            })
                        ) {
                            val detailViewModel: DetailViewModel = viewModel()
                            val state by detailViewModel.uiState.collectAsState()
                            DetailScreen(
                                state = state,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }

                        // Écran de liste de cocktails par catégorie ou ingrédient
                        composable(
                            route = Screen.CocktailList.route,
                            arguments = listOf(
                                navArgument("filterType") { type = NavType.StringType },
                                navArgument("filterValue") { type = NavType.StringType }
                            )
                        ) {
                            val viewModel: CocktailListViewModel = viewModel()
                            val state by viewModel.uiState.collectAsState()
                            CocktailListScreen(
                                state = state,
                                onCocktailClick = { cocktailId ->
                                    navController.navigate(Screen.Detail.createRoute(cocktailId))
                                },
                                onNavigateBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}