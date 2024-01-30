package com.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val userList = MutableLiveData<List<User>>()
    val listOfUsers: LiveData<List<User>> = userList
    fun setUserList(users: List<User>) {
        userList.value = users
    }
}