package com.kristinakoneva.nutritective.data.remote.interceptors

import com.kristinakoneva.nutritective.BuildConfig
import com.kristinakoneva.nutritective.utils.Constants.HEADER_API_KEY_CALORIE_NINJAS
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class CalorieNinjasApiKeyHeaderInterceptor @Inject constructor() : Interceptors.CalorieNinjasApiKeyHeader {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.header(
            HEADER_API_KEY_CALORIE_NINJAS,
            BuildConfig.CALORIE_NINJAS_API_KEY
        )

        return chain.proceed(requestBuilder.build())
    }
}
