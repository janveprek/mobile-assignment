package com.veprek.honza.rocketlaunch.repository

import com.veprek.honza.rocketlaunch.repository.api.Api
import com.veprek.honza.rocketlaunch.repository.db.dao.RocketDao
import com.veprek.honza.rocketlaunch.repository.entity.NoConnectionException
import com.veprek.honza.rocketlaunch.repository.entity.ResponseWrapper
import com.veprek.honza.rocketlaunch.repository.mapper.RocketApiMapper
import com.veprek.honza.rocketlaunch.repository.model.Rocket
import com.veprek.honza.rocketlaunch.repository.model.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import quanti.com.kotlinlog.Log

class RocketRepositoryImpl(
    private val rocketApi: Api,
    private val rocketDao: RocketDao,
    private val rocketMapper: RocketApiMapper
) : RocketRepository {

    override suspend fun getAllRockets(): Flow<ResponseWrapper<List<Rocket>?>> = flow {
        emit(ResponseWrapper(State.LOADING, null))

        try {
            val rockets = rocketApi.getAllRockets()
            rocketDao.insertRockets(rocketMapper.mapToDomainList(rockets))
            emit(ResponseWrapper(State.SUCCESS, rocketMapper.mapToDomainList(rockets)))
        } catch (e: Exception) {
            if (e is NoConnectionException) {
                val rockets = rocketDao.getAll()
                if (rockets.isEmpty()) {
                    emit(ResponseWrapper(State.NO_INTERNET, null))
                } else {
                    emit(ResponseWrapper(State.SUCCESS, rockets))
                }
            } else {
                emit(ResponseWrapper(State.FAILED, null))
                Log.d("Other exception ${e.stackTraceToString()}")
            }
        }
    }

    override suspend fun getRocket(id: String): Flow<ResponseWrapper<Rocket?>> = flow {
        emit(ResponseWrapper(State.LOADING, null))
        try {
            val rocket = rocketApi.getRocket(id)
            emit(ResponseWrapper(State.SUCCESS, rocketMapper.mapToDomain(rocket)))
        } catch (e: Exception) {
            if (e is NoConnectionException) {
                val rocket = rocketDao.findById(id)
                if (rocket == null) {
                    emit(ResponseWrapper(State.NO_INTERNET, null))
                } else {
                    emit(ResponseWrapper(State.SUCCESS, rocket))
                }
            } else {
                emit(ResponseWrapper(State.FAILED, null))
                Log.d("Other exception ${e.stackTraceToString()}")
            }
        }
    }
}
