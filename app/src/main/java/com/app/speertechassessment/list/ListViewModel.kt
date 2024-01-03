package com.app.speertechassessment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.app.speertechassessment.models.User
import com.app.speertechassessment.network.ApiService
import kotlinx.coroutines.launch


class ListViewModel(val apiService: ApiService) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>(listOf())
    val userList: LiveData<List<User>> = _userList


    private val _user = MutableLiveData<User?>(null)
    val user: LiveData<User?> = _user

    fun getUser(searchTerm:String){
        viewModelScope.launch {
            val data = apiService.getUser(searchTerm)
            _user.postValue(data.body())
        }
    }

    fun clearUser() {
        _user.postValue(null)
    }


    fun getFollowers(handle: String) {
        viewModelScope.launch {
            val data = apiService.getUserFollowers(handle)
            _userList.postValue(data.body())
        }
    }

    fun getFollowing(handle: String) {
        viewModelScope.launch {
            val data = apiService.getUserFollowing(handle)
            _userList.postValue(data.body())
        }
    }

}

class ListViewModelFactory(val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(apiService) as T
        }
        return super.create(modelClass)
    }
}