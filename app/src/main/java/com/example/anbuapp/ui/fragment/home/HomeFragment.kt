package com.example.anbuapp.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.anbuapp.MainActivity
import com.example.anbuapp.R
import com.example.anbuapp.data.remote.Movie
import com.example.anbuapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), MovieAdapter.OnItemClickListener {
    private val viewModel by viewModels<MovieViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding
        _binding = FragmentHomeBinding.bind(view)

        //setup adapter
        val adapter = MovieAdapter(this)

        binding.apply {
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MovieLoadStateAdapter { adapter.retry() },
                footer = MovieLoadStateAdapter { adapter.retry() }
            )
            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }

        adapter.addLoadStateListener { loadSate ->
            binding.apply {
                progressBar.isVisible = loadSate.source.refresh is LoadState.Loading
                rvMovie.isVisible = loadSate.source.refresh is LoadState.NotLoading
                btnRetry.isVisible = loadSate.source.refresh is LoadState.Error
                tvFailed.isVisible = loadSate.source.refresh is LoadState.Error

                //not found
                if (loadSate.source.refresh is LoadState.NotLoading && loadSate.append.endOfPaginationReached && adapter.itemCount<1){
                    rvMovie.isVisible = false
                    tvNotFound.isVisible = true
                }else{
                    tvNotFound.isVisible = false
                }
            }

        }

        //setup viewmodel
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        //setup menu searchview
        setHasOptionsMenu(true)
    }

    //menu search view
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.rvMovie.scrollToPosition(0)
                    viewModel.searchMovies(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    binding.rvMovie.scrollToPosition(0)
                    viewModel.searchMovies(newText)
//                    searchView.clearFocus()
                }
                return true
            }
        })
    }

    override fun onItemClick(movie: Movie) {

        //navigasi fragment
        val action = HomeFragmentDirections.actionNavHomeToNavDetails(movie)
        findNavController().navigate(action)

        //intent
        activity?.let{
//            val intent = Intent (it, MainActivity::class.java)
//            intent.putExtra("MOVIE_SAM",movie)
//            it.startActivity(intent)
        }
    }
}


