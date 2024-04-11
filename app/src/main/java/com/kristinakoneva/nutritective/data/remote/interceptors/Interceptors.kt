package com.kristinakoneva.nutritective.data.remote.interceptors

import okhttp3.Interceptor

interface Interceptors {

    interface Chucker : Interceptor

    interface CalorieNinjasApiKeyHeader : Interceptor

    interface UserAgentHeader: Interceptor
}
