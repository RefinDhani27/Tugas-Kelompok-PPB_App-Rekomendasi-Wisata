package com.refin.aplikasikelompok.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.refin.aplikasikelompok.HomeFragment
import com.refin.aplikasikelompok.ProfilFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val jumlah_menu = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> { return HomeFragment()}
            1 -> { return ProfilFragment()}
            else -> { return HomeFragment()}
        }
    }

    override fun getItemCount(): Int {
        return jumlah_menu
    }
}