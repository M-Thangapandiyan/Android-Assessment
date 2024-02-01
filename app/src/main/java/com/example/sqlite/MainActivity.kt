package com.example.sqlite

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var tvName: TextView
    private lateinit var btnAdd: AppCompatButton
    private lateinit var btnPrint: AppCompatButton

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.et_name)
        tvName = findViewById(R.id.tv_name)
        btnAdd = findViewById(R.id.btn_add)
        btnPrint = findViewById(R.id.btn_print)

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val dbHelper = DBHelper(this, null)
            dbHelper.apply {
                addName(name)
                Toast.makeText(this@MainActivity, "$name added to database", Toast.LENGTH_SHORT)
                    .show()
                etName.text.clear()
            }
        }

        btnPrint.setOnClickListener {
            val dbHelper = DBHelper(this, null)
            val result = dbHelper.getName()
            result?.moveToFirst()
            tvName.append(result?.getString(result.getColumnIndex(DBHelper.NAME)))
            result.use {
                result?.let {
                    while (it.moveToNext()) {
                        tvName.append(result.getString(result.getColumnIndex(DBHelper.NAME)) + "\n")
                    }
                }
            }
        }
    }
}