package com.veprek.honza.rocketlaunch.repository.mapper

import com.veprek.honza.rocketlaunch.repository.entity.StageNetwork
import com.veprek.honza.rocketlaunch.repository.model.Stage
import javax.inject.Inject

class StageApiMapper @Inject constructor() : ApiMapper<StageNetwork, Stage> {
    override fun mapToDomain(apiEntity: StageNetwork): Stage {
        return Stage(
            reusable = apiEntity.reusable,
            engines = apiEntity.engines ?: 0,
            fuelAmountTons = apiEntity.fuelAmountTons?.toInt() ?: 0,
            burnTimeSec = apiEntity.burnTimeSec?.toInt() ?: 0
        )
    }
}
