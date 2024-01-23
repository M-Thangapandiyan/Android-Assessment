package com.example.retrofit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() ,UserListener{

    private lateinit var recyclerView: RecyclerView

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userApi = RetrofitHelper.getInstance().create(UserApi::class.java)

//        GlobalScope.launch(Dispatchers.Main) {
//            val result = userApi.getUser()
//            recyclerView = findViewById(R.id.recyclerView)
//            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//            val adapter = UserAdapter(result, this@MainActivity) { selectedItem ->
//                val intent = Intent(this@MainActivity, TextExpandableActivity::class.java)
//                intent.putExtra("title", selectedItem.title)
//                intent.putExtra("body", selectedItem.body)
//                startActivity(intent)
//            }
//            recyclerView.adapter = adapter
//            adapter.notifyDataSetChanged()
//        }



        GlobalScope.launch(Dispatchers.Main) {
            val result = userApi.getUser()
            recyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            val adapter = UserAdapter(result, this@MainActivity,this@MainActivity)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    override fun onClick(user: User) {
        val intent = Intent(this,  TextExpandableActivity::class.java)
        intent.putExtra("title", user.title)
            intent.putExtra("body", user.body)
        startActivity(intent)
    }
}








