package com.emre.android.marvelcharacters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.emre.android.marvelcharacters.databinding.FragmentCharactersBinding
import java.lang.Exception

class CharactersFragment : Fragment(R.layout.fragment_characters) {
    private var _binding: FragmentCharactersBinding? = null
    private val binding
        get() = _binding ?: throw Exception("FragmentCharactersBinding is not found")
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val gridLayoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        val scrollListener = CharactersScrollListener(viewModel)
        val charactersAdapter = CharactersAdapter {
            val action = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.charactersRV.let {
            it.adapter = charactersAdapter
            it.layoutManager = gridLayoutManager
            it.addOnScrollListener(scrollListener)
        }
        viewModel.let {
            it.subscribeCharactersData(scrollListener.updateDataObservable)
            it.charactersLiveData.observe(viewLifecycleOwner, { listCharacter ->
                charactersAdapter.setList(listCharacter)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.charactersLiveData.value = viewModel.characterList
        _binding = null
    }
}