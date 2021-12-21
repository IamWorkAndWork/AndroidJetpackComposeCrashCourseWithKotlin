package com.example.jetpackcomposeexample.presentation.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeexample.domain.model.MealModel
import com.example.jetpackcomposeexample.domain.usecase.GetMealsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(
    private val getMealsUseCase: GetMealsUseCase
) : ViewModel() {

    val mealsState: MutableState<List<MealModel>> = mutableStateOf(emptyList<MealModel>())

    private val _mealStateFlow = MutableStateFlow<List<MealModel>>(emptyList())
    val mealStateFlow: StateFlow<List<MealModel>> = _mealStateFlow

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getMealsUseCase.execute()
                .collectLatest { result ->
                    if (result.isSuccess) {
                        result.getOrNull()?.let { mealModelList ->
                            mealsState.value = mealModelList
                            _mealStateFlow.value = mealModelList
                        }

                    }
                }
        }
    }

    fun fetchMeals(){
        viewModelScope.launch {
            getMealsUseCase.execute()
                .collectLatest { result ->
                    if (result.isSuccess) {
                        result.getOrNull()?.let { mealModelList ->
                            mealsState.value = mealModelList
                            _mealStateFlow.value = mealModelList
                        }
                    }
                }
        }
    }

    class Factory(
        private val getMealsUseCase: GetMealsUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MealsCategoriesViewModel(
                getMealsUseCase = getMealsUseCase
            ) as T
        }

    }

}