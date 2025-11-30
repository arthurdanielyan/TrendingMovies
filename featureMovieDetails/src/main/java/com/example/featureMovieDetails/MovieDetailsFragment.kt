package com.example.featureMovieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil3.load
import coil3.request.crossfade
import com.example.core.presentation.extensions.collectWithLifecycle
import com.example.core.presentation.extensions.format
import com.example.core.presentation.extensions.setSupportActionBarTitle
import com.example.core.presentation.extensions.showSupportActionBarBackButton
import com.example.core.presentation.viewState.DataLoadingState
import com.example.featureMovieDetails.databinding.FragmentMovieDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {

    private val viewModel by viewModel<MovieDetailsViewModel>()
    private val args: MovieDetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(args.movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMovieDetailsBinding.inflate(inflater).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeState()
    }

    private fun setupViews() {
        setSupportActionBarTitle(getString(R.string.movie_details))
        showSupportActionBarBackButton {
            findNavController().popBackStack()
        }
        binding.errorPlaceholder.btnRetry.setOnClickListener {
            viewModel.onRetry()
        }
        binding.networkErrorPlaceholder.btnRetry.setOnClickListener {
            viewModel.onRetry()
        }
    }

    private fun observeState() {
        collectWithLifecycle(viewModel.movieDetails) { movie ->
            binding.movieBackdrop.load(movie.backdropUrl) {
                crossfade(true)
            }
            binding.title.text = movie.title
            binding.averageRating.text = movie.rating.format()
            binding.releaseDate.text = getString(R.string.release_date, movie.releaseDate)
            binding.genres.text = getString(R.string.genres, movie.genres.joinToString(", "))
            binding.overview.text = movie.overview
        }
        collectWithLifecycle(viewModel.dataLoadingState) { loadingState ->
            binding.errorPlaceholder.root.isVisible         = (loadingState == DataLoadingState.ERROR)
            binding.networkErrorPlaceholder.root.isVisible  = (loadingState == DataLoadingState.NETWORK_ERROR)
            binding.loadingIndicator.isVisible              = (loadingState == DataLoadingState.LOADING)
            binding.content.isVisible                       = (loadingState == DataLoadingState.SUCCESS)
        }
    }
}