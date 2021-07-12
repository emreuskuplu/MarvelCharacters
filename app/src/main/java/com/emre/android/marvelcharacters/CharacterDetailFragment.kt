package com.emre.android.marvelcharacters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.emre.android.marvelcharacters.databinding.FragmentCharacterDetailBinding
import com.squareup.picasso.Picasso
import java.lang.Exception

class CharacterDetailFragment : Fragment() {
    private val args: CharacterDetailFragmentArgs by navArgs()
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding
        get() = _binding ?: throw Exception("FragmentCharactersBinding is not found")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val gridLayoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        val comicsAdapter = ComicsAdapter()
        binding.comicsRV.layoutManager = gridLayoutManager
        binding.comicsRV.adapter = comicsAdapter
        mainViewModel.fetchCharacterDetailData(args.characterId)
        mainViewModel.fetchCharacterComicsData(args.characterId)
        mainViewModel.characterDetailLiveData.observe(viewLifecycleOwner, { listCharacter ->
            val character = listCharacter[0]
            val thumbnail = character.thumbnail.path
            val extension = ".${character.thumbnail.extension}"
            Picasso.get()
                .load(thumbnail + extension)
                .fit()
                .into(binding.characterIV)
            binding.characterName.text = character.name
            if (character.description != "") {
                binding.characterDescriptionTV.text = character.description
            } else {
                binding.characterDescriptionTV.text = resources.getString(R.string.no_content)
            }
        })
        mainViewModel.characterComicsLiveData.observe(viewLifecycleOwner, { comicList ->
            comicsAdapter.setList(comicList)
        })



        binding.backIB.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}