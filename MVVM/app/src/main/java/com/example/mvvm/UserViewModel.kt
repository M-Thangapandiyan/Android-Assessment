package com.example.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val userRepository = UserRepository(this)

    val userList = MutableLiveData<List<User>>()
    fun setUserList(): MutableLiveData<List<User>> {
        return userList
    }

    fun getUsers() {
        userRepository.getUserDataFromApi()
    }

}



