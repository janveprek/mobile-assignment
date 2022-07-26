package com.veprek.honza.rocketlaunch.repository.mapper
import com.veprek.honza.rocketlaunch.repository.entity.MassNetwork
import com.veprek.honza.rocketlaunch.repository.model.Mass
import javax.inject.Inject

class MassApiMapper @Inject constructor() : ApiMapper<MassNetwork, Mass> {
    override fun mapToDomain(apiEntity: MassNetwork): Mass {
        return Mass(
            t = apiEntity.kg / 1000,
            lb = apiEntity.lb
        )
    }
}
