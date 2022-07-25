package com.veprek.honza.rocketlaunch.repository.mapper

interface ApiListMapper<E, D> {
    fun mapToDomainList(apiEntityList: List<E>): List<D>
}
