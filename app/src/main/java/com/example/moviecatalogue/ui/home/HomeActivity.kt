package com.example.moviecatalogue.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityHomeBinding
import com.example.moviecatalogue.ui.favorites.FavoritesActivity
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLE = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        activityHomeBinding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(
            activityHomeBinding.tabs,
            activityHomeBinding.viewPager
        ) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_favorites -> {
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

}