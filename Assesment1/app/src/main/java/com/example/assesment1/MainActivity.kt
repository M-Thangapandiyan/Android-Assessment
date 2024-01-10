package com.example.assesment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recylerView : RecyclerView = findViewById(R.id.rv_text)
        recylerView.layoutManager  = LinearLayoutManager(this)
        recylerView.adapter = SampleAdapter()
    }
}