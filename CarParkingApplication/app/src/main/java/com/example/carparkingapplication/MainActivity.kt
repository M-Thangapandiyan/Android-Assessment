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
    private lateinit var carParkingModel: CarParkingModel
    private lateinit var carParkingAdapter: CarParkingAdapter
    private lateinit var btnSubmit: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var carNo: String
    private lateinit var phoneNumber: String
    private var slotNo: Int = 0
    private var checkIn: Long = 0

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
                val data: Intent? = result.data
                data?.let { data ->
                    carNo = data.getStringExtra(Constants.CAR_NO) ?: Constants.EMPTY_STRING
                    phoneNumber =
                        data.getStringExtra(Constants.PHONE_NUMBER) ?: Constants.EMPTY_STRING
                    checkIn = System.currentTimeMillis()
                    carParkingModel = CarParkingModel(carNo, phoneNumber, slotNo, checkIn)
                    carParkingViewModel.addCarParkingDetails(carParkingModel)
                    getCarList()
                }
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

