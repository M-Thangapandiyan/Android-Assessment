package com.example.viewpager

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragmentInstances = SparseArray<Fragment>()
    override fun getCount() : Int {
        return 5
    }

    override fun getItem(postion : Int) : Fragment {

        val existingFragment = fragmentInstances.get(postion)
        if (existingFragment != null) {
            return existingFragment
        }

        val newFragment = when (postion) {
            0 -> {
                FragmentOne.newInstance()
            }
            1 -> {
                FragmentTwo.newInstance()
            }
            2 -> {
                FragmentThree.newInstance()
            }
            3 -> {
                FragmentFour.newInstance()
            }
            4 -> {
                FragmentFive.newInstance()
            }
            else -> {
                FragmentOne.newInstance()
            }
        }
        fragmentInstances.put(postion, newFragment)
        return newFragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Tab 1 "
            }
            1 -> {
                return "Tab 2 "
            }
            2 -> {
                return "Tab 3 "
            }
            3 -> {
                return "Tab 4 "
            }
            4 -> {
                return "Tab 5 "
            }
        }
        return super.getPageTitle(position)
    }
}

