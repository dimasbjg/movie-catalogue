package com.example.moviecatalogue.ui.tvshow_favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.databinding.FragmentTvShowFavoritesBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFavoritesFragment : Fragment() {

    private lateinit var binding: FragmentTvShowFavoritesBinding
    private lateinit var viewModel: TvShowFavoritesViewModel
    private lateinit var adapter: TvShowFavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowFavoritesViewModel::class.java]
        }

        adapter = TvShowFavoritesAdapter()

        binding.rvMovie.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.adapter = adapter

        viewModel.getTvShowFavorites().observe(viewLifecycleOwner, { tvShows ->
            tvShows?.let { tvShowList ->
                binding.progressBar.visibility = View.GONE
                adapter.submitList(tvShowList)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}