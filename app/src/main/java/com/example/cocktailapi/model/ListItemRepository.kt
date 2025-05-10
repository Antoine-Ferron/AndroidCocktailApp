package com.example.cocktailapi.model

import com.google.gson.Gson
import com.example.cocktailapi.LIST_BASE_URL

object ListItemRepository {

    fun getListCategory(): ListCategoryBean? {
        return Gson().fromJson(RepositoryUtils.sendGet( "${LIST_BASE_URL}c=list"), ListCategoryBean::class.java)
    }

    fun getListGlass(): ListGlassBean? {
        return Gson().fromJson(RepositoryUtils.sendGet( "${LIST_BASE_URL}g=list"), ListGlassBean::class.java)
    }

    fun getListAlcoholic(): ListAlcoholicBean? {
        return Gson().fromJson(RepositoryUtils.sendGet( "${LIST_BASE_URL}a=list"), ListAlcoholicBean::class.java)
    }

    fun getListIngredient(): ListIngredientBean? {
        return Gson().fromJson(RepositoryUtils.sendGet( "${LIST_BASE_URL}i=list"), ListIngredientBean::class.java)
    }
}

data class ListCategoryBean(
    val drinks: ArrayList<CategoryItemBean>
)

data class CategoryItemBean(
    val strCategory: String
)

data class ListGlassBean(
    val drinks: ArrayList<GlassItemBean>
)

data class GlassItemBean(
    val strGlass: String
)

data class ListAlcoholicBean(
    val drinks: ArrayList<AlcoholicItemBean>
)

data class AlcoholicItemBean(
    val strAlcoholic: String
)

data class ListIngredientBean(
    val drinks: ArrayList<IngredientItemBean>
)

data class IngredientItemBean(
    val strIngredient1: String
)