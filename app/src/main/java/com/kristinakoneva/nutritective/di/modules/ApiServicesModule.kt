package com.kristinakoneva.nutritective.di.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.CalorieNinjasApiService
import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.OpenFoodFactsApiService
import com.kristinakoneva.nutritective.di.qualifiers.CalorieNinjasApi
import com.kristinakoneva.nutritective.di.qualifiers.OpenFoodFactsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private val json = Json { ignoreUnknownKeys = true }

@Module
@InstallIn(SingletonComponent::class)
class ApiServicesModule {

    @Provides
    fun openFoodFactsApiService(@OpenFoodFactsApi retrofit: Retrofit): OpenFoodFactsApiService =
        retrofit.create(OpenFoodFactsApiService::class.java)

    @Provides
    @OpenFoodFactsApi
    fun openFoodFactsApiRetrofit(
        @OpenFoodFactsApi baseUrl: String,
        @OpenFoodFactsApi okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .build()

    @Provides
    fun calorieNinjasApiService(@CalorieNinjasApi retrofit: Retrofit): CalorieNinjasApiService =
        retrofit.create(CalorieNinjasApiService::class.java)

    @Provides
    @CalorieNinjasApi
    fun calorieNinjasApiRetrofit(
        @CalorieNinjasApi baseUrl: String,
        @CalorieNinjasApi okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .build()
}