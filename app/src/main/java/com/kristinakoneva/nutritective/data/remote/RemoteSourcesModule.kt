package com.kristinakoneva.nutritective.data.remote

import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.CalorieNinjasSource
import com.kristinakoneva.nutritective.data.remote.sources.calorieninjas.CalorieNinjasSourceImpl
import com.kristinakoneva.nutritective.data.remote.sources.firebaseauth.FirebaseAuthSource
import com.kristinakoneva.nutritective.data.remote.sources.firebaseauth.FirebaseAuthSourceImpl
import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.OpenFoodFactsSource
import com.kristinakoneva.nutritective.data.remote.sources.openfoodfacts.OpenFoodFactsSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteSourcesModule {

    @Binds
    fun bindOpenFoodFactsSource(source: OpenFoodFactsSourceImpl): OpenFoodFactsSource

    @Binds
    fun bindCalorieNinjasSource(source: CalorieNinjasSourceImpl): CalorieNinjasSource

    @Binds
    fun bindFirebaseAuthSource(source: FirebaseAuthSourceImpl): FirebaseAuthSource
}
