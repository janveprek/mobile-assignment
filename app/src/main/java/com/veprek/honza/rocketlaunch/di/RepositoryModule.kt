package com.veprek.honza.rocketlaunch.di

import com.veprek.honza.rocketlaunch.repository.RocketRepository
import com.veprek.honza.rocketlaunch.repository.RocketRepositoryImpl
import com.veprek.honza.rocketlaunch.repository.api.Api
import com.veprek.honza.rocketlaunch.repository.mapper.HeightApiMapper
import com.veprek.honza.rocketlaunch.repository.mapper.MassApiMapper
import com.veprek.honza.rocketlaunch.repository.mapper.RocketApiMapper
import com.veprek.honza.rocketlaunch.repository.mapper.StageApiMapper
import org.koin.dsl.module

val repoModule = module {
    factory { HeightApiMapper() }
    factory { MassApiMapper() }
    factory { StageApiMapper() }
    factory { RocketApiMapper(get(), get(), get()) }
    single<RocketRepository> {
        RocketRepositoryImpl(get<Api>(), get(), get())
    }
}
