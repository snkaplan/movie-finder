package com.sk.moviefinder.presentation.ui.moviesearch

import androidx.lifecycle.ViewModelProvider
import android.util.Log
import com.sk.moviefinder.base.BaseFragment
import com.sk.moviefinder.presentation.R
import javax.inject.Inject

class MovieSearchFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieSearchViewModel::class.java]
    }

    override fun viewReady() {
    }

    override fun getLayout(): Int = R.layout.movie_detail_fragment

}