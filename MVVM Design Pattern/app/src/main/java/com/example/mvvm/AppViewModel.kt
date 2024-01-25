package com.example.mvvm

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class AppViewModel : BaseObservable() {

    private val model = Model("", "")

    private val success = "Login successful"
    private val error = "Email or Password is not valid"

    @get:Bindable
    var message: String? = null
         set(value) {
            field = value
            notifyPropertyChanged(BR.message)
        }

    @get:Bindable
    var mail : String
        get() = model.email
        set(email) {
            model.email = email
            notifyPropertyChanged(BR.mail)
        }

    @get:Bindable
    var password : String
        get() = model.password
        set(password) {
            model.email = password
            notifyPropertyChanged(BR.password)
        }
}