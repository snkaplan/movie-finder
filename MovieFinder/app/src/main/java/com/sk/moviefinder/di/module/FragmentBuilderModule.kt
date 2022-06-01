package com.sk.moviefinder.di.module

import com.sk.moviefinder.presentation.ui.moviedetail.MovieDetailFragment
import com.sk.moviefinder.presentation.ui.moviesearch.MovieSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieSearchFragment(): MovieSearchFragment
}