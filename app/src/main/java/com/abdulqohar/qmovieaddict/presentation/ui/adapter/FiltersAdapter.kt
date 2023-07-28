package com.abdulqohar.qmovieaddict.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdulqohar.qmovieaddict.R
import com.abdulqohar.qmovieaddict.databinding.MovieFilterItemLayoutBinding

class FiltersAdapter(private val actions: FilterAdapterActions): RecyclerView.Adapter<FiltersAdapter.FiltersViewHolder>() {

    private var filtersList: MutableList<String> = mutableListOf()
    var selectedPos = 0

    inner class FiltersViewHolder(private val binding: MovieFilterItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.filterItemTextView.text = item

            binding.root.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                notifyItemChanged(selectedPos)
                selectedPos = adapterPosition
                notifyItemChanged(selectedPos)
                actions.onFilterItemClicked(filtersList[adapterPosition])
            }
            binding.filterItemTextView.setBackgroundResource(if (selectedPos == adapterPosition
            ) R.drawable.filter_item_selected_background else R.drawable.filter_item_deselected_background)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersViewHolder {
        val inflater = MovieFilterItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FiltersViewHolder(inflater)
    }

    override fun getItemCount(): Int = filtersList.size

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        holder.bind(filtersList[position])
    }

    fun submitList(list: MutableList<String>) {
        filtersList = list
        notifyDataSetChanged()
    }
}

interface FilterAdapterActions {
    fun onFilterItemClicked(item: String)
}
