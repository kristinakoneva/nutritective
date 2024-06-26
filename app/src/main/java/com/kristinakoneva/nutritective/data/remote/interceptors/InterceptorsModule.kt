package com.kristinakoneva.nutritective.data.remote.interceptors

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class InterceptorsModule {

    @Provides
    @Singleton
    fun chuckerInterceptor(interceptor: ChuckerInterceptor): Interceptors.Chucker = interceptor

    @Provides
    @Singleton
    fun calorieNinjasApiKeyHeaderInterceptor(interceptor: CalorieNinjasApiKeyHeaderInterceptor):
        Interceptors.CalorieNinjasApiKeyHeader = interceptor

    @Provides
    @Singleton
    fun userAgentHeaderInterceptor(interceptor: UserAgentHeaderInterceptor):
        Interceptors.UserAgentHeader = interceptor
}
