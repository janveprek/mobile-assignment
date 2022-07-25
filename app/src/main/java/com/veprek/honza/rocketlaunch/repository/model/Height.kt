package com.veprek.honza.rocketlaunch.repository.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Height(
    val feet: Int = 0,
    val meters: Int = 0
)
