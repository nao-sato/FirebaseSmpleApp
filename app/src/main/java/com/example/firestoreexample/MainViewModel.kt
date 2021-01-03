package com.example.firestoreexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val repository = Repository()
    var editGreet = MutableLiveData<Greet>()

    var greet = ""
    var greetList = MutableLiveData<List<Greet>>()

    fun loadBoard(){
        greetList.postValue(repository.loadGreet())
        Timber.d("Timberlist${greetList.value}")
    }
    fun sendGreet(){
        repository.sendGreet(greet)
    }
}