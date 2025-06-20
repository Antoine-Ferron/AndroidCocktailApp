package com.example.cocktailapi.model

import com.example.cocktailapi.LOOKUP_BASE_URL
import com.example.cocktailapi.SEARCH_BASE_URL
import com.example.cocktailapi.model.RepositoryUtils.sendGet
import com.google.gson.Gson

/**
 * Repository for the ingredients
 */
object IngredientsRepository {

    /**
     * Get the list of ingredients from the API
     *
     * @param name The name of the ingredient
     *
     * @return The list of ingredients
     */
    suspend fun getIngredientByName(name: String): IngredientsBean? {
        val result = sendGet("${SEARCH_BASE_URL}i=$name")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, IngredientsBean::class.java)
    }

    /**
     * Get the list of ingredients from the API
     *
     * @param id The id of the ingredient
     *
     * @return The list of ingredients
     */
    suspend fun getIngredientById(id: String): IngredientsBean? {
        val result = sendGet("${LOOKUP_BASE_URL}iid=$id")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, IngredientsBean::class.java)
    }

}

/**
 * Data class for the ingredients
 */
data class IngredientsBean(
    val ingredients: ArrayList<IngredientBean>
)

/**
 * Data class for the ingredients
 */
data class IngredientBean(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String?,
    val strType: String?,
    val strAlcohol: String?,
    val strABV: String?
)