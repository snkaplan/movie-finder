package com.sk.moviefinder.presentation.ui.moviedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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

    private fun subscribeToData() {
        viewModel.viewState.subscribe(this, ::handleViewState)
    }

    private fun handleViewState(viewState: ViewState<MovieDetail>) {
        when (viewState) {
            is Loading -> {
                binding.movieDetailsGroup.gone()
                binding.progressBar.visible()
            }
            is Success -> {
                currentMovie = viewState.data
                currentMovie?.let {
                    binding.movieDetailsGroup.visible()
                    binding.progressBar.gone()
                    binding.movieTitleTv.text = it.title
                    binding.descriptionTv.text = it.description
                    binding.genresTv.text = it.genre
                    binding.runtimeTv.text = it.runtime
                    binding.voteCountTv.text = it.imdbVotes
                    Glide.with(requireView()).load(it.poster)
                        .placeholder(R.drawable.ic_launcher_background)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.posterIv)
                }

            }
            is Error -> {
                binding.movieDetailsGroup.gone()
                binding.progressBar.visible()
                snackbar("Error happened. ${viewState.error.localizedMessage}", requireView())
            }
        }
    }


}