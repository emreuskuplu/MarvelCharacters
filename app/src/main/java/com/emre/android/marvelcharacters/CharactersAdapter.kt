package com.emre.android.marvelcharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emre.android.marvelcharacters.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharactersAdapter(private var characterList: List<String> = listOf()) :
    RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    fun setList(characterList: List<String>) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater)

        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val characterUrl = characterList[position]
        if (characterUrl != "") {
            Picasso.get()
                .load(characterUrl)
                .fit()
                .into(holder.binding.characterIV)
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    class CharactersViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)
}