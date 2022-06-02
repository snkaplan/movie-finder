package com.sk.moviefinder.presentation.ui.moviesearch

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import android.view.*
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sk.moviefinder.base.*
import com.sk.moviefinder.common.*
import com.sk.moviefinder.common.Constants.MOVIE_ID
import com.sk.moviefinder.domain.model.search.MovieSearchResult
import com.sk.moviefinder.presentation.R
import com.sk.moviefinder.presentation.databinding.MovieSearchFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class MovieSearchFragment : BaseFragment() {
    private lateinit var binding: MovieSearchFragmentBinding
    private lateinit var moviesRvAdapter: MoviesRvAdapter
    private lateinit var searchView: SearchView

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieSearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MovieSearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun viewReady() {
        setHasOptionsMenu(true)
        setupUI()
        subscribeToData()
        viewModel.listenNetwork()
    }

    override fun getLayout(): Int = R.layout.movie_detail_fragment

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_menu, menu)
        searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.apply {
            queryHint = "Search"
            isSubmitButtonEnabled = true
            onActionViewExpanded()
        }
        search(searchView)
    }

    private fun subscribeToData() {
        viewModel.viewState.subscribe(this, ::handleViewState)
        handleConnectionState()
    }

    private fun setupUI() {
        moviesRvAdapter = MoviesRvAdapter()
        binding.moviesRv.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            setItemViewCacheSize(0)
            adapter = moviesRvAdapter
            addOnItemTouchListener(
                RecyclerItemClickListener(
                    context.applicationContext,
                    object : RecyclerItemClickListener.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            if (moviesRvAdapter.getData().isNotEmpty()) {
                                val searchItem = moviesRvAdapter.getData()[position]
                                searchItem.let {
                                    val bundle = bundleOf(MOVIE_ID to it.id)
                                    Navigation.findNavController(view)
                                        .navigate(R.id.action_movieSearchFragment_to_movieDetailFragment,
                                            bundle)
                                }
                            }
                        }
                    })
            )
        }
    }

    private fun handleConnectionState() {
        lifecycleScope.launchWhenStarted {
            viewModel.connectionState.collectLatest { connectionState ->
                when (connectionState.data) {
                    true -> {
                        binding.networkStatusTv.text = getString(R.string.text_connectivity)
                        binding.networkStatusLayout.apply {
                            setBackgroundColor(context.getColor(R.color.colorStatusConnected))
                            animate()
                                .alpha(1f)
                                .setStartDelay(0)
                                .setDuration(4500)
                                .setListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator) {
                                        gone()
                                    }
                                })
                        }
                    }
                    false -> {
                        binding.networkStatusTv.text = getString(R.string.text_no_connectivity)
                        binding.networkStatusLayout.apply {
                            visible()
                            setBackgroundColor(context.getColor(R.color.colorStatusNotConnected))
                        }
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun handleViewState(viewState: ViewState<MovieSearchResult>) {
        when (viewState) {
            is Loading -> {
                binding.moviesRv.removeAllViews();
                binding.moviesRv.recycledViewPool.clear();
                binding.moviesRv.gone()
                binding.searchLinearLayout.gone()
                binding.progressBar.visible()
            }
            is Success -> {
                binding.moviesRv.visible()
                binding.searchLinearLayout.gone()
                binding.progressBar.gone()
                moviesRvAdapter.clear()
                moviesRvAdapter.setData(viewState.data.movies)
            }
            is Error -> {
                binding.progressBar.gone()
                snackbar("Error happened. ${viewState.error.localizedMessage}", requireView())
            }
        }
    }

    private fun search(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.dismissKeyboard(searchView)
                searchView.clearFocus()
                viewModel.connectionState.value.data?.let {
                    if (it) {
                        viewModel.searchMovie(query)
                        return true
                    }
                }
                snackbar("Please make sure you are connected to the internet", searchView)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }
}