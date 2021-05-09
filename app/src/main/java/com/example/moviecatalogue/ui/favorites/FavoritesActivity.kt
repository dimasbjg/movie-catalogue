package com.example.moviecatalogue.ui.favorites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityFavoritesBinding
import com.example.moviecatalogue.ui.home.SectionPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding

    companion object {
        @StringRes
        private val TAB_TITLE = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = FavoritesSectionPagerAdapter(this)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tabs, position ->
            tabs.text = resources.getString(TAB_TITLE[position])
        }.attach()
    }
}