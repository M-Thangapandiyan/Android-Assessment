package com.example.carparkingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), CarParkingDialogFragment.CarParkingDialogListener {

    private lateinit var carParkingViewModel: CarParkingViewModel
    private lateinit var carParkingAdapter: CarParkingAdapter
    private lateinit var btnSubmit: FloatingActionButton
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        carParkingViewModel = ViewModelProvider(this)[CarParkingViewModel::class.java]
        initView()
    }

    private val carParkingInterface: CarParkingInterFace = object : CarParkingInterFace {
        override fun onClick(view: CarParkingModel) {
            val carParkingDialogFragment = CarParkingDialogFragment()
            val bundle = Bundle()
            bundle.putString(Constants.CAR_NO, view.carNo)
            bundle.putString(Constants.USER_PHONE_NUMBER, view.phoneNumber)
            bundle.putInt(Constants.SLOT_NO, view.slotNumber)
            bundle.putLong(Constants.CHECK_IN, view.checkIn)
            carParkingDialogFragment.arguments = bundle
            carParkingDialogFragment.show(supportFragmentManager, Constants.CAR_PARKING_DETAILS)
        }
    }

    private fun initView() {
        btnSubmit = findViewById(R.id.floating_action_button)
        recyclerView = findViewById(R.id.recyclerView)
        btnSubmit.setOnClickListener {
            val intent = Intent(this, CarDetailsActivity::class.java)
            resultLauncher.launch(intent)
//            startActivity(intent)
        }
        carParkingAdapter = CarParkingAdapter(carParkingInterface)
        recyclerView.adapter = carParkingAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        getCarList()
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                getCarList()
            }
        }

    override fun btnClicked(slotNumber: Int) {
        carParkingViewModel.remove(slotNumber)
        getCarList()
    }

    private fun getCarList() {
        carParkingViewModel.setListOfCarParkingDetails()
        carParkingViewModel.getCarDetailLiveData().observe(this, Observer {
            this.carParkingAdapter.setCarList(it)
        })
    }
}

