package com.example.carparkingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var carParkingModel: CarParkingModel
    private val carParkingDataBase = CarParkingDataBase(this, null)
    private lateinit var carParkingAdapter: CarParkingAdapter
    private lateinit var btnSubmit: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var carNo: String
    private lateinit var phoneNumber: String
    private var slotNo: Int = 1
    private var checkIn: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btnSubmit = findViewById(R.id.floating_action_button)
        recyclerView = findViewById(R.id.recyclerView)
        btnSubmit.setOnClickListener {
            val intent = Intent(this, CarDetailsActivity::class.java)
            resultLauncher.launch(intent)
        }
        carParkingAdapter = CarParkingAdapter(carParkingDataBase)
        recyclerView.adapter = carParkingAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                data?.let { data ->
                    carNo = data.getStringExtra(Constants.CAR_NO) ?: Constants.EMPTY_STRING
                    phoneNumber =
                        data.getStringExtra(Constants.PHONE_NUMBER) ?: Constants.EMPTY_STRING
                    checkIn = System.currentTimeMillis()
                    carParkingModel = CarParkingModel(carNo, phoneNumber, slotNo, checkIn)
                    addCarParkingDetails(carParkingModel)
                }
            }
        }

    private fun addCarParkingDetails(carParkingModel: CarParkingModel) {
        carParkingDataBase.addCarParkingDetails(carParkingModel)
    }

}
