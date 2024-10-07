package com.example.apiresponse.adaptor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.apiresponse.fragment.ArchiveFragment
import com.example.apiresponse.fragment.FavouriteFragment
import com.example.apiresponse.fragment.HomeFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf(
        HomeFragment(),
        FavouriteFragment(),
        ArchiveFragment()
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }


    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}