package com.example.carparkingapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CarParkingDataBase(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION), CarParking {

    companion object {
        private const val DATABASE_NAME = "CARPARKINGDETAILS"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "car_parking"
        const val ID = "id"
        const val CAR_NO = "car_no"
        const val PHONE_NUMBER = "phone_number"
        const val SLOT_NUMBER = "slot_number"
        const val CHECK_IN_TIME = "check_in_time"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = ("CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAR_NO TEXT, $PHONE_NUMBER TEXT, $SLOT_NUMBER INTEGER, $CHECK_IN_TIME INTEGER )")
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    override suspend fun addCarParkingDetails(carParkingModel: CarParkingModel) {
        val value = ContentValues()
        with(value) {
            put(CAR_NO, carParkingModel.carNo)
            put(PHONE_NUMBER, carParkingModel.phoneNumber)
            put(SLOT_NUMBER, carParkingModel.slotNumber)
            put(CHECK_IN_TIME, carParkingModel.checkIn)
        }
        val writeDataBase = this.writableDatabase
        writeDataBase.insert(TABLE_NAME, null, value)
    }

    override suspend fun getCarParkingDetails(): MutableList<CarParkingModel> {
        val carParkingList = mutableListOf<CarParkingModel>()
        val readDataBase = this.readableDatabase
        val result =
            readDataBase.rawQuery(" SELECT * FROM $TABLE_NAME  ORDER BY  $SLOT_NUMBER ASC  ", null)
        if (result.moveToFirst()) {
            do {
                val carNo = result.getString(result.getColumnIndexOrThrow(CAR_NO))
                val phoneNumber = result.getString(result.getColumnIndexOrThrow(PHONE_NUMBER))
                val slotNumber = result.getString(result.getColumnIndexOrThrow(SLOT_NUMBER)).toInt()
                val checkIn = result.getString(result.getColumnIndexOrThrow(CHECK_IN_TIME)).toLong()
                val carParkingModel = CarParkingModel(carNo, phoneNumber, slotNumber, checkIn)
                carParkingList.add(carParkingModel)
            } while (result.moveToNext())
        }
        return carParkingList
    }

    override suspend fun getAvailableCarParkingDetails(): MutableList<Int> {
        val carParkingSlotList = mutableListOf<Int>()
        val readDataBase = this.readableDatabase
        val result =
            readDataBase.rawQuery(
                " SELECT $SLOT_NUMBER FROM $TABLE_NAME ORDER BY  $SLOT_NUMBER ASC ",
                null
            )
        if (result.moveToFirst()) {
            do {
                val slotNumber = result.getString(result.getColumnIndexOrThrow(SLOT_NUMBER)).toInt()
                carParkingSlotList.add(slotNumber)
            } while (result.moveToNext())
        }
        return carParkingSlotList
    }

    override suspend fun remove(slotNumber: Int) {
        val writeDataBase = this.writableDatabase
        val query = "DELETE FROM $TABLE_NAME WHERE SLOT_NUMBER = $slotNumber"
        writeDataBase.execSQL(query)
    }
}