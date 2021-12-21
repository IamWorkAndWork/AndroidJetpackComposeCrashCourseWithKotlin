package com.example.jetpackcomposeexample.data.datasource

import com.example.jetpackcomposeexample.data.api.MealsApi
import com.example.jetpackcomposeexample.domain.model.MealModel
import kotlinx.coroutines.flow.flow

class MealsDataSource(
    private val mealsApi: MealsApi
) {

    suspend fun getMeals() = flow<Result<List<MealModel>>> {
        try {
            val response = mealsApi.getMeals()
                .categories.map {
                    MealModel(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        imageUrl = it.imageUrl
                    )
                }
            emit(Result.success(response))
        } catch (error: Exception) {
            emit(Result.failure(error))
        }
    }

}