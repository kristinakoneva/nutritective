package com.kristinakoneva.nutritective.di.modules

import android.content.Context
import com.kristinakoneva.nutritective.di.qualifiers.CacheDir
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File

@Module
@InstallIn(SingletonComponent::class)
class CacheDirectoryModule {

    @Provides
    @CacheDir
    fun provideCacheDir(@ApplicationContext context: Context): File = context.cacheDir
}
