package com.veprek.honza.rocketlaunch.repository.mapper

import com.veprek.honza.rocketlaunch.repository.entity.HeightNetwork
import com.veprek.honza.rocketlaunch.repository.model.Height
import javax.inject.Inject

class HeightApiMapper @Inject constructor() : ApiMapper<HeightNetwork, Height> {
    override fun mapToDomain(apiEntity: HeightNetwork): Height {
        return Height(
            feet = apiEntity.feet.toInt(),
            meters = apiEntity.meters.toInt()
        )
    }
}
