package com.veprek.honza.rocketlaunch.di

import com.veprek.honza.rocketlaunch.feature.detail.presentation.RocketDetailViewModel
import com.veprek.honza.rocketlaunch.feature.launch.presentation.RocketLaunchViewModel
import com.veprek.honza.rocketlaunch.feature.list.presentation.RocketListViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { RocketListViewModel(get()) }
    factory { RocketDetailViewModel(get(), get(), get()) }
    factory { RocketLaunchViewModel() }
}
