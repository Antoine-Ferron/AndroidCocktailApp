// Dans ui/DetailScreen.kt
package com.example.cocktailapi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.tensorflow.lite.support.label.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: MainScreenState,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.cocktail?.strDrink ?: "Détails") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.TopCenter
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null) {
                Text(text = state.error, color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.align(Alignment.Center))
            } else if (state.cocktail != null) {
                val cocktail = state.cocktail
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()), // Pour faire défiler si le contenu est long
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = cocktail.strDrinkThumb,
                        contentDescription = "Image of ${cocktail.strDrink}",
                        modifier = Modifier.size(200.dp).clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = cocktail.strDrink,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TagInfo(iconEmoji = "🏷️", text = cocktail.strCategory ?: "-")
                        TagInfo(iconEmoji = "🍷", text = cocktail.strAlcoholic ?: "-")
                        TagInfo(iconEmoji = "🍸", text = cocktail.strGlass ?: "-")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // --- Section des Ingrédients ---
                    SectionTitle(title = "Ingrédients")
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            cocktail.ingredientList.forEach { (ingredient, measure) ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = ingredient, color = MaterialTheme.colorScheme.onSurface)
                                    Text(text = measure ?: "", color = MaterialTheme.colorScheme.onSurface)
                                }
                                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // --- Section des Instructions ---
                    SectionTitle(title = "Instructions")
                    Text(
                        text = cocktail.strInstructionsFR ?: cocktail.strInstructions ?: "Aucune instruction disponible.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // Espace en bas
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
    )
}

@Composable
private fun TagInfo(iconEmoji: String, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = iconEmoji,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}