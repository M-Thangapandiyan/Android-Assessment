package com.example.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserActivityViewModel() : ViewModel() {

    private val userRepository = UserRepository(this)

    private val userById = MutableLiveData<User>()
    fun setUserById(): MutableLiveData<User> {
        return userById
    }
    fun getUserId(id: Int) {
         userRepository.getUserDataFromApiById(id)
    }

}