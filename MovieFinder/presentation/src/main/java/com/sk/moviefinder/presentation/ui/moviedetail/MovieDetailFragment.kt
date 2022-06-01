package com.sk.moviefinder.presentation.ui.moviedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sk.moviefinder.base.BaseFragment
import com.sk.moviefinder.presentation.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]
    }

    override fun viewReady() {
    }

    override fun getLayout(): Int = R.layout.movie_detail_fragment

}