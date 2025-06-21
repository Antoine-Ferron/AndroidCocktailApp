package com.example.cocktailapi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cocktailapi.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onNavigateBack: () -> Unit,
    onResultClick: (String) -> Unit
) {
    val viewModel: SearchViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recherche") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TextField(
                value = state.query,
                onValueChange = { viewModel.onQueryChange(it) },
                label = { Text("Nom du cocktail") },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                singleLine = true
            )

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (state.noResultsFound) {
                Text("Aucun résultat trouvé.", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.results) { cocktail ->
                        ListItem(
                            headlineContent = { Text(cocktail.strDrink) },
                            leadingContent = {
                                AsyncImage(
                                    model = cocktail.strDrinkThumb,
                                    contentDescription = cocktail.strDrink,
                                    modifier = Modifier.size(56.dp)
                                )
                            },
                            modifier = Modifier.clickable { onResultClick(cocktail.idDrink) }
                        )
                    }
                }
            }
        }
    }
}