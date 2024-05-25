package com.kristinakoneva.nutritective.data.remote.interceptors

import android.content.Context
import com.kristinakoneva.nutritective.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class UserAgentHeaderInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptors.UserAgentHeader {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val appName = context.applicationInfo.loadLabel(context.packageManager).toString()
        val appVersion = context.packageManager.getPackageInfo(context.packageName, 0).versionName

        requestBuilder.header(
            Constants.HEADER_USER_AGENT,
            "$appName/$appVersion (non-commercial app)"
        )

        return chain.proceed(requestBuilder.build())
    }
}
