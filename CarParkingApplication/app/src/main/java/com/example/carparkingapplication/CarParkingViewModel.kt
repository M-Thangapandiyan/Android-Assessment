package com.example.carparkingapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CarParkingViewModel(application: Application) : AndroidViewModel(application) {

    private var carParkingDataBase = DataBase(application, null)
    private var carParkingSlotList = mutableListOf<Int>()

    fun addCarParkingDetails(carParkingModel: CarParkingModel) {
        val availableSlot = getNextAvailable()
        if (availableSlot == -1) {
            carParkingModel.slotNumber = carParkingSlotList.size + 1
        } else {
            carParkingModel.slotNumber = availableSlot
        }
        carParkingDataBase.addCarParkingDetails(carParkingModel)
    }

    fun getCarDetails(): MutableList<CarParkingModel> {
        return carParkingDataBase.getCarParkingDetails()
    }

    private fun getNextAvailable(): Int {
        this.carParkingSlotList = carParkingDataBase.getAvailableCarParkingDetails()
        carParkingSlotList.forEachIndexed { index, slotNumber ->
            if (slotNumber != index + 1) {
                return index + 1
            }
        }
        return -1
    }

    fun remove(slotNumber: Int) {
        carParkingDataBase.remove(slotNumber)
    }

}