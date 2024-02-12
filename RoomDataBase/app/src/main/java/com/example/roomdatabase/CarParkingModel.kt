package com.example.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CarParkingModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "car_no") val carNo: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String,
    @ColumnInfo(name = "slot_number") val slotNumber: Int,
    @ColumnInfo(name = "check_in") val checkIn: Long
)
