package com.example.firestoreexample.ui

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firestoreexample.Greet
import com.example.firestoreexample.GreetApplication
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*



class MainViewModel : ViewModel() {

    private val docRef = FirebaseFirestore.getInstance().collection("Greets")

    var editGreet = MutableLiveData<Greet>()
    var greetList = MutableLiveData<List<Greet>>()
    var greetText:String = ""

    fun sendGreet() {
        val greet = Greet()
        greet.greeting = greetText
        docRef.document("${greet.id}").set(greet).addOnCompleteListener { sendResult ->
            if (sendResult.isSuccessful) {
                loadGreet()
            } else {
                Toast.makeText(GreetApplication.context, "送信できませんでした。", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun loadGreet(){
        docRef.get().addOnCompleteListener { loadResult ->
            if (loadResult.isSuccessful){
                greetList.postValue(loadResult.result?.toObjects(Greet::class.java) ?: emptyList())
            }
            else {
                Toast.makeText(GreetApplication.context, "読み込めませんでした。", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun upData(greet: Greet){
       greet.apply {
            createAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
        }.let {
            FirebaseFirestore.getInstance().collection("Greets")
                    .document("${greet.id}")
                    .set(it)
                    .addOnCompleteListener { sendResult ->
                        if (sendResult.isSuccessful) {
                            loadGreet()
                        } else {
                            Toast.makeText(GreetApplication.context, "送信できませんでした。", Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }
}
