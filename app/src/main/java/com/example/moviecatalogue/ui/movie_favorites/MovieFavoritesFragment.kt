package com.example.moviecatalogue.ui.movie_favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.databinding.FragmentMovieFavoritesBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class MovieFavoritesFragment : Fragment() {

    private lateinit var binding: FragmentMovieFavoritesBinding
    private lateinit var viewModel: MovieFavoritesViewModel
    private lateinit var adapter: MovieFavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavoritesViewModel::class.java]
        }

        adapter = MovieFavoritesAdapter()

        binding.rvMovie.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.adapter = adapter

        viewModel.getMoviesFavorites().observe(viewLifecycleOwner, { movies ->
            movies?.let { movieList ->
                binding.progressBar.visibility = View.GONE
                adapter.submitList(movieList)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}