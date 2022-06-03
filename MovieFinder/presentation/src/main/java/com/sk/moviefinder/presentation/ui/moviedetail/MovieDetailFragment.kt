package com.sk.moviefinder.presentation.ui.moviedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sk.moviefinder.base.*
import com.sk.moviefinder.common.Constants.MOVIE_ID
import com.sk.moviefinder.common.gone
import com.sk.moviefinder.common.snackbar
import com.sk.moviefinder.common.subscribe
import com.sk.moviefinder.common.visible
import com.sk.moviefinder.domain.model.detail.MovieDetail
import com.sk.moviefinder.presentation.R
import com.sk.moviefinder.presentation.databinding.MovieDetailFragmentBinding
import javax.inject.Inject

class MovieDetailFragment : BaseFragment() {
    private var movieId: String = ""
    private lateinit var binding: MovieDetailFragmentBinding
    private var currentMovie: MovieDetail? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        movieId = arguments?.getString(MOVIE_ID) ?: ""
        return binding.root
    }

    override fun viewReady() {
        subscribeToData()
        viewModel.getMovieDetail(movieId)
    }

    override fun getLayout(): Int = R.layout.movie_detail_fragment

    /**
     * Subscribes the live data on view model.
     */
    private fun subscribeToData() {
        viewModel.viewState.subscribe(this, ::handleViewState)
    }

    /**
     * Handles view state when state changed.
     */
    private fun handleViewState(viewState: ViewState<MovieDetail>) {
        when (viewState) {
            is Loading -> {
                binding.movieDetailsGroup.gone()
                binding.progressBar.visible()
            }
            is Success -> {
                currentMovie = viewState.data
                currentMovie?.let {
                    binding.apply {
                        movieDetailsGroup.visible()
                        progressBar.gone()
                        movieTitleTv.text = it.title
                        descriptionTv.text = it.description
                        genresTv.text = it.genre
                        runtimeTv.text = it.runtime
                        voteCountTv.text = it.imdbVotes
                        imdbRatingTv.text = it.imdbRating
                        Glide.with(requireView()).load(it.poster)
                            .placeholder(R.drawable.shape_movie_placeholder)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .apply(RequestOptions().override(800, 600))
                            .into(posterIv)
                    }
                }

            }
            is Error -> {
                binding.movieDetailsGroup.gone()
                binding.progressBar.visible()
                snackbar("${viewState.error.localizedMessage}", requireView())
            }
        }
    }


}