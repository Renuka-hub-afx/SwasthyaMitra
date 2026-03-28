package com.example.swasthyamitra.api

// Retrofit imports for REST API networking and HTTP operations
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Retrofit interface for OpenFoodFacts API - global food database for barcode scanning and nutrition lookup
interface OpenFoodFactsApi {

    // Get food product details by scanning barcode (13-digit EAN or UPC code)
    @GET("api/v0/product/{barcode}.json")
    suspend fun getProductByBarcode(@Path("barcode") barcode: String): Response<FoodProductResponse>

    // Search food products by name or keywords with pagination support
    @GET("cgi/search.pl")
    suspend fun searchProducts(
        @Query("search_terms") searchTerms: String,       // Food name or keywords to search
        @Query("search_simple") searchSimple: Int = 1,    // Use simple search algorithm
        @Query("action") action: String = "process",      // API action parameter
        @Query("json") json: Int = 1,                     // Return JSON response format
        @Query("page_size") pageSize: Int = 20,           // Number of products per page
        @Query("page") page: Int = 1                      // Page number for pagination
    ): Response<SearchProductsResponse>

    // Companion object for API client creation with base configuration
    companion object {
        // OpenFoodFacts API base URL for worldwide food database
        private const val BASE_URL = "https://world.openfoodfacts.org/"

        // Factory method to create configured Retrofit API client
        fun create(): OpenFoodFactsApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)                          // Set API endpoint base URL
                .addConverterFactory(GsonConverterFactory.create()) // JSON serialization with Gson
                .build()
                .create(OpenFoodFactsApi::class.java)       // Generate API implementation
        }
    }
}

// Response data classes for API responses with structured food product information

// Response wrapper for single product lookup by barcode
data class FoodProductResponse(
    val status: Int,        // API response status code (0=not found, 1=found)
    val product: Product?   // Product details if found, null if not found
)

// Response wrapper for product search results with pagination
data class SearchProductsResponse(
    val count: Int,             // Total number of matching products
    val page: Int,              // Current page number
    val page_size: Int,         // Products per page
    val products: List<Product>? // List of matching products
)

// Core product data structure with food information and nutritional data
data class Product(
    val product_name: String?,  // Food product name (e.g., "Organic Whole Wheat Bread")
    val brands: String?,        // Brand names (e.g., "Nature's Own, Whole Foods")
    val nutriments: Nutriments?, // Comprehensive nutritional information
    val serving_size: String?,   // Serving size description (e.g., "1 slice (28g)")
    val image_url: String?,      // Product package image URL
    val code: String?           // Product barcode number
)

// Detailed nutritional information per 100g and per serving
data class Nutriments(
    val `energy-kcal_100g`: Double?,       // Calories per 100 grams
    val `energy-kcal_serving`: Double?,    // Calories per serving
    val proteins_100g: Double?,            // Protein in grams per 100g
    val proteins_serving: Double?,         // Protein in grams per serving
    val carbohydrates_100g: Double?,       // Carbohydrates in grams per 100g
    val carbohydrates_serving: Double?,    // Carbohydrates in grams per serving
    val fat_100g: Double?,                 // Fat in grams per 100g
    val fat_serving: Double?,              // Fat in grams per serving
    val fiber_100g: Double?,               // Fiber in grams per 100g
    val fiber_serving: Double?             // Fiber in grams per serving
)
