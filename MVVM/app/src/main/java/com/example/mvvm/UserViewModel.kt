package com.example.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {

    private val userList = MutableLiveData<List<User>>()
    fun setUserList(): MutableLiveData<List<User>> {
        return userList
    }
    fun getUserDataFromApi() {
        val userApi = RetrofitHelper.getInstance().create(UserApi::class.java)
        viewModelScope.launch(Dispatchers.IO) {
            val result = userApi.getUser()
                userList.postValue(result)
            userList.value = result
        }
    }
}



