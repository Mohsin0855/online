package com.example.apiresponse.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiresponse.R
import com.example.apiresponse.adaptor.UserAdapter
import com.example.apiresponse.viewmodel.UserViewModel

class HomeFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels() // Use activityViewModels to share the ViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false) // Inflate the fragment layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView
        userAdapter = UserAdapter(emptyList()) // Initialize with an empty list
        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        // Observe the user data from the ViewModel
        userViewModel.users.observe(viewLifecycleOwner) { users ->
            if (users != null) {
                Log.d("HomeFragment", "Received users: $users")
                userAdapter.updateUsers(users) // Update the adapter with new user data
            } else {
                Log.d("HomeFragment", "No users received")
            }
        }
    }
}