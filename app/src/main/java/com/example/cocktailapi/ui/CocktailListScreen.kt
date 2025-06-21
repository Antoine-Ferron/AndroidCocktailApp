package com.example.cocktailapi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cocktailapi.R
import com.example.cocktailapi.CocktailListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailListScreen(
    onCocktailClick: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val viewModel: CocktailListViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button_description)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.cocktails.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.cocktails) { cocktail ->
                        ListItem(
                            headlineContent = { Text(cocktail.strDrink) },
                            leadingContent = {
                                AsyncImage(
                                    model = cocktail.strDrinkThumb,
                                    contentDescription = stringResource(
                                        id = R.string.cocktail_image_description,
                                        cocktail.strDrink
                                    ),
                                    modifier = Modifier.size(56.dp)
                                )
                            },
                            modifier = Modifier.clickable { onCocktailClick(cocktail.idDrink) }
                        )
                    }
                }
            } else {
                Text("Aucun cocktail trouvé.")
            }
        }
    }
}