package com.example.apiresponse.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiresponse.R
import com.example.apiresponse.adaptor.UserAdapter
import com.example.apiresponse.viewmodel.UserViewModel

class FavouriteFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView for favorites
        userAdapter = UserAdapter(
            users = emptyList(),
            onFavoriteToggle = { user ->
                userViewModel.toggleFavorite(user)
            },
            archiveUser = { user ->
                Toast.makeText(requireContext(), "${user.name} archived", Toast.LENGTH_SHORT).show()
                userViewModel.archiveUser(user) // Add the logic for archiving the user
            },
            onRenameUser = { user, newName -> userViewModel.renameUser(user, newName) }
        )

        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        // Observe favorite users from the ViewModel
        userViewModel.favoriteUsers.observe(viewLifecycleOwner) { favoriteUsers ->
            if (!favoriteUsers.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "No favorite users found", Toast.LENGTH_SHORT).show()
            }
            userAdapter.updateUsers(favoriteUsers)
        }
    }
}