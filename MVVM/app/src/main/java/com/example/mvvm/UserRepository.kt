package com.example.mvvm


import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userViewModel: UserActivityViewModel, private val viewModel: UserViewModel ) {
    fun getUserDataFromApi() {
        val userApi = RetrofitHelper.getInstance().create(UserApi::class.java)
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            val result = userApi.getUser()
            viewModel.userList.postValue(result)
        }
    }
    fun getUserDataFromApiById(getId: Int) {
        val userApi = RetrofitHelper.getInstance().create(UserApi::class.java)
        userViewModel.viewModelScope.launch(Dispatchers.IO) {
            val result = userApi.getUserById(getId)
            userViewModel.setUserById().postValue(result)
        }
    }

}