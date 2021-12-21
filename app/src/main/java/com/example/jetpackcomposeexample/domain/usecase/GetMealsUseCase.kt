package com.example.jetpackcomposeexample.domain.usecase

import com.example.jetpackcomposeexample.data.repository.MealsRepository
import com.example.jetpackcomposeexample.domain.model.MealModel
import kotlinx.coroutines.flow.Flow

class GetMealsUseCase(
    private val mealsRepository: MealsRepository
) {

    suspend fun execute(): Flow<Result<List<MealModel>>> {
        return mealsRepository.getMeals()
    }

}