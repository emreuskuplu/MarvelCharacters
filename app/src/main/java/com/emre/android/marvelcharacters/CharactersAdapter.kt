package com.emre.android.marvelcharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emre.android.marvelcharacters.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharactersAdapter(
    private var characterList: MutableList<Character> = mutableListOf(),
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    fun setList(characterList: List<Character>) {
        this.characterList.addAll(characterList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater)

        return CharactersViewHolder(binding) {
            onClickListener(it)
        }
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val thumbnail = characterList[position].thumbnail.path
        val extension = ".${characterList[position].thumbnail.extension}"

        Picasso.get()
            .load(thumbnail + extension)
            .fit()
            .into(holder.binding.characterIV)
        holder.binding.let {
            it.characterTV.text = characterList[position].name
            it.characterIV.contentDescription = characterList[position].name
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class CharactersViewHolder(
        val binding: ItemCharacterBinding,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.characterIV.setOnClickListener {
                onItemClicked(characterList[adapterPosition].id)
            }
        }
    }
}