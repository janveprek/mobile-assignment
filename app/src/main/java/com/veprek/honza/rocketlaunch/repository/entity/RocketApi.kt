package com.veprek.honza.rocketlaunch.repository.entity

import com.squareup.moshi.Json

data class RocketApi(
    @Json(name = "id")
    val id: String = "1",
    @Json(name = "active")
    val active: Boolean = false,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "first_flight")
    val firstFlight: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "height")
    val height: HeightApi = HeightApi(),
    @Json(name = "diameter")
    val diameter: HeightApi = HeightApi(),
    @Json(name = "mass")
    val mass: MassApi = MassApi(),
    @Json(name = "first_stage")
    val firstStage: StageApi = StageApi(),
    @Json(name = "second_stage")
    val secondStage: StageApi = StageApi(),
    @Json(name = "flickr_images")
    val images: List<String> = listOf()
)

data class HeightApi(
    @Json(name = "feet")
    val feet: Double = 0.0,
    @Json(name = "meters")
    val meters: Double = 0.0
)

data class MassApi(
    @Json(name = "kg")
    val kg: Int = 0,
    @Json(name = "lb")
    val lb: Int = 0
)

data class StageApi(
    @Json(name = "reusable")
    val reusable: Boolean = false,
    @Json(name = "engines")
    val engines: Int? = 0,
    @Json(name = "fuel_amount_tons")
    val fuelAmountTons: Double? = 0.0,
    @Json(name = "burn_time_sec")
    val burnTimeSec: Double? = 0.0
)
