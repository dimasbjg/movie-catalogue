package com.example.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private var isLoading: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        val tvShowAdapter = TvShowAdapter()
        tvShowAdapter.notifyDataSetChanged()

        fragmentTvShowBinding.rvTvShow.layoutManager = GridLayoutManager(requireActivity(), 2)
        fragmentTvShowBinding.rvTvShow.adapter = tvShowAdapter


        fragmentTvShowBinding.search.setOnClickListener {
            fragmentTvShowBinding.search.isIconified = false
        }

        fragmentTvShowBinding.search.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) return false
                isLoading = true
                showLoading(isLoading)
                viewModel.setTvShow(query)
                tvShowAdapter.notifyDataSetChanged()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrBlank()) return false
                isLoading = true
                showLoading(isLoading)
                viewModel.setTvShow(query)
                tvShowAdapter.notifyDataSetChanged()
                return false
            }

        })

        viewModel.listTvShow.observe(this, { items ->
            tvShowAdapter.setTvShow(items)
            tvShowAdapter.notifyDataSetChanged()
            isLoading = false
            showLoading(isLoading)
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
        else fragmentTvShowBinding.progressBar.visibility = View.INVISIBLE
    }

}