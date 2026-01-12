package com.example.swasthyamitra.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Edamam Recipe Search API Interface
 */
interface EdamamApi {
    
    @GET("search")
    suspend fun searchRecipes(
        @Query("q") query: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("calories") calories: String? = null,
        @Query("from") from: Int = 0,
        @Query("to") to: Int = 20
    ): Response<EdamamResponse>
    
    companion object {
        private const val BASE_URL = "https://api.edamam.com/"
        const val APP_ID = "72ec9638"
        const val APP_KEY = "6349cea96a29f504407529f1ddc10e9f"
        
        fun create(): EdamamApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EdamamApi::class.java)
        }
    }
}

/**
 * Data Classes for Edamam API Response
 */
data class EdamamResponse(
    val q: String,
    val from: Int,
    val to: Int,
    val more: Boolean,
    val count: Int,
    val hits: List<Hit>
)

data class Hit(
    val recipe: Recipe
)

data class Recipe(
    val uri: String,
    val label: String,
    val image: String,
    val source: String,
    val url: String,
    val yield: Double,
    val calories: Double,
    val totalWeight: Double,
    val totalTime: Double,
    val ingredients: List<Ingredient>?,
    val totalNutrients: Map<String, Nutrient>?
)

data class Ingredient(
    val text: String,
    val weight: Double
)

data class Nutrient(
    val label: String,
    val quantity: Double,
    val unit: String
)
