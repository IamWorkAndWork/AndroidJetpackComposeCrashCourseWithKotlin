package com.example.jetpackcomposeexample.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.basicofjetpackcompose.ui.theme.ComposeAppTheme
import com.example.jetpackcomposeexample.di.InjectorUtil
import com.example.jetpackcomposeexample.presentation.ui.meals.MealsCategoriesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MealsCategoriesViewModel>() {
        val okHttpCLient = InjectorUtil.provideOKHttp()
        val retrofit = InjectorUtil.provideRetrofit(okHttpClient = okHttpCLient)
        val mealApi = InjectorUtil.provideMealApi(retrofit = retrofit)
        val mealDataSource = InjectorUtil.provideMealDataSource(mealsApi = mealApi)
        val mealRepository = InjectorUtil.provideMealRepository(mealsDataSource = mealDataSource)
        val getMealsUseCase = InjectorUtil.provideGetMealsUseCase(mealsRepository = mealRepository)

        MealsCategoriesViewModel.Factory(getMealsUseCase = getMealsUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            ComposeAppTheme(){
                MealsCategoriesScreen(viewModel)
            }

        }

    }

}