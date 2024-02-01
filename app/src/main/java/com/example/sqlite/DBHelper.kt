package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "sql example"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "user_table"
        private const val ID = "id"
        const val NAME = "name"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = ("CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY, $NAME TEXT )")
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    fun addName(name: String) {
        val values = ContentValues()
        values.put(NAME, name)
        this.writableDatabase.use {
            it.apply {
                insert(TABLE_NAME, null, values)
            }
        }
    }

    fun getName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
}