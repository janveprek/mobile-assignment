package com.veprek.honza.rocketlaunch.repository.api

import com.veprek.honza.rocketlaunch.repository.entity.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkStatusInterceptor(private val connectionManager: ConnectionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected()) {
            chain.proceed(chain.request())
        } else {
            throw NoConnectionException()
        }
    }
}
