package com.example.cocktailapi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cocktailapi.R
import com.example.cocktailapi.model.CocktailBean
import com.example.cocktailapi.ui.theme.CocktailApiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: MainScreenState,
    onCocktailClick: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.random_cocktail_title)) },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(Icons.Default.Search, contentDescription = stringResource(id = R.string.search_icon_description))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold
                )
            } else if (state.cocktail != null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onCocktailClick(state.cocktail.idDrink) }
                ) {
                    AsyncImage(
                        model = state.cocktail.strDrinkThumb,
                        contentDescription = "Image of ${state.cocktail.strDrink}",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(id = R.string.cocktail_of_the_day),
                        fontSize = 22.sp,
                        color = Color.White
                    )
                    Text(
                        text = state.cocktail.strDrink,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Success State Preview")
@Composable
fun CocktailScreenSuccessPreview() {
    CocktailApiTheme {
        val fakeCocktail = CocktailBean(
            idDrink = "1", strDrink = "Mojito", strDrinkThumb = "",
            strDrinkAlternate = "",
            strTags = "",
            strVideo = "",
            strCategory = "",
            strIBA = "",
            strAlcoholic = "",
            strGlass = "",
            strInstructions = "",
            strInstructionsES = "",
            strInstructionsDE = "",
            strInstructionsFR = "",
            strInstructionsIT = "",
            strInstructionsZH_HANS = "",
            strInstructionsZH_HANT = "",
            strIngredient1 = "",
            strIngredient2 = "",
            strIngredient3 = "",
            strIngredient4 = "",
            strIngredient5 = "",
            strIngredient6 = "",
            strIngredient7 = "",
            strIngredient8 = "",
            strIngredient9 = "",
            strIngredient10 = "",
            strIngredient11 = "",
            strIngredient12 = "",
            strIngredient13 = "",
            strIngredient14 = "",
            strIngredient15 = "",
            strMeasure1 = "",
            strMeasure2 = "",
            strMeasure3 = "",
            strMeasure4 = "",
            strMeasure5 = "",
            strMeasure6 = "",
            strMeasure7 = "",
            strMeasure8 = "",
            strMeasure9 = "",
            strMeasure10 = "",
            strMeasure11 = "",
            strMeasure12 = "",
            strMeasure13 = "",
            strMeasure14 = "",
            strMeasure15 = "",
            strImaageAttribution = "",
            strCreativeCommonsConfirmed = ""
        )
        HomeScreen(
            state = MainScreenState(cocktail = fakeCocktail),
            onCocktailClick = {},
            onSearchClick = {}
        )
    }

}

@Preview(showBackground = true, name = "Loading State Preview")
@Composable
fun CocktailScreenLoadingPreview() {
    CocktailApiTheme {
        HomeScreen(
            state = MainScreenState(isLoading = true),
            onCocktailClick = { },
            onSearchClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Error State Preview")
@Composable
fun CocktailScreenErrorPreview() {
    CocktailApiTheme {
        HomeScreen(
            state = MainScreenState(error = "Failed to load cocktail."),
            onCocktailClick = {},
            onSearchClick = {}
        )
    }
}