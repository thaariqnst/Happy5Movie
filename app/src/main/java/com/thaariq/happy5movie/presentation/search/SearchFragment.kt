package com.thaariq.happy5movie.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.thaariq.happy5movie.data.responses.SearchItem
import com.thaariq.happy5movie.data.responses.ShowingMovieItem
import com.thaariq.happy5movie.databinding.FragmentSearchBinding
import com.thaariq.happy5movie.presentation.home.HomeFragmentDirections
import com.thaariq.happy5movie.presentation.home.adapter.ShowingMovieAdapter
import com.thaariq.happy5movie.presentation.search.adapter.SearchAdapter
import com.thaariq.happy5movie.presentation.viewmodels.SearchViewModel

class SearchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    private val args : SearchFragmentArgs by navArgs()
    private var latestQuery : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query = args.search
        if (query.isNotEmpty()){
            performSearch(query)
        } else {
            performSearch(latestQuery)
        }
        performSearch(latestQuery)

        binding.edtSearch.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val newQuery = binding.edtSearch.text.toString().trim()
                latestQuery = newQuery
                performSearch(newQuery)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

    }

    private fun setupShowingMovieRV(data: List<SearchItem>){
        binding.rvSearch.apply {
            val searchAdapter = SearchAdapter()
            searchAdapter.setData(data)
            adapter = searchAdapter
        }
    }

    private fun performSearch(query : String){
        viewModel.search(query)
        viewModel.searchMovie.observe(viewLifecycleOwner){
            setupShowingMovieRV(it)
        }
    }

}