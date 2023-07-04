package com.thaariq.happy5movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thaariq.happy5movie.databinding.FragmentDetailBinding
import com.thaariq.happy5movie.presentation.MovieViewModel
import com.thaariq.happy5movie.utils.textOnParentheses
import kotlin.math.roundToInt

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding

    private val viewModel by viewModels<MovieViewModel>()

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
        }
    }
}