package com.sk.moviefinder.presentation.ui.moviesearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sk.moviefinder.domain.model.search.Movie
import com.sk.moviefinder.presentation.R
import com.sk.moviefinder.presentation.databinding.ListItemMovieBinding


class MoviesRvAdapter :
    RecyclerView.Adapter<MoviesRvAdapter.MViewHolder>() {
    private lateinit var itemBinding: ListItemMovieBinding
    private var movies: MutableList<Movie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        itemBinding =
            ListItemMovieBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return MViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun reloadList(movies: ArrayList<Movie>) {
        movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun setData(movies: ArrayList<Movie>) {
        this.movies.addAll(movies)
        notifyItemRangeChanged(0, movies.size)
    }

    fun clear() {
        val size: Int = movies.size
        movies.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun getData() = movies


    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class MViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            itemBinding.titleTv.text = movie.title
            itemBinding.yearTv.text = movie.year
            movie.poster?.let {
                Glide.with(view).load(movie.poster)
                    .centerCrop()
                    .thumbnail(0.5f)
                    .placeholder(R.drawable.ic_launcher_background)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemBinding.posterIv)
            }
        }
    }
}
