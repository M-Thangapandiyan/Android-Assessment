package com.example.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserActivityViewModel() : ViewModel() {

    private val userRepository = UserRepository()

    private val userById = MutableLiveData<APIResponse<User>>()
    fun setUserById(): MutableLiveData<APIResponse<User>> {
        return userById
    }

    fun getUserId(id: Int) {
        viewModelScope.launch {
            userRepository.getUserDataFromApiById(id, userById)
        }
    }
}