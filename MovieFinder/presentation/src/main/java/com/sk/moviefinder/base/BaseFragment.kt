package com.sk.moviefinder.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sk.moviefinder.common.showFragment
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewReady()
    }

    abstract fun viewReady()

    abstract fun getLayout(): Int

    fun addFragment(fragment: Fragment, containerId: Int, addToBackStack: Boolean = false) {
        activity?.showFragment(fragment, containerId, addToBackStack)
    }
}