package com.abdulqohar.qmovieaddict.presentation.ui.screens

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.abdulqohar.qmovieaddict.R
import com.abdulqohar.qmovieaddict.data.local.entity.FavouredMovie
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.data.remote.model.MovieDetailsDto
import com.abdulqohar.qmovieaddict.databinding.FragmentHomeBinding
import com.abdulqohar.qmovieaddict.databinding.FragmentMovieDetailsBinding
import com.abdulqohar.qmovieaddict.presentation.ui.mappers.Mappers.Companion.mapMovieEntityToFavouredEntity
import com.abdulqohar.qmovieaddict.presentation.ui.viewmodel.MovieDetailsViewModel
import com.abdulqohar.qmovieaddict.util.Constants.Companion.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val vieModel: MovieDetailsViewModel by viewModels()
    private lateinit var movieItem: MovieEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onMovieDetailsResponseReceived()
    }

    //Function to handle initial state of UI
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun init() {
        movieItem = arguments?.getParcelable("movieEntity")!!
        vieModel.getMovieDetails(movieItem.id.toString())
    }

    //Function observing the state of movie details call state
    private fun onMovieDetailsResponseReceived() {
        vieModel.popularMovies.observe(viewLifecycleOwner) { movieDetails ->
            updateUi(movieDetails)
        }
    }

    // Function to update the UI with the data returned from the call to get shipment details
    private fun updateUi(movieDetailsDto: MovieDetailsDto) {
        Glide.with(requireActivity())
            .load(IMAGE_BASE_URL +movieDetailsDto.backdropPath)
            .placeholder(R.drawable.movies_placeholder)
            .into(binding.posterImageView)
        binding.titleTextView.text = movieDetailsDto.title
        binding.shortDescriptionTextView.text = movieDetailsDto.releaseDate
        binding.ratingTextView.text =  String.format("%.1f", movieDetailsDto.averageVote)
        binding.descriptionTextView.text = movieDetailsDto.overview
        binding.favoriteTextView.setOnClickListener {
           vieModel.saveFavouredMovies(mapMovieEntityToFavouredEntity(movieItem))
            Snackbar.make(requireView(), "Movie added to favourites", Snackbar.LENGTH_LONG).show()
        }
    }

}