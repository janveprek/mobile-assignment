package com.veprek.honza.rocketlaunch.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.veprek.honza.rocketlaunch.repository.api.Api
import com.veprek.honza.rocketlaunch.repository.api.ConnectionManager
import com.veprek.honza.rocketlaunch.repository.api.DownloadManager
import com.veprek.honza.rocketlaunch.repository.api.NetworkStatusInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { DownloadManager(androidContext()) }
    single { ConnectionManager(androidContext()) }
    factory { NetworkStatusInterceptor(get()) }
    factory { provideOkHttpClient(get()) }
    factory { provideMoshi() }
    single { provideRetrofit(get(), get()) }
    single<Api> { provideRocketApi(get()) }
}

fun provideOkHttpClient(
    networkStatusInterceptor: NetworkStatusInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(networkStatusInterceptor)
        .build()
}

fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://api.spacexdata.com/v4/")
    .client(okHttpClient)
    .build()

fun provideRocketApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
