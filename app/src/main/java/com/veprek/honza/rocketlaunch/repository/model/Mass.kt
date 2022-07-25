package com.veprek.honza.rocketlaunch.repository.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Mass(
    val t: Int = 0,
    val lb: Int = 0
)
