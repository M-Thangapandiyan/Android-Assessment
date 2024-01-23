package com.example.retrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class TextExpandableActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_expandable)
        val title = findViewById<TextView>(R.id.tvTitle)
        val body = findViewById<TextView>(R.id.tvBody)

        val getTitle = intent.getStringExtra("title")
        val getBody = intent.getStringExtra("body")

        title.text ="Tittle : $getTitle"
        body.text = "Body : $getBody"
    }
}