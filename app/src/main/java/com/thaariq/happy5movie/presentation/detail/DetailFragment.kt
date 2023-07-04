package com.thaariq.happy5movie.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thaariq.happy5movie.BuildConfig
import com.thaariq.happy5movie.data.responses.GenresItem
import com.thaariq.happy5movie.databinding.FragmentDetailBinding
import com.thaariq.happy5movie.presentation.detail.adapter.DetailGenreAdapter
import com.thaariq.happy5movie.presentation.viewmodels.DetailViewModel
import com.thaariq.happy5movie.utils.textOnParentheses
import kotlin.math.roundToInt

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding

    private val viewModel by viewModels<DetailViewModel>()

    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.movieId
        //Log.i("MovieID", movieId.toString())

        viewModel.detail(movieId)
        viewModel.movieDetail.observe(viewLifecycleOwner){
            binding.apply {
                tvTitle.text = it.title
                tvSinopsis.text = it.overview
                tvVoteAverage.text = it.voteAverage?.roundToInt().toString()
                tvVoteCount.text = textOnParentheses(it.voteCount.toString())

                Glide.with(imgPoster.context)
                    .load(BuildConfig.IMG_BASE_URL + it.posterPath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPoster)
            }
            setupGenreRV(it.genres!!)
        }
    }

    private fun setupGenreRV(data : List<GenresItem>){
        binding.rvCategory.apply {
            val genreAdapter = DetailGenreAdapter()
            genreAdapter.setData(data)
            adapter = genreAdapter
        }
    }
}