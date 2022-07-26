package com.veprek.honza.rocketlaunch.repository.entity

import com.squareup.moshi.Json

data class RocketNetwork(
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
    val height: HeightNetwork = HeightNetwork(),
    @Json(name = "diameter")
    val diameter: HeightNetwork = HeightNetwork(),
    @Json(name = "mass")
    val mass: MassNetwork = MassNetwork(),
    @Json(name = "first_stage")
    val firstStage: StageNetwork = StageNetwork(),
    @Json(name = "second_stage")
    val secondStage: StageNetwork = StageNetwork(),
    @Json(name = "flickr_images")
    val images: List<String> = listOf()
)

data class HeightNetwork(
    @Json(name = "feet")
    val feet: Double = 0.0,
    @Json(name = "meters")
    val meters: Double = 0.0
)

data class MassNetwork(
    @Json(name = "kg")
    val kg: Int = 0,
    @Json(name = "lb")
    val lb: Int = 0
)

data class StageNetwork(
    @Json(name = "reusable")
    val reusable: Boolean = false,
    @Json(name = "engines")
    val engines: Int? = 0,
    @Json(name = "fuel_amount_tons")
    val fuelAmountTons: Double? = 0.0,
    @Json(name = "burn_time_sec")
    val burnTimeSec: Double? = 0.0
)
