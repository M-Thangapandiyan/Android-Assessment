package com.example.carparkingapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CarParkingAdapter(
    private val carParkingDataBase: CarParkingDataBase) :
    RecyclerView.Adapter<CarParkingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCarNo: AppCompatTextView = itemView.findViewById(R.id.carNo)
        var phoneNumber: AppCompatTextView = itemView.findViewById(R.id.phoneNumber)
        var slotNumber: AppCompatTextView = itemView.findViewById(R.id.slotNumber)
        var checkInTime: AppCompatTextView = itemView.findViewById(R.id.checkInTime)
        var checkOut: AppCompatButton = itemView.findViewById(R.id.checkOut)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.display_car_parking_details, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carParkingDataBase.getCarParkingDetails()?.count ?: 0
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val carParkingDetails = carParkingDataBase.getCarParkingDetails()
        carParkingDetails?.moveToFirst()
        holder.tvCarNo.text =
            carParkingDetails?.getString(carParkingDetails.getColumnIndex(CarParkingDataBase.CAR_NO))
        holder.phoneNumber.text =
            carParkingDetails?.getString(carParkingDetails.getColumnIndex(CarParkingDataBase.PHONE_NUMBER))
        holder.slotNumber.text =
            (carParkingDetails?.getString(carParkingDetails.getColumnIndex(CarParkingDataBase.SLOT_NUMBER)) ?:0).toString()
        val date = carParkingDetails?.getColumnIndex(CarParkingDataBase.CHECK_IN_TIME)
            ?.let { getCurrentDateTime(it.toLong()) }
        holder.checkInTime.text = "${Constants.CHECK_IN_TIME}${date}"
    }

    private fun getCurrentDateTime(checkIn: Long): String {
        val dateTime = SimpleDateFormat(Constants.DATE_PATTERN, Locale.getDefault())
        return dateTime.format(Date(checkIn))
    }
}
