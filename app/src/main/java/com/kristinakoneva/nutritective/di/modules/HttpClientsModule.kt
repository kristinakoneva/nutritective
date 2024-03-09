package com.kristinakoneva.nutritective.di.modules

import com.kristinakoneva.nutritective.data.remote.interceptors.Interceptors
import com.kristinakoneva.nutritective.data.remote.interceptors.InterceptorsModule
import com.kristinakoneva.nutritective.di.qualifiers.CacheDir
import com.kristinakoneva.nutritective.di.qualifiers.CalorieNinjasApi
import com.kristinakoneva.nutritective.di.qualifiers.OpenFoodFactsApi
import com.kristinakoneva.nutritective.utils.Constants.CALORIES_NINJAS_API_BASE_URL
import com.kristinakoneva.nutritective.utils.Constants.OPEN_FOOD_FACTS_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient

@Module(includes = [InterceptorsModule::class])
@InstallIn(SingletonComponent::class)
class HttpClientsModule {

    companion object {
        private const val MAX_CACHING_SIZE = 100L * 1024L * 1024L
    }

    @Provides
    @OpenFoodFactsApi
    fun openFoodFactsApiBaseUrl(): String = OPEN_FOOD_FACTS_API_BASE_URL

    @Provides
    @Singleton
    @OpenFoodFactsApi
    fun openFoodFactsApiOkHttpClient(
        chuckerInterceptor: Interceptors.Chucker,
        @CacheDir cacheDir: File
    ): OkHttpClient = OkHttpClient.Builder().apply {
        cache(
            Cache(
                directory = File(cacheDir, "http_cache"),
                maxSize = MAX_CACHING_SIZE
            )
        )
        addNetworkInterceptor(chuckerInterceptor)
    }.build()


    @Provides
    @CalorieNinjasApi
    fun calorieNinjasApiBaseUrl(): String = CALORIES_NINJAS_API_BASE_URL

    @Provides
    @Singleton
    @CalorieNinjasApi
    fun calorieNinjasApiOkHttpClient(
        chuckerInterceptor: Interceptors.Chucker,
        apiKeyHeaderInterceptor: Interceptors.CalorieNinjasApiKeyHeader,
        @CacheDir cacheDir: File
    ): OkHttpClient = OkHttpClient.Builder().apply {
        cache(
            Cache(
                directory = File(cacheDir, "http_cache"),
                maxSize = MAX_CACHING_SIZE
            )
        )
        addNetworkInterceptor(chuckerInterceptor)
        addInterceptor(apiKeyHeaderInterceptor)
    }.build()
}