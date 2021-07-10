package com.emre.android.marvelcharacters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.emre.android.marvelcharacters.databinding.FragmentCharactersBinding
import java.lang.Exception

class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding
        get() = _binding ?: throw Exception("FragmentCharactersBinding is not found")

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
        val gridLayoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        val charactersAdapter = CharactersAdapter()
        binding.charactersRV.adapter = charactersAdapter
        binding.charactersRV.layoutManager = gridLayoutManager
    }

    companion object {
        fun newInstance() = CharactersFragment()
    }
}