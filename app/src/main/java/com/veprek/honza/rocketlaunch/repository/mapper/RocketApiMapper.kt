package com.veprek.honza.rocketlaunch.repository.mapper

import com.veprek.honza.rocketlaunch.repository.entity.RocketNetwork
import com.veprek.honza.rocketlaunch.repository.model.Rocket
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RocketApiMapper(
    private val heightApiMapper: HeightApiMapper,
    private val massApiMapper: MassApiMapper,
    private val stageApiMapper: StageApiMapper
) : ApiMapper<RocketNetwork, Rocket>, ApiListMapper<RocketNetwork, Rocket> {
    private var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    override fun mapToDomain(apiEntity: RocketNetwork): Rocket {
        return Rocket(
            id = apiEntity.id,
            active = apiEntity.active,
            name = apiEntity.name.orEmpty(),
            firstFlight = formatDate(apiEntity.firstFlight.orEmpty()),
            description = apiEntity.description.orEmpty(),
            height = heightApiMapper.mapToDomain(apiEntity.height),
            diameter = heightApiMapper.mapToDomain(apiEntity.diameter),
            mass = massApiMapper.mapToDomain(apiEntity.mass),
            firstStage = stageApiMapper.mapToDomain(apiEntity.firstStage),
            secondStage = stageApiMapper.mapToDomain(apiEntity.secondStage),
            images = apiEntity.images
        )
    }

    override fun mapToDomainList(apiEntityList: List<RocketNetwork>): List<Rocket> {
        return apiEntityList.map { mapToDomain(it) }
    }

    private fun formatDate(dateString: String): String {
        val date = LocalDate.parse(dateString)
        return date.format(formatter)
    }
}
