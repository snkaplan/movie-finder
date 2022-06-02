package com.sk.moviefinder.di.module


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.sk.moviefinder.MovieFinderApplication
import dagger.Module
import dagger.Provides

import javax.inject.Singleton


@Module
class ApplicationModule(
) {
    @Singleton
    @Provides
    fun provideContext(application: MovieFinderApplication): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideNetworkRequest(context: Context): NetworkRequest {
        return NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkCallback(context: Context): ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    }

}