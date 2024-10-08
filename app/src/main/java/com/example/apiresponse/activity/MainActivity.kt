package com.example.apiresponse.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.apiresponse.R
import com.example.apiresponse.adaptor.ViewPagerAdapter
import com.example.apiresponse.db.AppDatabase
import com.example.apiresponse.repo.UserRepository
import com.example.apiresponse.util.UserViewModelFactory
import com.example.apiresponse.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    // Expose the ViewModelFactory for Fragments
    lateinit var userViewModelFactory: UserViewModelFactory
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing the database and repository
        val userDao = AppDatabase.getInstance(applicationContext).userDao()
        val repository = UserRepository(userDao)

        // Creating the ViewModelFactory and make it accessible
        userViewModelFactory = UserViewModelFactory(repository)

        // Initializing the ViewModel using the factory
        userViewModel = ViewModelProvider(this, userViewModelFactory)[UserViewModel::class.java]

        // Set up the ViewPager and TabLayout
        setupViewPagerAndTabs()
    }

    private fun setupViewPagerAndTabs() {
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        // Set the adapter for the ViewPager
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Connect the TabLayout with the ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Home"
                1 -> tab.text = "Favourites"
                2 -> tab.text = "Archive"
            }
        }.attach()
    }
}