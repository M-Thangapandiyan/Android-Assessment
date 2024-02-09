package com.example.carparkingapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarDetailsViewModel(application: Application) : AndroidViewModel(application) {


    private var carParkingRepository = CarParkingRepository(application)
    fun addCarParkingDetails(carParkingModel: CarParkingModel) {
        viewModelScope.launch(Dispatchers.IO) {
            carParkingModel.slotNumber = getNextAvailable()
            carParkingRepository.addCarParkingDetails(carParkingModel)
        }
    }

    private suspend fun getNextAvailable(): Int {
        val list = carParkingRepository.getAvailableCarParkingDetails()
        list.forEachIndexed { index, slotNumber ->
            if (slotNumber != index + 1) {
                return index + 1
            }
        }
        return list.size + 1
    }
}

