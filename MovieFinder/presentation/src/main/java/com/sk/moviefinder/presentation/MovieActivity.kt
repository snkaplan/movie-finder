package com.sk.moviefinder.presentation

import android.os.Bundle
import com.sk.moviefinder.base.BaseActivity
import com.sk.moviefinder.presentation.ui.moviesearch.MovieSearchFragment

class MovieActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieSearchFragment())
                .commitNow()
        }
    }
}