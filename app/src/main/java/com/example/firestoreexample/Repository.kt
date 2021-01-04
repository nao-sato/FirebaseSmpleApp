package com.example.firestoreexample

import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class Repository {

    private val DocRef = FirebaseFirestore.getInstance().collection("Greets")
    var list = listOf<Greet>()

    suspend fun sendGreet(g:String) {
        val greet = Greet()
        greet.greeting = g
        DocRef.document("${greet.id}").set(greet).addOnCompleteListener { sendResult ->
                if (sendResult.isSuccessful) {
                    CoroutineScope(IO).launch {
                        loadGreet()
                    }
                } else {
                    Toast.makeText(GreetApplication.context, "送信できませんでした", Toast.LENGTH_LONG).show()
                }
            }
    }


    suspend fun loadGreet(){
        DocRef.get().addOnCompleteListener { loadResult ->
            if (loadResult.isSuccessful){
                list = loadResult.result?.toObjects(Greet::class.java) ?: emptyList<Greet>()
                Timber.d("TimberlistR${list.size}")
            }
            else {
                Toast.makeText(GreetApplication.context, "読み込みできませんでした", Toast.LENGTH_LONG).show()
            }
        }

    }

    suspend fun upData(greet: Greet?){
        greet?.apply {
            createAt = Date().toString()
        }?.let {
            FirebaseFirestore.getInstance().collection("Greets")
                .document("${greet.id}")
                .set(it)
                .addOnCompleteListener { sendResult ->
                    if (sendResult.isSuccessful) {
                        CoroutineScope(IO).launch {
                            loadGreet()
                        }
                    } else {
                        Toast.makeText(GreetApplication.context, "送信できませんでした", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}