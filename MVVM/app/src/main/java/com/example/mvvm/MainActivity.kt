package com.example.mvvm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), UserListener {

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
        userAdapter = UserAdapter(this, this)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        getUserData(userAdapter)
    }

    private fun getUserData(userAdapter: UserAdapter) {

        userViewModel.getUserDataFromApi()
        userViewModel.setUserList().observe(this, Observer {
            if (it.success != null){
                userAdapter.setUserData(it.success)
                recyclerView.adapter = userAdapter
                progressBar.visibility = View.INVISIBLE
            }

            if (it.error != null){
                Toast.makeText(this,it.error,Toast.LENGTH_SHORT)
            }
        })
    }

    override fun onClick(user: User) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("id", user.id)
        startActivity(intent)
    }
}

































