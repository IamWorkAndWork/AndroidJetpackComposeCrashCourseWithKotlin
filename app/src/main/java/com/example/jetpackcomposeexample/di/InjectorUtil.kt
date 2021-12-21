package com.example.jetpackcomposeexample.di

import com.example.jetpackcomposeexample.data.api.MealsApi
import com.example.jetpackcomposeexample.data.datasource.MealsDataSource
import com.example.jetpackcomposeexample.data.repository.MealsRepository
import com.example.jetpackcomposeexample.domain.usecase.GetMealsUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InjectorUtil {

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    fun provideOKHttp(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideMealApi(retrofit: Retrofit): MealsApi {
        return retrofit.create(MealsApi::class.java)
    }

    fun provideMealDataSource(mealsApi: MealsApi): MealsDataSource {
        return MealsDataSource(mealsApi = mealsApi)
    }

    fun provideMealRepository(mealsDataSource: MealsDataSource): MealsRepository {
        return MealsRepository(mealsDataSource = mealsDataSource)
    }

    fun provideGetMealsUseCase(mealsRepository: MealsRepository): GetMealsUseCase {
        return GetMealsUseCase(mealsRepository = mealsRepository)
    }

}