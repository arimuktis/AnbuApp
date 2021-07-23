package com.example.anbuapp.ui.fragment.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.Target
import com.example.anbuapp.R
import com.example.anbuapp.data.remote.Result
import com.example.anbuapp.databinding.FragmentDetailsBinding
import com.example.anbuapp.ui.fragment.home.MovieLoadStateAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation


@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details), ReviewAdapter.OnItemClickListener {
    private val args by navArgs<DetailsFragmentArgs>()

    private val viewModel by viewModels<DetailViewModel>()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding
        _binding = FragmentDetailsBinding.bind(view)

        //setup adapter
        val adapter = ReviewAdapter(this)

        //view binding
        binding.apply {
            val movie = args.movie
            Glide.with(this@DetailsFragment)
                .load("${movie.baseUrl}${movie.poster_path}")
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        tvDescription.isVisible = true
                        tvMovieTitle.isVisible = true
                        return false
                    }

                }).into(ivPoster)

            Glide.with(this@DetailsFragment)
                .load("${movie.baseUrl}${movie.poster_path}")
                .apply(bitmapTransform(BlurTransformation(55, 5)))
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        tvDescription.isVisible = true
                        tvMovieTitle.isVisible = true
                        return false
                    }

                }).into(background)
            tvDescription.text = movie.overview
            tvMovieTitle.text = movie.original_title

            //RecyclerView Movie
            rvReview.setHasFixedSize(true)
            rvReview.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MovieLoadStateAdapter { adapter.retry() },
                footer = MovieLoadStateAdapter { adapter.retry() }
            )


            //Binding Button Retry
            btnRetry.setOnClickListener {
                adapter.retry()
            }

            adapter.addLoadStateListener { loadSate ->
                binding.apply {
                    progressBar.isVisible = loadSate.source.refresh is LoadState.Loading
                    rvReview.isVisible = loadSate.source.refresh is LoadState.NotLoading
                    btnRetry.isVisible = loadSate.source.refresh is LoadState.Error
                    tvFailed.isVisible = loadSate.source.refresh is LoadState.Error

                    //not found
                    if (loadSate.source.refresh is LoadState.NotLoading && loadSate.append.endOfPaginationReached && adapter.itemCount < 1) {
                        rvReview.isVisible = false
                        tvNotFound.isVisible = true
                    } else {
                        tvNotFound.isVisible = false
                    }
                }

            }


            //setup viewmodel
            viewModel.reviews.observe(viewLifecycleOwner) {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }

            viewModel.trailer.observe(viewLifecycleOwner) {

            }

            lifecycle.addObserver(youtube)

            viewModel.trailers.observe(viewLifecycleOwner) {
                youtube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(it.results[0].key, 0f)
                    }
                })
            }



            binding.rvReview.scrollToPosition(0)
            viewModel.getReview(movie.id.toString())
            viewModel.getTrailer(movie.id.toString())
            viewModel.getTrailers(movie.id.toString())

        }
    }

    override fun onItemClick(result: Result) {

    }
}
