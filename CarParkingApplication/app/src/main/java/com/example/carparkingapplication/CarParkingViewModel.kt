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

    fun setListOfCarParkingDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            carParkingDetails.postValue(carParkingRepository.getCarParkingDetails())
        }
    }

    fun getCarDetailLiveData(): MutableLiveData<List<CarParkingModel>> {
        return carParkingDetails
    }


    fun remove(slotNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            carParkingRepository.remove(slotNumber)
        }
    }
}