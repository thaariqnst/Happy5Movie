package com.thaariq.happy5movie.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thaariq.happy5movie.data.responses.GenreItem
import com.thaariq.happy5movie.databinding.RowItemCategoryBinding
import java.util.ArrayList

class HomeGenreAdapter : RecyclerView.Adapter<HomeGenreAdapter.MyViewHolder>() {

    private var listGenre = ArrayList<GenreItem>()

    class MyViewHolder (val binding: RowItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyViewHolder (
        RowItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getHomeGenre(holder, position)
    }

    override fun getItemCount() = listGenre.size

    private fun getHomeGenre(holder : MyViewHolder, position: Int){
        val genreItem = listGenre[position]
        holder.binding.tvGenre.text = genreItem.name

    }

    fun setData(data : List<GenreItem>){
        listGenre.clear()
        listGenre.addAll(data)
    }
}