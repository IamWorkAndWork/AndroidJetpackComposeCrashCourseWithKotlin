package com.example.jetpackcomposeexample.data.api

import com.example.jetpackcomposeexample.data.model.MealsCategoriesResponse
import retrofit2.http.GET

interface MealsApi {
    @GET("categories.php")
    suspend fun getMeals(): MealsCategoriesResponse
}