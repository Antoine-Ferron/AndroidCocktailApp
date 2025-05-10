package com.example.cocktailapi.model

import com.example.cocktailapi.LOOKUP_BASE_URL
import com.example.cocktailapi.SEARCH_BASE_URL
import com.example.cocktailapi.model.RepositoryUtils.sendGet
import com.google.gson.Gson

object IngredientsRepository {
    fun getIngredientByName(name: String): IngredientsBean? {
        val result = sendGet("${SEARCH_BASE_URL}i=$name")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, IngredientsBean::class.java)
    }

    fun getIngredientById(id: String): IngredientsBean? {
        val result = sendGet("${LOOKUP_BASE_URL}iid=$id")
        if (result.contains("no data found"))
            return null

        return Gson().fromJson(result, IngredientsBean::class.java)
    }

}

data class IngredientsBean(
    val ingredients: ArrayList<IngredientBean>
)

data class IngredientBean(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String?,
    val strType: String?,
    val strAlcohol: String?,
    val strABV: String?
)