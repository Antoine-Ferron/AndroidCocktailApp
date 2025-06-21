package com.example.cocktailapi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cocktailapi.CategoryListViewModel
import com.example.cocktailapi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListScreen(
    onCategoryClick: (String) -> Unit
) {
    val viewModel: CategoryListViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.categories_title)) }
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
            } else if(state.error != null){
                Text(text = stringResource(id = R.string.error_generic, state.error!!))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.categories) { category ->
                        ListItem(
                            headlineContent = { Text(category.strCategory) },
                            modifier = Modifier.clickable { onCategoryClick(category.strCategory) }
                        )
                    }
                }
            }
        }
    }
}