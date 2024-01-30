package com.example.retrofit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), UserListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var userViewModel:UserViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.listOfUsers.observe(this) {
            recyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            val adapter = UserAdapter(it, this@MainActivity, this@MainActivity)
            progressBar.visibility = View.INVISIBLE
            recyclerView.adapter = adapter
        }
    }

    private fun initView() {
        progressBar = findViewById(R.id.indeterminateBar)
        progressBar.visibility = View.VISIBLE
        val userApi = RetrofitHelper.getInstance().create(UserApi::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            val result = userApi.getUser()
            withContext(Dispatchers.Main) {
                userViewModel.setUserList(result)
            }
        } }

    override fun onClick(user: User) {
        val intent = Intent(this, TextExpandableActivity::class.java)
        intent.putExtra("title", user.title)
        intent.putExtra("body", user.body)
        startActivity(intent)
    }
}








