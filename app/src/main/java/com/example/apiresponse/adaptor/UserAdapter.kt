package com.example.apiresponse.adaptor

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiresponse.R
import com.example.apiresponse.data.User
import com.example.apiresponse.databinding.UserBinding

class UserAdapter (private var users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder( val binding: UserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            Glide.with(binding.root.context)
                .load(user.photo)
                .into(binding.profileImageView)

            binding.nameTextView.text = user.name
            binding.emailTextView.text = user.email

            binding.favoriteButton.setOnLongClickListener {
                showPopupMenu(it.context, binding.favoriteButton, user)
                true // Return true to indicate the long press was handled
            }
        }
        private fun showPopupMenu(context: Context, view: View, user: User) {
            val popupMenu = PopupMenu(context, view)
            popupMenu.menuInflater.inflate(R.menu.menu_user_options, popupMenu.menu)

            // Handle menu item clicks
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_rename -> {
                        // Handle rename action
                        Log.d("UserAdapter", "Rename clicked for user: ${user.name}")
                        true
                    }
                    R.id.action_archive -> {
                        // Handle archive action
                        Log.d("UserAdapter", "Archive clicked for user: ${user.name}")
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder{
        val binding = UserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }



    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
        holder.binding.nameTextView.text = user.name
        holder.binding.emailTextView.text = user.email
        Log.d("UserAdapter", "Binding user: ${user.name}")
    }

    override fun getItemCount(): Int = users.size

    // Method to update the user list and notify the adapter
    fun updateUsers(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}