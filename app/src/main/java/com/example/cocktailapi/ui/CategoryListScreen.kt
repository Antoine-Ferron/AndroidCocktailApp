package com.example.cocktailapi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cocktailapi.CategoryListViewModel

@Composable
fun CategoryListScreen(
    onCategoryClick: (String) -> Unit
) {
    val viewModel: CategoryListViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    if(state.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn {
            items(state.categories) { category ->
                ListItem(
                    headlineContent = { Text(category.strCategory) },
                    modifier = Modifier.clickable { onCategoryClick(category.strCategory) }
                )
            }
        }
    }
}