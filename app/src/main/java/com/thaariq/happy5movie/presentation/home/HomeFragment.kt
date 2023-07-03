package com.thaariq.happy5movie.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.thaariq.happy5movie.data.responses.GenreItem
import com.thaariq.happy5movie.data.responses.GenreResponse
import com.thaariq.happy5movie.databinding.FragmentHomeBinding
import com.thaariq.happy5movie.presentation.MovieViewModel
import com.thaariq.happy5movie.presentation.home.adapter.HomeGenreAdapter

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
        return binding.root
    }

    private fun setupGenreRV(data : List<GenreItem>){
        binding.rvCategory.apply {
            val genreAdapter = HomeGenreAdapter()
            genreAdapter.setData(data)
            adapter = genreAdapter
        }

    }

}