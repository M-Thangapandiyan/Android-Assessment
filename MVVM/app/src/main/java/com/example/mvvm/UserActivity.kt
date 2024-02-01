package com.example.mvvm

import android.annotation.SuppressLint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class UserActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserActivityViewModel
    private lateinit var tv_title: TextView
    private lateinit var tv_body: TextView
    private lateinit var tv_userId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        tv_userId = findViewById(R.id.tv_userId)
        tv_body = findViewById(R.id.tvBody)
        tv_title = findViewById(R.id.tvTitle)

        val getId = intent.getIntExtra("id", 0)

        userViewModel = ViewModelProvider(this)[UserActivityViewModel::class.java]
        if (getId != 0) {
            userViewModel.getUserId(getId)
        }
        getUser()
        textDesign()
    }

    private fun getUser() {
        userViewModel.setUserById().observe(this, Observer {
            if (it.success != null) {
                tv_userId.text = this.getString(R.string.userId, it.success.userId)
                tv_title.text = this.getString(R.string.userTitle, it.success.title)
                tv_body.text = this.getString(R.string.userBody, it.success.body)
            }
            if (it.error != null) {
                Toast.makeText(this, it.error, Toast.LENGTH_SHORT)
            }
        })
    }

    private fun textDesign() {
        val typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        tv_userId.typeface = typeface
        tv_title.typeface = typeface
        tv_body.typeface = typeface
    }
}
