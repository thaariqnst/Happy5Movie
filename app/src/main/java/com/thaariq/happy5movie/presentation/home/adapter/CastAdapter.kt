package com.thaariq.happy5movie.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thaariq.happy5movie.BuildConfig
import com.thaariq.happy5movie.data.responses.CastItem
import com.thaariq.happy5movie.databinding.RowItemCastBinding
import com.thaariq.happy5movie.utils.getCastInitials
import com.thaariq.happy5movie.utils.textOnParentheses

class CastAdapter : RecyclerView.Adapter<CastAdapter.MyViewHolder>() {

    private val listCast = ArrayList<CastItem>()

    class MyViewHolder(val binding: RowItemCastBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyViewHolder(
        RowItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getCast(holder, position)
    }

    override fun getItemCount() = listCast.size

    private fun getCast(holder : MyViewHolder, position: Int){
        val castItem = listCast[position]
        val castInitial = getCastInitials(castItem.name)
        holder.binding.apply {
            tvCast.text = castItem.name
            tvCastInitials.text = textOnParentheses(castInitial)
            tvCastChar.text = castItem.character
            Glide.with(imgCast.context)
                .load(BuildConfig.IMG_BASE_URL + castItem.profilePath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgCast)
        }
    }

    fun setData(data : List<CastItem>){
        listCast.clear()
        listCast.addAll(data)
    }
}