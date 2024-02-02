package com.example.carparkingapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CarParkingDataBase(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
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

    fun addCarParkingDetails(carParkingModel: CarParkingModel) {
        val value = ContentValues()
        value.put(CAR_NO, carParkingModel.carNo)
        value.put(PHONE_NUMBER, carParkingModel.phoneNumber)
        value.put(SLOT_NUMBER, carParkingModel.slotNumber + 1)
        value.put(CHECK_IN_TIME, carParkingModel.checkIn)
        val write = this.writableDatabase
        write.insert(TABLE_NAME, null, value)
    }

    fun getCarParkingDetails(): Cursor? {
        val result = this.readableDatabase
        return result.rawQuery(" SELECT * FROM car_parkind " , null)
    }

}