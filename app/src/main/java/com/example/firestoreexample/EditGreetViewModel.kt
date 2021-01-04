package com.example.firestoreexample

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class EditGreetViewModel : ViewModel() {

    private val repository = Repository()
    var dataGreet:Greet? = null

    fun updateData(){
        CoroutineScope(IO).launch {
            repository.upData(dataGreet)
        }
    }
}