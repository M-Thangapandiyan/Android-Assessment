package com.example.carparkingapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout

class CarDetailsActivity : AppCompatActivity() {

    private lateinit var carDetailsViewModel: CarDetailsViewModel
    private lateinit var carParkingModel: CarParkingModel
    private lateinit var btnSubmit: AppCompatButton
    private lateinit var tvCarNo: TextInputLayout
    private lateinit var tvPhoneNumber: TextInputLayout
    private var isAllFieldsChecked = false
    private var slotNo: Int = 0
    private var checkIn: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_details)
        carDetailsViewModel = ViewModelProvider(this)[CarDetailsViewModel::class.java]
        btnSubmit = findViewById(R.id.submitButton)
        tvCarNo = findViewById(R.id.tvCarNo)
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber)
        checkIn = System.currentTimeMillis()
        btnSubmit.setOnClickListener {
            val carNo = tvCarNo.editText?.text.toString()
            val phoneNumber = tvPhoneNumber.editText?.text.toString()
            isAllFieldsChecked = checkAllFields(carNo, phoneNumber)
            if (isAllFieldsChecked) {
                carParkingModel = CarParkingModel(carNo, phoneNumber, slotNo, checkIn)
                carDetailsViewModel.addCarParkingDetails(carParkingModel)
                setResult(Activity.RESULT_OK)
                finish()
            }
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


























