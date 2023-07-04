package com.thaariq.happy5movie.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thaariq.happy5movie.data.responses.GenresItem
import com.thaariq.happy5movie.databinding.RowItemCategoryBinding
import java.util.ArrayList

class DetailGenreAdapter : RecyclerView.Adapter<DetailGenreAdapter.MyViewHolder>() {

    val listGenre = ArrayList<GenresItem>()

    class MyViewHolder(val binding: RowItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyViewHolder(
        RowItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getDetailGenre(holder, position)
    }

    override fun getItemCount() = listGenre.size

    private fun getDetailGenre(holder : MyViewHolder, position: Int){
        val genreItem = listGenre[position]
        holder.binding.tvGenre.text = genreItem.name
    }

    fun setData(data : List<GenresItem>){
        listGenre.clear()
        listGenre.addAll(data)
    }
}