package com.sk.moviefinder

import com.sk.moviefinder.di.component.DaggerMovieFinderMainComponent
import dagger.android.DaggerApplication
import dagger.android.AndroidInjector


class MovieFinderApplication : DaggerApplication() {
/*
    override fun onCreate() {
        super.onCreate()
        val component: ApplicationComponent =
            FactoryCreator.createDaggerFactory().createAppComponent(this)
    }
*/

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMovieFinderMainComponent.factory().create(this)
    }
}