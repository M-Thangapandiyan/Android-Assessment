package com.example.mvvm

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.indeterminateBar)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar.visibility = View.VISIBLE

        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(this)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        getUserData(userAdapter)
    }

    private fun getUserData(userAdapter : UserAdapter) {
        userViewModel.getUserDataFromApi()

        userViewModel.setUserList().observe(this, Observer {

            userAdapter.setUserData(it)
            recyclerView.adapter = userAdapter
            progressBar.visibility = View.INVISIBLE
        })

    }
}

































