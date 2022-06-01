package com.sk.moviefinder.presentation.ui.moviesearch

import androidx.lifecycle.ViewModel
import com.sk.moviefinder.domain.usecase.SearchMovieUseCase
import javax.inject.Inject

class MovieSearchViewModel @Inject constructor(private val searchMovieUseCase: SearchMovieUseCase) :
    ViewModel() {
}