package com.veprek.honza.rocketlaunch.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rocket(
    @PrimaryKey
    val id: String,
    val active: Boolean = false,
    val name: String = "",
    val firstFlight: String = "",
    val description: String = "",
    val height: Height = Height(),
    val diameter: Height = Height(),
    val mass: Mass = Mass(),
    val firstStage: Stage?,
    val secondStage: Stage?,
    val images: List<String> = listOf()
)
