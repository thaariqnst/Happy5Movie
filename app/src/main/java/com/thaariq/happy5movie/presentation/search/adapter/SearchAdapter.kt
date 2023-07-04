package com.thaariq.happy5movie.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thaariq.happy5movie.BuildConfig
import com.thaariq.happy5movie.data.responses.SearchItem
import com.thaariq.happy5movie.databinding.RowItemMovieBinding
import com.thaariq.happy5movie.presentation.search.SearchFragmentDirections

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    private val listSearch = ArrayList<SearchItem>()

    class MyViewHolder(val binding: RowItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyViewHolder(
        RowItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getSearch(holder, position)
    }

    override fun getItemCount() = listSearch.size

    private fun getSearch(holder : MyViewHolder, position: Int){
        val searchMovieItem = listSearch[position]
        holder.binding.apply {
            tvTitle.text = searchMovieItem.title
            Glide.with(imgMovie)
                .load(BuildConfig.IMG_BASE_URL + searchMovieItem.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgMovie)
        }

        holder.itemView.setOnClickListener {
            val movieId = searchMovieItem.id
            val action = SearchFragmentDirections.actionSearchFragment2ToDetailFragment(movieId!!)
            it.findNavController().navigate(action)
        }
    }

    fun setData(data : List<SearchItem>){
        listSearch.clear()
        listSearch.addAll(data)
    }
}