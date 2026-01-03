package com.example.swasthyamitra.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenFoodFactsApi {
    
    @GET("api/v0/product/{barcode}.json")
    suspend fun getProductByBarcode(@Path("barcode") barcode: String): Response<FoodProductResponse>
    
    @GET("cgi/search.pl")
    suspend fun searchProducts(
        @Query("search_terms") searchTerms: String,
        @Query("search_simple") searchSimple: Int = 1,
        @Query("action") action: String = "process",
        @Query("json") json: Int = 1,
        @Query("page_size") pageSize: Int = 20,
        @Query("page") page: Int = 1
    ): Response<SearchProductsResponse>
    
    companion object {
        private const val BASE_URL = "https://world.openfoodfacts.org/"
        
        fun create(): OpenFoodFactsApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenFoodFactsApi::class.java)
        }
    }
}

// Response data classes
data class FoodProductResponse(
    val status: Int,
    val product: Product?
)

data class SearchProductsResponse(
    val count: Int,
    val page: Int,
    val page_size: Int,
    val products: List<Product>?
)

data class Product(
    val product_name: String?,
    val brands: String?,
    val nutriments: Nutriments?,
    val serving_size: String?,
    val image_url: String?,
    val code: String? // barcode
)

data class Nutriments(
    val `energy-kcal_100g`: Double?,
    val `energy-kcal_serving`: Double?,
    val proteins_100g: Double?,
    val proteins_serving: Double?,
    val carbohydrates_100g: Double?,
    val carbohydrates_serving: Double?,
    val fat_100g: Double?,
    val fat_serving: Double?,
    val fiber_100g: Double?,
    val fiber_serving: Double?
)
