package com.example.cocktailapi.model

import com.example.cocktailapi.*

import com.example.cocktailapi.model.RepositoryUtils.sendGet
import com.google.gson.Gson

object CocktailRepository {

    fun getCocktailByName(name: String): DrinksBean? {
        val result = sendGet("${SEARCH_BASE_URL}s=$name")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, DrinksBean::class.java)
    }

    fun getCocktailByFirstLetter(letter: Char): DrinksBean? {
        val result = sendGet("${SEARCH_BASE_URL}f=$letter")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, DrinksBean::class.java)
    }

    fun getCocktailById(id: String): DrinksBean? {
        val result = sendGet("${LOOKUP_BASE_URL}i=$id")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, DrinksBean::class.java)
    }

    fun getRandomCocktail(): DrinksBean? {
        return Gson().fromJson(sendGet(RANDOM_BASE_URL), DrinksBean::class.java)
    }

    fun getCocktailByIngredient(ingredient: String): SmallDrinksBean? {
        val result = sendGet("${SEARCH_BASE_URL}i=$ingredient")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, SmallDrinksBean::class.java)
    }

    fun getCocktailByAlcoholic(alcoholic: String): SmallDrinksBean? {
        val result = sendGet("${FILTER_BASE_URL}a=$alcoholic")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, SmallDrinksBean::class.java)
    }

    fun getCocktailByCategory(category: String): SmallDrinksBean? {
        val result = sendGet("${FILTER_BASE_URL}c=$category")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, SmallDrinksBean::class.java)
    }

    fun getCocktailByGlass(glass: String): SmallDrinksBean? {
        val result = sendGet("${FILTER_BASE_URL}g=$glass")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, SmallDrinksBean::class.java)
    }
}

data class DrinksBean(
    val drinks: List<CocktailBean>?
)

data class CocktailBean(
    val idDrink: String,
    val strDrink: String,
    val strDrinkAlternate: String?,
    val strTags: String?,
    val strVideo: String?,
    val strCategory: String?,
    val strIBA: String?,
    val strAlcoholic: String?,
    val strGlass: String?,
    val strInstructions: String?,
    val strInstructionsES: String?,
    val strInstructionsDE: String?,
    val strInstructionsFR: String?,
    val strInstructionsIT: String?,
    val strInstructionsZH_HANS: String?,
    val strInstructionsZH_HANT: String?,
    val strDrinkThumb: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strImaageAttribution: String?,
    val strCreativeCommonsConfirmed: String?
)

data class SmallDrinksBean(
    val drinks: List<SmallCocktailBean>
)

data class SmallCocktailBean (
    val strDrink: String,
    val strDrinkThumb: String?,
    val idDrink: String,
)