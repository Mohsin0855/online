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

class ArchiveFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_archive, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        userAdapter = UserAdapter(
            users = emptyList(),
            onFavoriteToggle = {user ->
                userViewModel.toggleFavorite(user)},
            archiveUser = { user ->
                Toast.makeText(requireContext(), "${user.name} is archived", Toast.LENGTH_SHORT).show()
            },
            onRenameUser = { user, newName -> userViewModel.renameUser(user, newName) }
        )

        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        userViewModel.archivedUsers.observe(viewLifecycleOwner) { archivedUsers ->
            if (!archivedUsers.isNullOrEmpty()) {
                userAdapter.updateUsers(archivedUsers)
            } else {
                Toast.makeText(requireContext(), "No archived users found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}