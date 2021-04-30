package com.example.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.data.source.local.MovieEntity
import com.example.moviecatalogue.data.source.remote.response.MovieItems
import com.example.moviecatalogue.databinding.ItemLayoutBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_DETAIL
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_FROM

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    private var listMovie = ArrayList<MovieEntity>()

    fun setMovie(movie: List<MovieEntity>?) {
        if (movie == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movie)
    }

    class MyViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                itemTitle.text = movie.title
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.imgPoster)
                    .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_DETAIL, movie.id)
                    intent.putExtra(EXTRA_FROM, "Movie")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLayoutBinding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemLayoutBinding)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }
}