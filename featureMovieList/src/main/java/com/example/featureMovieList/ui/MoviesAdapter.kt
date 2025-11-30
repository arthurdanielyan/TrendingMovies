package com.example.featureMovieList.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.core.presentation.extensions.format
import com.example.domain.model.Movie
import com.example.featureMovieList.databinding.ItemMovieBinding

internal class MoviesAdapter(
    private val onClick: (Movie) -> Unit
) : ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            binding = ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun submitMovies(list: List<Movie>, onCommit: () -> Unit = {}) {
        submitList(list) {
            onCommit()
        }
    }

    class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val onClick: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.title.text = movie.title
            binding.averageRating.text = movie.averageRating.format()
            binding.moviePoster.load(movie.posterPath) {
                crossfade(true)
            }

            binding.root.setOnClickListener { onClick(movie) }
        }
    }
}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(old: Movie, new: Movie): Boolean =
        old.id == new.id

    override fun areContentsTheSame(old: Movie, new: Movie): Boolean =
        old == new
}
