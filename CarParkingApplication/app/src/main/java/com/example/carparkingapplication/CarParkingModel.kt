package com.example.carparkingapplication

data class CarParkingModel(
    val carNo: String,
    val phoneNumber: String,
    var slotNumber: Int,
    val checkIn: Long
)