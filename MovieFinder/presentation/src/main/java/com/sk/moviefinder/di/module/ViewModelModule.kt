package com.sk.moviefinder.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sk.moviefinder.di.annotation.ViewModelKey
import com.sk.moviefinder.presentation.ui.ViewModelFactory
import com.sk.moviefinder.presentation.ui.moviedetail.MovieDetailViewModel
import com.sk.moviefinder.presentation.ui.moviesearch.MovieSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieSearchViewModel::class)
    abstract fun bindSearchMovieViewModel(viewModel: MovieSearchViewModel): ViewModel
}