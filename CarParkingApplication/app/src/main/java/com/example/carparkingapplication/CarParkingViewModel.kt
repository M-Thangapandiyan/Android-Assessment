package com.example.carparkingapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarParkingViewModel(application: Application) : AndroidViewModel(application) {

    private var carParkingDataBase = CarParkingRepository(application, null)
    private var carParkingSlotList = mutableListOf<Int>()


    private var carParkingDetails = MutableLiveData<List<CarParkingModel>>()

    fun addCarParkingDetails(carParkingModel: CarParkingModel) {
        val availableSlot = getNextAvailable()
        if (availableSlot == -1) {
            carParkingModel.slotNumber = carParkingSlotList.size + 1
        } else {
            carParkingModel.slotNumber = availableSlot
        }
        viewModelScope.launch(Dispatchers.IO) {
            carParkingDataBase.addCarParkingDetails(carParkingModel)
            setListOfCarParkingDetails()
        }
    }

    fun setListOfCarParkingDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            carParkingDetails.postValue(carParkingDataBase.getCarParkingDetails())
        }
    }

    fun getCarDetailLiveData(): MutableLiveData<List<CarParkingModel>> {
        return carParkingDetails
    }

    private fun getNextAvailable(): Int {
        this.carParkingSlotList = carParkingDataBase.getAvailableCarParkingDetails()
        this.carParkingSlotList.forEachIndexed { index, slotNumber ->
            if (slotNumber != index + 1) {
                return index + 1
            }
        }
        return -1
    }

//    private fun getNextAvailable(): Int {
//        viewModelScope.launch(Dispatchers.IO) {
//            carParkingSlotList = carParkingDataBase.getAvailableCarParkingDetails()
//            println("===================================================$carParkingSlotList ")
//        }
//
//        this.carParkingSlotList.forEachIndexed { index, slotNumber ->
//            if (slotNumber != index + 1) {
//                return index + 1
//            }
//        }
//        return -1
//    }

    fun remove(slotNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            carParkingDataBase.remove(slotNumber)
        }
    }






























    private fun getNextAvailable(): Int {
    viewModelScope.launch {
        val a = carParkingDataBase.getCarParkingDetails()
        getNextAvailabl()
    }
        this.carParkingSlotList.forEachIndexed { index, slotNumber ->
            if (slotNumber != index + 1) {
                return index + 1
            }
        }
        return -1

    }

    private suspend fun getNextAvailabl(): Int {
        val carParkingDetails = carParkingDataBase.getCarParkingDetails()
         var car = mutableListOf<Int>()
        for (a in carParkingDetails ) {
            car.add(a.slotNumber)
            println("car ===========================$car")
            println("=================================================slot number = ${a.slotNumber}")
        }
        car.forEachIndexed { index, slotNumber ->
            if (slotNumber != index + 1) {
                return index + 1
            }
        }
        return -1
    }

}