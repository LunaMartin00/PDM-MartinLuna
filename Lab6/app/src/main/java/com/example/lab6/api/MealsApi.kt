package com.example.lab6.api

import com.example.lab6.data.MealResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class MealListResponse(
    val meals: List<MealResponse>?
)

interface MealsApi {
    @GET("search.php")
    suspend fun getMeals(@Query("s") searchQuery: String = ""): MealListResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val mealsApi: MealsApi = retrofit.create(MealsApi::class.java)

    suspend fun getMeals(): List<MealResponse> {
        val response = mealsApi.getMeals("")
        return response.meals ?: emptyList()
    }
}