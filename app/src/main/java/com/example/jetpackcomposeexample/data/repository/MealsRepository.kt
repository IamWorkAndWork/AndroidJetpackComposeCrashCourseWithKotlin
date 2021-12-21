package com.example.jetpackcomposeexample.data.repository

import com.example.jetpackcomposeexample.data.datasource.MealsDataSource
import com.example.jetpackcomposeexample.domain.model.MealModel
import kotlinx.coroutines.flow.Flow

class MealsRepository(
    private val mealsDataSource: MealsDataSource
) {

    suspend fun getMeals(): Flow<Result<List<MealModel>>> {
        return mealsDataSource.getMeals()
    }

}