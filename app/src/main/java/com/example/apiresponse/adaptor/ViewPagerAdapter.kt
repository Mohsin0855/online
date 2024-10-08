package com.example.apiresponse.adaptor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.apiresponse.fragment.ArchiveFragment
import com.example.apiresponse.fragment.FavouriteFragment
import com.example.apiresponse.fragment.HomeFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> FavouriteFragment()
            2 -> ArchiveFragment()
            else -> HomeFragment()
        }
    }
}