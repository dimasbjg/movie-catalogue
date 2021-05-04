package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = MovieAdapter()
        adapter.notifyDataSetChanged()

        fragmentMovieBinding.rvMovie.layoutManager = GridLayoutManager(requireActivity(), 2)
        fragmentMovieBinding.rvMovie.adapter = adapter


        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(
            this,
            factory
        )[MovieViewModel::class.java]

        fragmentMovieBinding.btSearch.setOnClickListener {
            val search = fragmentMovieBinding.search.text.toString()
            if (search.isEmpty()) return@setOnClickListener
            isLoading = true
            showLoading(isLoading)
            viewModel.setMovie(search)
            adapter.notifyDataSetChanged()
        }

        viewModel.listMovie.observe(this, { listMovie ->
            adapter.setMovie(listMovie)
            adapter.notifyDataSetChanged()
            isLoading = false
            showLoading(isLoading)
        })
    }


    private fun showLoading(state: Boolean) {
        if (state) fragmentMovieBinding.progressBar.visibility = View.VISIBLE
        else fragmentMovieBinding.progressBar.visibility = View.INVISIBLE
    }

}