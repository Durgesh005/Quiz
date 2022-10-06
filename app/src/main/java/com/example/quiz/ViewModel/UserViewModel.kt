package com.example.api_coroutine.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_coroutine.ViewModel.ApiClient.Companion.getretrofit
import com.example.quiz.Modeldata
import com.example.quiz.QuestionsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class UserViewModel : ViewModel() {

    var list = MutableLiveData<List<QuestionsItem>>()


    init {
        viewModelScope.launch {
            list.postValue(getUserApiData()?.result!!.questions as List<QuestionsItem>)
        }
    }


    suspend fun getUserApiData(): Modeldata? {

        var apiInterface = getretrofit().create(ApiInterface::class.java)

        return (withContext(Dispatchers.IO) {
            apiInterface.getdata("true").body()
        })

    }





}
