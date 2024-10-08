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

class HomeFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializing the UserAdapter
        userAdapter = UserAdapter(
            users = emptyList(),
            onFavoriteToggle = { user ->
                userViewModel.toggleFavorite(user)
                Toast.makeText(requireContext(), "${user.name} marked as favorite", Toast.LENGTH_SHORT).show()
            },
            archiveUser = { user ->
                userViewModel.archiveUser(user) // Handle archiving
                Toast.makeText(requireContext(), "${user.name} archived", Toast.LENGTH_SHORT).show()
            },
            onRenameUser = { user, newName -> userViewModel.renameUser(user, newName) }
        )

        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        // Observe the users from the ViewModel
        userViewModel.users.observe(viewLifecycleOwner) { users ->
            userAdapter.updateUsers(users)
        }

        // Fetch users from the API
        userViewModel.fetchUsers()
        userViewModel.fetchAllUsers()
    }
}