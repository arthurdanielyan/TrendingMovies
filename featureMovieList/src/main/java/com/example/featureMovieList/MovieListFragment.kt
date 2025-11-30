package com.example.featureMovieList

import android.app.AppComponentFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.presentation.extensions.collectWithLifecycle
import com.example.core.presentation.extensions.hideSupportActionBarBackButton
import com.example.core.presentation.extensions.setSupportActionBarTitle
import com.example.core.presentation.viewState.DataLoadingState
import com.example.featureMovieList.databinding.FragmentMoviesListBinding
import com.example.featureMovieList.ui.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

    private val viewModel by viewModel<MovieListViewModel>()

    private lateinit var binding: FragmentMoviesListBinding

    private val moviesAdapter = MoviesAdapter(
        onClick = {
            viewModel.onMovieClick(it)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMoviesListBinding.inflate(inflater).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeState()
        observeEffects()
    }

    private fun setupViews() {
        setSupportActionBarTitle(getString(R.string.movie_list))
        hideSupportActionBarBackButton()
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesAdapter
        }
        binding.errorPlaceholder.btnRetry.setOnClickListener {
            viewModel.onRetry()
        }
        binding.networkErrorPlaceholder.btnRetry.setOnClickListener {
            viewModel.onRetry()
        }
    }

    private fun observeState() {
        collectWithLifecycle(viewModel.movies) { courses ->
            moviesAdapter.submitMovies(courses)
        }
        collectWithLifecycle(viewModel.dataLoadingState) { loadingState ->
            binding.errorPlaceholder.root.isVisible         = (loadingState == DataLoadingState.ERROR)
            binding.networkErrorPlaceholder.root.isVisible  = (loadingState == DataLoadingState.NETWORK_ERROR)
            binding.loadingIndicator.isVisible              = (loadingState == DataLoadingState.LOADING)
            binding.rvMovies.isVisible                     = (loadingState == DataLoadingState.SUCCESS)
        }
    }

    private fun observeEffects() {
        collectWithLifecycle(viewModel.uiEffect) { effect ->
            when(effect) {
                is MovieListUiEffect.NavigateToDetails -> {
                    findNavController().navigate(
                        MovieListFragmentDirections.actionToMovieDetails(effect.movieId)
                    )
                }
            }
        }
    }
}