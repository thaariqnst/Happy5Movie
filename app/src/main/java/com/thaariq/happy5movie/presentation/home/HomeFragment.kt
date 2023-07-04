package com.thaariq.happy5movie.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thaariq.happy5movie.data.responses.GenreItem
import com.thaariq.happy5movie.data.responses.ShowingMovieItem
import com.thaariq.happy5movie.data.responses.TopRatedMovieItem
import com.thaariq.happy5movie.databinding.FragmentHomeBinding
import com.thaariq.happy5movie.presentation.viewmodels.MovieViewModel
import com.thaariq.happy5movie.presentation.home.adapter.HomeGenreAdapter
import com.thaariq.happy5movie.presentation.home.adapter.ShowingMovieAdapter
import com.thaariq.happy5movie.presentation.home.adapter.TopRatedMovieAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        viewModel.genre()
        viewModel.genre.observe(viewLifecycleOwner){
            setupGenreRV(it)
        }

        viewModel.showingMovie()
        viewModel.showingMovie.observe(viewLifecycleOwner){
            setupShowingMovieRV(it)
        }
        viewModel.topRatedMovie()
        viewModel.topRatedMovie.observe(viewLifecycleOwner){
            setupTopRatedMovieRV(it)
        }

        binding.edtSearch.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.edtSearch.text.toString().trim()
                val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment2(query)
                findNavController().navigate(action)

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        return binding.root
    }

    private fun setupGenreRV(data : List<GenreItem>){
        binding.rvCategory.apply {
            val genreAdapter = HomeGenreAdapter()
            genreAdapter.setData(data)
            adapter = genreAdapter
        }
    }
    private fun setupShowingMovieRV(data: List<ShowingMovieItem>){
        binding.rvLatest.apply {
            val showingAdapter = ShowingMovieAdapter()
            showingAdapter.setData(data)
            adapter = showingAdapter
        }
    }
    private fun setupTopRatedMovieRV(data : List<TopRatedMovieItem>){
        binding.rvPopular.apply {
            val topRatedAdapter = TopRatedMovieAdapter()
            topRatedAdapter.setData(data)
            adapter = topRatedAdapter
        }
    }

}