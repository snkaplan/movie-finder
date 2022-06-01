package com.sk.moviefinder.domain.di.module

import com.sk.moviefinder.domain.usecase.MovieDetailUseCase
import com.sk.moviefinder.domain.usecase.MovieDetailUseCaseImpl
import com.sk.moviefinder.domain.usecase.SearchMovieUseCase
import com.sk.moviefinder.domain.usecase.SearchMovieUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideSearchMovieUseCase(): SearchMovieUseCase {
        return SearchMovieUseCaseImpl()
    }

    @Provides
    fun provideMovieDetailUseCase(): MovieDetailUseCase {
        return MovieDetailUseCaseImpl()
    }
}