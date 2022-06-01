package com.sk.moviefinder.data.di.module

import com.sk.moviefinder.data.api.MovieService
import com.sk.moviefinder.data.repository.MovieRepositoryImpl
import com.sk.moviefinder.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }
}