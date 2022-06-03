package com.sk.moviefinder.presentation.ui.moviedetail

import com.sk.moviefinder.base.BaseViewModel
import com.sk.moviefinder.base.Success
import com.sk.moviefinder.domain.model.Failure
import com.sk.moviefinder.domain.model.detail.MovieDetail
import com.sk.moviefinder.domain.model.onFailure
import com.sk.moviefinder.domain.model.onSuccess
import com.sk.moviefinder.domain.usecase.MovieDetailUseCase
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val movieDetailUseCase: MovieDetailUseCase) :
    BaseViewModel<MovieDetail>() {
    /**
     * Gets the given movie detail
     */
    fun getMovieDetail(id: String) {
        executeUseCase {
            movieDetailUseCase.getMovieDetail(id).onSuccess {
                _viewState.value = Success(it)
            }.onFailure { Failure(it) }
        }
    }
}