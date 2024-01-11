package com.example.fragment

import SecondSampleFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), DataListener , SampleFragment.DataPassListener {
    private lateinit var sampleFragment: SampleFragment
    private lateinit var secondSampleFragment: SecondSampleFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager
        sampleFragment = SampleFragment()
        secondSampleFragment = SecondSampleFragment()
        fragmentManager.beginTransaction().replace(R.id.first_fragment, SampleFragment()).commit()
        fragmentManager.beginTransaction().replace(R.id.second_fragment, SecondSampleFragment()).commit()
    }

    override fun onListener(data: String) {
        sampleFragment.onListener(data)
    }

    override fun firstFragmentListener(data: String) {
        secondSampleFragment.firstFragmentListener(data)
    }
}