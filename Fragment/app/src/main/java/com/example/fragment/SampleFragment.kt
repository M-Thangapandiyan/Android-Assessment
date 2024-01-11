package com.example.fragment

import SecondSampleFragment
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class SampleFragment : Fragment(), DataListener  {

    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private var dataPassListener: DataListener? = null

    interface DataPassListener {
        fun firstFragmentListener(data: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sample, container, false)
        editText = view.findViewById(R.id.et_first_fragment)
        textView = view.findViewById(R.id.tv_first_fragment)

        val btnPassData = view.findViewById<Button>(R.id.btnSubmit)
        btnPassData.setOnClickListener {
            val data = editText.text.toString()
            dataPassListener?.onListener(data)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPassListener = context as DataListener
    }

    override fun onListener(data: String) {
        editText.setText(data)
    }




























    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("==============================================================================  onCreate ")
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("==============================================================================  onViewCreated ")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        println("==============================================================================  onViewStateRestored ")
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
