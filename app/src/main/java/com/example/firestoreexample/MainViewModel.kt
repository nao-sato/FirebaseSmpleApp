package com.example.firestoreexample

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val repository = Repository()

    var greet = ""
    var editGreet = MutableLiveData<Greet>()
    var greetList = MutableLiveData<List<Greet>>()

    fun loadBoard(){
        CoroutineScope(IO).launch {
            repository.loadGreet()
        }
        greetList.postValue(repository.dataList)
    }
    fun sendGreet(){
        CoroutineScope(IO).launch {
            repository.sendGreet(greet)
        }
    }
}