package com.example.fragment

import SecondSampleFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), DataListener , SampleFragment.FirstFragmentListener{
    private lateinit var sampleFragment: SampleFragment
    private lateinit var secondSampleFragment: SecondSampleFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager
        sampleFragment = SampleFragment(this)
        println(" this ==========================================================================${this}")
        secondSampleFragment = SecondSampleFragment()
        fragmentManager.beginTransaction().replace(R.id.first_fragment, sampleFragment).commit()
        fragmentManager.beginTransaction().replace(R.id.second_fragment, secondSampleFragment).commit()
    }

    override fun onListener(data: String) {
        sampleFragment.onListener(data)
    }
    override fun firstFragmentListenerData(data: String) {
        secondSampleFragment.firstFragmentListenerData(data)
    }

}