package com.example.carparkingapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputLayout

class CarDetailsActivity : AppCompatActivity() {
    private lateinit var btnSubmit: AppCompatButton
    private lateinit var tvCarNo: TextInputLayout
    private lateinit var tvPhoneNumber: TextInputLayout
    private var isAllFieldsChecked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_details)
        btnSubmit = findViewById(R.id.submitButton)
        tvCarNo = findViewById(R.id.tvCarNo)
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber)
        btnSubmit.setOnClickListener {
            val carNo = tvCarNo.editText?.text.toString()
            val phoneNumber = tvPhoneNumber.editText?.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            submitForm(carNo, phoneNumber)
        }
    }

    private fun submitForm(carNo: String, phoneNumber: String) {
        isAllFieldsChecked = checkAllFields(carNo, phoneNumber)
        if (isAllFieldsChecked) {
            intent.putExtra(Constants.CAR_NO, carNo)
            intent.putExtra(Constants.PHONE_NUMBER, phoneNumber)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun checkAllFields(carNo: String, phoneNumber: String): Boolean {
        if (carNo.isEmpty()) {
            errorMessage(Constants.CAR_NUMBER_FIELD_ERROR_MESSAGE)
            return false
        } else if (phoneNumber.length < 10) {
            errorMessage(Constants.PHONE_NUMBER_ERROR_MESSAGE)
            return false
        }
        return true
    }

    private fun errorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}