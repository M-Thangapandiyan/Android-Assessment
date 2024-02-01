package com.example.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private val userList = MutableLiveData<APIResponse<List<User>>>()
    fun setUserList(): MutableLiveData<APIResponse<List<User>>> {
        return userList
    }

    fun getUserDataFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUsers(userList)
        }
    }
}



