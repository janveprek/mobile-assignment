package com.veprek.honza.rocketlaunch.repository.api

import com.veprek.honza.rocketlaunch.repository.entity.RocketNetwork
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("rockets")
    suspend fun getAllRockets(): List<RocketNetwork>

    @GET("rockets/{rocketId}")
    suspend fun getRocket(@Path("rocketId") rocketId: String): RocketNetwork
}
