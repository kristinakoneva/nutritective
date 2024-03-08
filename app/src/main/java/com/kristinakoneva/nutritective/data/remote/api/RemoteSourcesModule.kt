package com.kristinakoneva.nutritective.data.remote.api

import com.kristinakoneva.nutritective.data.remote.api.openfoodfacts.OpenFoodFactsSource
import com.kristinakoneva.nutritective.data.remote.api.openfoodfacts.OpenFoodFactsSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteSourcesModule {

    @Binds
    fun bindOpenFoodFactsSource(source: OpenFoodFactsSourceImpl): OpenFoodFactsSource
}