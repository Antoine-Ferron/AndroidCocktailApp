package com.example.cocktailapi.model

import okhttp3.OkHttpClient
import okhttp3.Request

object RepositoryUtils {
    private val client = OkHttpClient()

    fun sendGet(url: String): String {
        println("URL : $url")
        val request = Request.Builder().url(url).build()
        return client.newCall(request).execute().use { //it:Response
            if (!it.isSuccessful) {
                throw Exception("Erreur serveur :${it.code}\n${it.body.string()}")
            }
            it.body.string()
        }
    }
}