package com.abdulqohar.qmovieaddict.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdulqohar.qmovieaddict.R
import com.abdulqohar.qmovieaddict.data.local.entity.MovieEntity
import com.abdulqohar.qmovieaddict.databinding.MovieItemLayoutBinding
import com.abdulqohar.qmovieaddict.util.Constants.Companion.IMAGE_BASE_URL
import com.bumptech.glide.Glide

class PopularMoviesAdapter(private val actions: PopularMoviesAdapterActions): RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    private var popularMoviesList: MutableList<MovieEntity> = mutableListOf()

    inner class PopularMoviesViewHolder(private val binding: MovieItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            binding.movieTitleTextView.text = movieEntity.title
            binding.ratingTextView.text = movieEntity.voteAverage?.toString()?: ""
            Glide.with(binding.root)
                .load(IMAGE_BASE_URL+movieEntity.posterPath)
                .placeholder(R.drawable.movies_placeholder)
                .into(binding.movieImageView)
            itemView.setOnClickListener {
                actions.onMovieItemClicked(popularMoviesList[adapterPosition])
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val inflater = MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  PopularMoviesViewHolder(inflater)
    }

    override fun getItemCount(): Int = popularMoviesList.size

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.bind(popularMoviesList[position])
    }

    fun submitList(list: MutableList<MovieEntity>) {
        popularMoviesList = list
        notifyDataSetChanged()
    }
}

interface PopularMoviesAdapterActions {
    fun onMovieItemClicked(movieEntity: MovieEntity)
}