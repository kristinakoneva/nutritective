package com.kristinakoneva.nutritective.data.local

import com.kristinakoneva.nutritective.data.local.sharedprefs.SharedPreferencesSource
import com.kristinakoneva.nutritective.data.local.sharedprefs.SharedPreferencesSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalSourcesModule {

    @Binds
    fun bindSharedPrefsSource(sharedPreferencesSourceImpl: SharedPreferencesSourceImpl): SharedPreferencesSource
}
