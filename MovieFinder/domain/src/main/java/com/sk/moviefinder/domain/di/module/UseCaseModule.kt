package com.sk.moviefinder.domain.di.module

import com.sk.moviefinder.domain.repository.MovieRepository
import com.sk.moviefinder.domain.usecase.MovieDetailUseCase
import com.sk.moviefinder.domain.usecase.MovieDetailUseCaseImpl
import com.sk.moviefinder.domain.usecase.SearchMovieUseCase
import com.sk.moviefinder.domain.usecase.SearchMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    fun provideSearchMovieUseCase(movieRepository: MovieRepository): SearchMovieUseCase {
        return SearchMovieUseCaseImpl(movieRepository)
    }

    @Provides
    fun provideMovieDetailUseCase(movieRepository: MovieRepository): MovieDetailUseCase {
        return MovieDetailUseCaseImpl(movieRepository)
    }
}