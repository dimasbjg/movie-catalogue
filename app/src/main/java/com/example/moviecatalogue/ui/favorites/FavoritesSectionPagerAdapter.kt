package com.example.moviecatalogue.ui.favorites

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviecatalogue.ui.movie_favorites.MovieFavoritesFragment
import com.example.moviecatalogue.ui.tvshow_favorites.TvShowFavoritesFragment

class FavoritesSectionPagerAdapter(
    activity: AppCompatActivity
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MovieFavoritesFragment()
            1 -> TvShowFavoritesFragment()
            else -> Fragment()
        }


}