package com.example.firestoreexample

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val repository = Repository()

    var greet = ""
    var editGreet = MutableLiveData<Greet>()

    fun loadBoard(){
        CoroutineScope(IO).launch {
            repository.loadGreet()
        }
    }
    fun sendGreet(){
        CoroutineScope(IO).launch {
            repository.sendGreet(greet)
        }
    }
}