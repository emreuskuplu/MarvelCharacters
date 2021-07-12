package com.emre.android.marvelcharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emre.android.marvelcharacters.databinding.ItemComicBinding
import com.squareup.picasso.Picasso

class ComicsAdapter(
    private var comicList: List<Comic> = listOf()
) : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    fun setList(comicList: List<Comic>) {
        this.comicList = comicList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemComicBinding.inflate(layoutInflater)

        return ComicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val thumbnail = comicList[position].thumbnail.path
        val extension = ".${comicList[position].thumbnail.extension}"
        Picasso.get()
            .load(thumbnail + extension)
            .fit()
            .into(holder.binding.comicIV)
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

    class ComicsViewHolder(val binding: ItemComicBinding) : RecyclerView.ViewHolder(binding.root)
}