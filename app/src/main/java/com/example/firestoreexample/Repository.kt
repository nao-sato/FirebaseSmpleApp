package com.example.firestoreexample

import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class Repository {

    fun sendGreet(g:String) {
        val greet = Greet()
        greet.greeting = g
        FirebaseFirestore.getInstance().collection("Greets")
                .document("${greet.id}")
                .set(greet)
                .addOnCompleteListener { sendResult ->
                    if (sendResult.isSuccessful) {
                        loadGreet()
                    } else {
                        Toast.makeText(GreetApplication.context, "送信できませんでした", Toast.LENGTH_SHORT).show()
                    }
                }
    }
        fun loadGreet():List<Greet> {
            var data = listOf<Greet>()
            FirebaseFirestore.getInstance().collection("Greets")
            .get()
            .addOnCompleteListener { loadResult ->
                if (loadResult.isSuccessful){
                    data = loadResult.result?.toObjects(Greet::class.java) ?: emptyList()
                    Timber.d("Timberlist${data.size}")
                } else {
                    Toast.makeText(GreetApplication.context, "読み込みできませんでした", Toast.LENGTH_SHORT).show()
                }
            }
            return data
        }
}