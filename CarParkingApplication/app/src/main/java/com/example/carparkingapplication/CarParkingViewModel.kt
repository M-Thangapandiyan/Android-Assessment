package com.example.carparkingapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarParkingViewModel(application: Application) : AndroidViewModel(application) {

    private var carParkingRepository = CarParkingRepository(application)
    private var carParkingDetails = MutableLiveData<List<CarParkingModel>>()

    fun addCarParkingDetails(carParkingModel: CarParkingModel) {
        viewModelScope.launch(Dispatchers.IO) {
            carParkingModel.slotNumber = getNextAvailable()
            carParkingRepository.addCarParkingDetails(carParkingModel)
            setListOfCarParkingDetails()
        }
    }

    fun setListOfCarParkingDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            carParkingDetails.postValue(carParkingRepository.getCarParkingDetails())
        }
    }

    fun getCarDetailLiveData(): MutableLiveData<List<CarParkingModel>> {
        return carParkingDetails
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

    fun remove(slotNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            carParkingRepository.remove(slotNumber)
        }
    }
}