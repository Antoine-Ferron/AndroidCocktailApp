package com.example.cocktailapi.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

object RepositoryUtils {
    private val client = OkHttpClient()

    suspend fun sendGet(url: String): String {
        println("URL : $url")

        return withContext(Dispatchers.IO) {
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw Exception("Erreur serveur : ${response.code}\n${response.body.string()}")
                }

                response.body.string() ?: ""
            }
        }
    }
}