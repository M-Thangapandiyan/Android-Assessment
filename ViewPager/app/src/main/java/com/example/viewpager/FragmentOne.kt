package com.example.viewpager


import androidx.fragment.app.Fragment
class FragmentOne : Fragment(R.layout.fragment_one) {
    companion object {
        fun newInstance(): FragmentOne {
            return FragmentOne()
        }
    }
}