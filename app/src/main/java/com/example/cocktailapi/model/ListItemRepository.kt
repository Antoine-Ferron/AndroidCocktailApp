package com.example.cocktailapi.model

import com.google.gson.Gson
import com.example.cocktailapi.LIST_BASE_URL

object ListItemRepository {

    /**
     * Get the list of categories from the API
     */
    suspend fun getListCategory(): ListCategoryBean? {
        return Gson().fromJson(RepositoryUtils.sendGet( "${LIST_BASE_URL}c=list"), ListCategoryBean::class.java)
    }

    /**
     * Get the list of glasses from the API
     */
    suspend fun getListGlass(): ListGlassBean? {
        return Gson().fromJson(RepositoryUtils.sendGet( "${LIST_BASE_URL}g=list"), ListGlassBean::class.java)
    }

    /**
     * Get the list of alcoholics from the API
     */
    suspend fun getListAlcoholic(): ListAlcoholicBean? {
        return Gson().fromJson(RepositoryUtils.sendGet( "${LIST_BASE_URL}a=list"), ListAlcoholicBean::class.java)
    }

    /**
     * Get the list of ingredients from the API
     */
    suspend fun getListIngredient(): ListIngredientBean? {
        return Gson().fromJson(RepositoryUtils.sendGet( "${LIST_BASE_URL}i=list"), ListIngredientBean::class.java)
    }
}

/**
 * Data class for the list of categories
 */
data class ListCategoryBean(
    val drinks: ArrayList<CategoryItemBean>
)

/**
 * Data class for the list of categories
 */
data class CategoryItemBean(
    val strCategory: String
)

/**
 * Data class for the list of glasses
 */
data class ListGlassBean(
    val drinks: ArrayList<GlassItemBean>
)

/**
 * Data class for the list of glasses
 */
data class GlassItemBean(
    val strGlass: String
)

/**
 * Data class for the list of alcoholics
 */
data class ListAlcoholicBean(
    val drinks: ArrayList<AlcoholicItemBean>
)

/**
 * Data class for the list of alcoholics
 */
data class AlcoholicItemBean(
    val strAlcoholic: String
)

/**
 * Data class for the list of ingredients
 */
data class ListIngredientBean(
    val drinks: ArrayList<IngredientItemBean>
)

/**
 * Data class for the list of ingredients
 */
data class IngredientItemBean(
    val strIngredient1: String
)