package com.sk.moviefinder.presentation.ui.moviesearch

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.sk.moviefinder.base.BaseViewModel
import com.sk.moviefinder.base.ConnectionState
import com.sk.moviefinder.base.Success
import com.sk.moviefinder.base.Error
import com.sk.moviefinder.domain.model.search.MovieSearchResult
import com.sk.moviefinder.domain.model.onFailure
import com.sk.moviefinder.domain.model.onSuccess
import com.sk.moviefinder.domain.usecase.SearchMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MovieSearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val connectivityManager: ConnectivityManager,
    private val networkRequest: NetworkRequest,
) :
    BaseViewModel<MovieSearchResult>() {
    private val _connectionState = MutableStateFlow(ConnectionState<Boolean?>(null))
    val connectionState: StateFlow<ConnectionState<Boolean?>> = _connectionState

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _connectionState.value = ConnectionState(true)
            super.onAvailable(network)
        }

        override fun onLost(network: Network) {
            _connectionState.value = ConnectionState(false)
            super.onLost(network)
        }
    }

    fun searchMovie(name: String) {
        executeUseCase {
            searchMovieUseCase.searchMovie(name).onSuccess {
                _viewState.value = Success(it)
            }.onFailure {
                _viewState.value = Error(it.throwable)
            }
        }
    }

    fun listenNetwork() {
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }
}