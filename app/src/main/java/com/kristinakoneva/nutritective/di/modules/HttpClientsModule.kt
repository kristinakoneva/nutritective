package com.kristinakoneva.nutritective.di.modules

import com.kristinakoneva.nutritective.di.qualifiers.CacheDir
import com.kristinakoneva.nutritective.di.qualifiers.OpenFoodFactsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class HttpClientsModule {

    companion object {
        private const val MAX_CACHING_SIZE = 100L * 1024L * 1024L
    }

    @Provides
    @OpenFoodFactsApi
    fun openFoodFactsApiBaseUrl(): String = "https://world.openfoodfacts.net/api/v2/"

    @Provides
    @Singleton
    @OpenFoodFactsApi
    fun openFoodFactsApiOkHttpClient(
        @CacheDir cacheDir: File
    ): OkHttpClient = OkHttpClient.Builder().apply {
        cache(
            Cache(
                directory = File(cacheDir, "http_cache"),
                maxSize = MAX_CACHING_SIZE
            )
        )
    }.build()
}