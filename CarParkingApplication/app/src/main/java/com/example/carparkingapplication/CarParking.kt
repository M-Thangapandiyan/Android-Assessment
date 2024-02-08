package com.example.carparkingapplication

interface CarParking {

    suspend fun getCarParkingDetails() : MutableList<CarParkingModel>
    suspend fun remove(slotNumber: Int)
    suspend fun addCarParkingDetails(carParkingModel: CarParkingModel)
    suspend fun getAvailableCarParkingDetails(): MutableList<Int>

}