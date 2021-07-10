package com.emre.android.marvelcharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emre.android.marvelcharacters.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharactersAdapter(private var characterList: List<Character> = listOf()) :
    RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    fun setList(characterList: List<Character>) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater)

        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val characterUrl = characterList[position].thumbnail.path
        Picasso.get()
            .load(Utils.appendExtensionJpgToThumbnailUrl(characterUrl))
            .fit()
            .into(holder.binding.characterIV)
        holder.binding.characterTV.text = characterList[position].name
        holder.binding.characterIV.contentDescription = characterList[position].name
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    class CharactersViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)
}