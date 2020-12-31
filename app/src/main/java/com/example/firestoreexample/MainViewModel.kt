package com.example.firestoreexample

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var greet = ""
    val repository = Repository()


    fun loadBoard(){
        repository.loadGreet()
    }
    fun sendGreet(){
        repository.sendGreet(greet)
    }
}