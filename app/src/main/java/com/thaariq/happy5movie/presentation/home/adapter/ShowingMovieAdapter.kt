package com.thaariq.happy5movie.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thaariq.happy5movie.BuildConfig
import com.thaariq.happy5movie.data.responses.ShowingMovie
import com.thaariq.happy5movie.databinding.RowItemMovieBinding

class ShowingMovieAdapter : RecyclerView.Adapter<ShowingMovieAdapter.MyViewHolder>() {

    private var listShowing = ArrayList<ShowingMovie>()

    class MyViewHolder(val binding: RowItemMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyViewHolder(
        RowItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getShowingMovie(holder, position)
    }

    override fun getItemCount() = listShowing.size

    private fun getShowingMovie(holder : MyViewHolder, position: Int){
        val showingMovieItem = listShowing[position]
        holder.binding.apply {
            tvTitle.text = showingMovieItem.title
            Glide.with(imgMovie)
                .load(BuildConfig.IMG_BASE_URL + showingMovieItem.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgMovie)
        }
    }

    fun setData(data : List<ShowingMovie>){
        listShowing.clear()
        listShowing.addAll(data)
    }
}