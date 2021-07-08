package com.example.anbuapp.ui.fragment.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.anbuapp.data.remote.GenreX
import com.example.anbuapp.databinding.ItemGenreBinding

class GenreAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<GenreX, GenreAdapter.GenreViewHolder>(COMPARATOR) {
    inner class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item: GenreX? = getItem(position)
                    if (item != null) {
                        if (item.isSelected()) {
                            item.setSelected(false)
                        } else {
                            item.setSelected(true)
                        }
                        listener.onItemClick(item)
                        notifyDataSetChanged()
                    }

                }
            }
        }

        fun bind(genre: GenreX) {
            with(binding) {
                textGenre.text = genre.name
                if (genre.isSelected()) {
                    textGenre.setTextColor(Color.RED)
                } else {
                    textGenre.setTextColor(Color.BLACK)
                }
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding: ItemGenreBinding =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val currentItem: GenreX? = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GenreX>() {
            override fun areItemsTheSame(oldItem: GenreX, newItem: GenreX): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GenreX, newItem: GenreX): Boolean =
                oldItem == newItem

        }
    }

    interface OnItemClickListener {
        fun onItemClick(genre: GenreX)
    }
}