package com.sk.moviefinder.di.module

import com.sk.moviefinder.presentation.MovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class, ViewModelModule::class])
    abstract fun contributeMovieActivity(): MovieActivity
}