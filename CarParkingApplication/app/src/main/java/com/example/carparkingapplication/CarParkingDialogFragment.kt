package com.example.carparkingapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.Locale

class CarParkingDialogFragment : DialogFragment() {
    private lateinit var tvCarNo : AppCompatTextView
    private lateinit var tvPhoneNumber : AppCompatTextView
    private lateinit var tvSlotNo : AppCompatTextView
    private lateinit var tvCheckInTime : AppCompatTextView
    private lateinit var btnOk : AppCompatButton
    private lateinit var priceCalculation : AppCompatTextView
    private var checkIn : Long = 0
    private var dialogListener: CarParkingDialogListener? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_car_parking_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCarNo = view.findViewById(R.id.carNo)
        tvPhoneNumber = view.findViewById(R.id.phoneNumber)
        tvSlotNo = view.findViewById(R.id.slotNumber)
        tvCheckInTime = view.findViewById(R.id.checkInTime)
        btnOk = view.findViewById(R.id.ok)
        priceCalculation = view.findViewById(R.id.priceCalculation)
        initView()
    }

    private fun initView(){
        val carNo = arguments?.getString(Constants.CAR_NO,)
        val phoneNumber = arguments?.getString(Constants.USER_PHONE_NUMBER)
        val checkIn = arguments?.getLong(Constants.CHECK_IN)
        val slotNumber =  arguments?.getInt(Constants.SLOT_NO)
        tvCarNo.text = "${Constants.CAR_NUMBER}${carNo}"
        tvPhoneNumber.text = "${Constants.USER_PHONE_NUMBER}${phoneNumber}"
        tvSlotNo.text = "${Constants.CAR_SLOT_NO}${slotNumber}"
        val checkInDateTime = getCurrentDateTime(checkIn)
        tvCheckInTime.text = "${Constants.CHECK_IN_TIME}${checkInDateTime}"
        priceCalculation.text = "${Constants.CAR_PARKING_AMOUNT}${calculate()}"
        btnOk.setOnClickListener{

            if (slotNumber != null) {
                slotNumber?.let { it1 -> dialogListener?.btnClicked(it1) }
            }
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(800,600)
    }

    private fun calculate(): Int {
        val ratePerHour = 50
        val timeDifference = System.currentTimeMillis() - checkIn
        val parkingDurationInHours = timeDifference / (1000 * 60)
        val parkingDuration = getHours(parkingDurationInHours)
        return ratePerHour * parkingDuration.toInt()
    }
    private fun getHours(parkingDurationInMillis: Long): String {
        val dateTime = SimpleDateFormat(Constants.HOURS_PATTERN, Locale.getDefault())
        return dateTime.format(parkingDurationInMillis)
    }
    private fun getCurrentDateTime(checkIn: Long?): String {
        val dateTime = SimpleDateFormat(Constants.DATE_PATTERN, Locale.getDefault())
        return dateTime.format(checkIn)
    }

    interface CarParkingDialogListener {
        fun btnClicked(slotNumber: Int)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialogListener = context as CarParkingDialogListener
    }
}