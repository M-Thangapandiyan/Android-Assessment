package com.example.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
class ViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        println("create fragment ======================================================== $position")
      return  when (position) {
            0 -> {
                FragmentOne()
            }
            1 -> {
                FragmentTwo()
            }
            2 -> {
                FragmentThree()
            }
            3 -> {
                FragmentFour()
            }
            4 -> {
                FragmentFive()
            }
            else -> {
                FragmentOne()
            }
        }
    }
}
