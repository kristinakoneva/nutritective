package com.kristinakoneva.nutritective.di.modules

import com.kristinakoneva.nutritective.domain.shared.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {
    @Provides
    @Singleton
    fun dispatchers(): AppDispatchers {
        return AppDispatchers(
            main = Dispatchers.Main,
            io = Dispatchers.IO
        )
    }
}
