package com.sk.moviefinder.di.module


import android.content.Context
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
}