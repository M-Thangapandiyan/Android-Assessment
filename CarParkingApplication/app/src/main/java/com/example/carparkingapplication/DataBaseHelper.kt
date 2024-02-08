package com.example.carparkingapplication

import android.content.Context

object DataBaseHelper {
    private var carParkingDataBase: CarParkingDataBase? = null
    fun getInstance(context: Context): CarParkingDataBase {
        if (carParkingDataBase == null) {
            carParkingDataBase = CarParkingDataBase(context, null)
        }
        return carParkingDataBase as CarParkingDataBase
    }
}
