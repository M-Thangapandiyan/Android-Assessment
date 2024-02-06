package com.example.mvvm

import androidx.lifecycle.MutableLiveData

class UserRepository {

    private var userApi: UserApi = RetrofitHelper.getInstance().create(UserApi::class.java)
    suspend fun getUserDataFromApiById(getId: Int, userById: MutableLiveData<APIResponse<User>>) {
        try {
            val result = userApi.getUserById(getId)
            userById.postValue(APIResponse(success = result))
        } catch (exception: Exception) {
            userById.postValue(APIResponse(error = exception.message))
        }
    }

    suspend fun getUsers(userList: MutableLiveData<APIResponse<List<User>>>) {
        try {
            val result = userApi.getUser()
            userList.postValue(APIResponse(success = result))
        } catch (exception: Exception) {
            userList.postValue(APIResponse(error = exception.message))
        }
    }

}