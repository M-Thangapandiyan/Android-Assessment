package com.example.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SampleFragment : Fragment(R.layout.fragment_sample) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("================================================================================= onAttach ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("==============================================================================  onCreate ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("==============================================================================  onCreateView ")
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("==============================================================================  onViewCreated ")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        println("==============================================================================  onCreateView ")
    }

    override fun onStart() {
        super.onStart()
        println("==============================================================================  onStart ")
    }

    override fun onResume() {
        super.onResume()
        println("==============================================================================  onResume ")
    }

    override fun onPause() {
        super.onPause()
        println("==============================================================================  onPause ")
    }

    override fun onStop() {
        super.onStop()
        println("==============================================================================  onStop ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("==============================================================================  onSaveInstanceState ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("==============================================================================  onDestroyView ")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("==============================================================================  onDestroy ")
    }
}
