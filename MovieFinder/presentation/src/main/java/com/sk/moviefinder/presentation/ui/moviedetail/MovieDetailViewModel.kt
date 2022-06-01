package com.sk.moviefinder.presentation.ui.moviedetail

import androidx.lifecycle.ViewModel
import com.sk.moviefinder.domain.usecase.MovieDetailUseCase
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val movieDetailUseCase: MovieDetailUseCase) :
    ViewModel() {
}