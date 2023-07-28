package com.abdulqohar.qmovieaddict.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdulqohar.qmovieaddict.R
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.databinding.FragmentHomeBinding
import com.abdulqohar.qmovieaddict.presentation.ui.adapter.FilterAdapterActions
import com.abdulqohar.qmovieaddict.presentation.ui.adapter.FiltersAdapter
import com.abdulqohar.qmovieaddict.presentation.ui.adapter.PopularMoviesAdapter
import com.abdulqohar.qmovieaddict.presentation.ui.adapter.PopularMoviesAdapterActions
import com.abdulqohar.qmovieaddict.presentation.ui.mappers.Mappers.Companion.mapFavouredMovieEntityToMovieEntity
import com.abdulqohar.qmovieaddict.presentation.ui.mappers.Mappers.Companion.mapMovieEntityToFavouredEntity
import com.abdulqohar.qmovieaddict.presentation.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), PopularMoviesAdapterActions, FilterAdapterActions{
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var filtersAdapter: FiltersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onGetPopularMovieResponseReceived()
        onGetFavouredMoviesResponseReceived()
    }

    //Function to initialize initial state of UI
    private fun init() {
        popularMoviesAdapter = PopularMoviesAdapter(this)
        filtersAdapter = FiltersAdapter(this)
        binding.moviesRecyclerView.apply {
            adapter = popularMoviesAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false )
        }

        binding.movieFilterRecyclerView.apply {
            adapter = filtersAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        viewModel.fetchPopularMovies()
        filtersAdapter.submitList(mutableListOf("All", "Favourites"))
    }

    //Function observing state of popular movies
    private fun onGetPopularMovieResponseReceived()  {
        viewModel.popularMovies.observe(viewLifecycleOwner) { movies ->
            popularMoviesAdapter.submitList(movies as MutableList<MovieEntity>)
        }
    }

    //Function observing state of favourite movies

    private fun onGetFavouredMoviesResponseReceived() {
        viewModel.favouredMovies.observe(viewLifecycleOwner) { favouredMovies ->
            popularMoviesAdapter.submitList(favouredMovies.map { mapFavouredMovieEntityToMovieEntity(it) } as MutableList<MovieEntity>)
        }
    }

    //Abstract function to navigate to the movie details screen with the movie item in a bundle
    override fun onMovieItemClicked(movieEntity: MovieEntity) {
        val bundle = Bundle()
        bundle.putParcelable("movieEntity", movieEntity)
        findNavController().navigate(R.id.movieDetailsFragment, bundle)
    }

    //Abstract function to handle movie items filtering
    override fun onFilterItemClicked(item: String) {
        when (item) {
            "All" -> {viewModel.fetchPopularMovies()}
            "Favourites" -> {viewModel.getFavouredMoviesDto()}
        }
    }

}