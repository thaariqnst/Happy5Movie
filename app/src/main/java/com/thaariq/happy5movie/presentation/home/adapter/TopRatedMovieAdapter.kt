package com.thaariq.happy5movie.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thaariq.happy5movie.BuildConfig
import com.thaariq.happy5movie.data.responses.TopRatedMovie
import com.thaariq.happy5movie.databinding.RowItemMovieBinding
import com.thaariq.happy5movie.presentation.home.HomeFragmentDirections

class TopRatedMovieAdapter : RecyclerView.Adapter<TopRatedMovieAdapter.MyViewHolder>() {

    private var listTopRated = ArrayList<TopRatedMovie>()

    class MyViewHolder(val binding: RowItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyViewHolder (
        RowItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getTopRatedMovie(holder, position)
    }

    override fun getItemCount() = listTopRated.size

    private fun getTopRatedMovie(holder : MyViewHolder, position: Int){
        val topRatedItem = listTopRated[position]
        holder.binding.apply {
            tvTitle.text = topRatedItem.title
            Glide.with(imgMovie.context)
                .load(BuildConfig.IMG_BASE_URL + topRatedItem.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgMovie)
        }

        holder.itemView.setOnClickListener {
            val movieIid = topRatedItem.id
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movieIid!!)
            it.findNavController().navigate(action)
        }
    }

    fun setData(data : List<TopRatedMovie>){
        listTopRated.clear()
        listTopRated.addAll(data)
    }
}