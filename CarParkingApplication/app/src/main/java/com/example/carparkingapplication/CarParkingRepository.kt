package com.example.carparkingapplication

import android.app.Application

class CarParkingRepository(application: Application) {

    private var carParkingDataBase: CarParkingDataBase = DataBaseHelper.getInstance(application)
    suspend fun addCarParkingDetails(carParkingModel: CarParkingModel) {
        carParkingDataBase.addCarParkingDetails(carParkingModel)
    }

    suspend fun getCarParkingDetails(): List<CarParkingModel> {
        return carParkingDataBase.getCarParkingDetails()
    }

    suspend fun remove(slotNumber: Int) {
        carParkingDataBase.remove(slotNumber)
    }

    suspend fun getAvailableCarParkingDetails(): MutableList<Int> {
        return carParkingDataBase.getAvailableCarParkingDetails()
    }
}