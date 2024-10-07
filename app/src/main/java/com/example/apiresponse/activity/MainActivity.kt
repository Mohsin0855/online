package com.example.apiresponse.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiresponse.R
import com.example.apiresponse.adaptor.UserAdapter
import com.example.apiresponse.adaptor.ViewPagerAdapter
import com.example.apiresponse.databinding.ActivityMainBinding
import com.example.apiresponse.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()
   // private lateinit var userAdapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the RecyclerView and adapter
        //setupRecyclerView()

        // Observe ViewModel data
       // observeViewModel()

        // Set tab layout and viewpager
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        // link tablyout and pager view
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Home"
                1 -> tab.text = "Favourite"
                2 -> tab.text = "Archive"
            }
        }.attach()

        // Fetch users
        userViewModel.fetchUsers()

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.main.id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
//    private fun setupRecyclerView() {
//        userAdapter = UserAdapter(emptyList()) // Initialize with empty list first
//        binding.recycler.layoutManager = LinearLayoutManager(this)
//        binding.recycler.setHasFixedSize(true)
//        binding.recycler.adapter = userAdapter
//    }
//
//    private fun observeViewModel() {
//        userViewModel.users.observe(this) { users ->
//            if (users != null) {
//                Log.d("MainActivity", "Received users: $users")
//                userAdapter = UserAdapter(users) // Pass the new user list to the adapter
//                binding.recycler.adapter = userAdapter // Re-set the adapter with new data
//            } else {
//                Log.d("MainActivity", "No users received")
//            }
//        }
//    }
}
