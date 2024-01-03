package com.app.speertechassessment.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.app.speertechassessment.models.User
import com.app.speertechassessment.network.ApiService
import kotlinx.coroutines.launch

class SearchViewModel(val apiService: ApiService): ViewModel() {

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
}

class SearchViewModelFactory(val apiService: ApiService): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(apiService) as T
        }
        return super.create(modelClass)
    }
}