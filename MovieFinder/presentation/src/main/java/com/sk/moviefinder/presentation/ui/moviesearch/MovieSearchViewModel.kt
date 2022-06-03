package com.sk.moviefinder.presentation.ui.moviesearch

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import com.sk.moviefinder.base.BaseViewModel
import com.sk.moviefinder.base.ConnectionState
import com.sk.moviefinder.base.Success
import com.sk.moviefinder.base.Error
import com.sk.moviefinder.domain.model.search.MovieSearchResult
import com.sk.moviefinder.domain.model.onFailure
import com.sk.moviefinder.domain.model.onSuccess
import com.sk.moviefinder.domain.model.search.Movie
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

    private var currentPage = 0
    private var currentMovieName = ""

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
                currentPage = 1
                currentMovieName = name
            }.onFailure {
                _viewState.value = Error(it.throwable)
            }
        }
    }

    fun loadMoreMovie() {
        val oldList = arrayListOf<Movie>()
        try {
            val currentState = viewState.value as Success<MovieSearchResult>
            oldList.addAll(currentState.data.movies)
            if (oldList.size >= currentState.data.totalResults) {
                _viewState.value = Error(Throwable(("No more movie!!")))
                return
            }
            currentPage++
            executeUseCase {
                searchMovieUseCase.getMoreMovie(currentMovieName, currentPage).onSuccess {
                    _viewState.value = Success(it.apply {
                        movies.addAll(0, oldList)
                    })
                }.onFailure {
                    _viewState.value = Error(it.throwable)
                }
            }
        } catch (e: Exception) {
            Log.e(this::class.simpleName, "Exception occurred while loading more movies", e)
        }
    }

    /**
     * Listens network changes.
     */
    fun listenNetwork() {
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }
}