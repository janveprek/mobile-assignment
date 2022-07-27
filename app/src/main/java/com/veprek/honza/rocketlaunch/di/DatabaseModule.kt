package com.veprek.honza.rocketlaunch.di

import androidx.room.Room
import com.veprek.honza.rocketlaunch.repository.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "rocket_db"
        ).fallbackToDestructiveMigration().build()
    }

    single {
        val database = get<AppDatabase>()
        database.rocketDao()
    }
}
