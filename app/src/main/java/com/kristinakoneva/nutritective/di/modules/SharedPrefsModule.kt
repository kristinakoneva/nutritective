package com.kristinakoneva.nutritective.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.kristinakoneva.nutritective.di.qualifiers.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefsModule {

    companion object {
        const val SHARED_PREFS = "nutritective_shared_prefs"
    }

    @Provides
    @SharedPrefs
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    }
}
