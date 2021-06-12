package com.example.anbuapp.ui.fragment.home

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.anbuapp.R
import com.example.anbuapp.data.remote.Movie
import com.example.anbuapp.databinding.ItemMovieBinding

class MovieAdapter(private val listener : OnItemClickListener) : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(COMPARATOR) {

    inner class MovieViewHolder(private val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION) {
                    val item:Movie? = getItem(position)
                    if(item!=null){
                        listener.onItemClick(item)
                    }
                }
            }
        }
            fun bind(movie: Movie){
                with(binding){
                    Glide.with(itemView)
                        .load("${movie.baseUrl}${movie.poster_path}")
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_error)
                        .into(ivPoster)

                    titleMovie.text = movie.original_title
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding : ItemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem : Movie? = getItem(position)
        if(currentItem != null){
            holder.bind(currentItem)
        }
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }
    }

    interface OnItemClickListener{
        fun onItemClick(movie: Movie)
    }
}