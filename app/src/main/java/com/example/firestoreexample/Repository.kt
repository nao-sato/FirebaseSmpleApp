package com.example.firestoreexample

import com.google.firebase.firestore.FirebaseFirestore

class Repository {

    var greetList = listOf<Greet>()

    fun sendGreet(g:String){
        val greet = Greet()
        greet.greeting = g
        FirebaseFirestore.getInstance().collection("Greets")
                .document("${greet.id}")
                .set(greet)
    }
    fun loadGreet(){
        FirebaseFirestore.getInstance().collection("Greets")
            .get()
            .addOnCompleteListener{result ->
                greetList = result.result?.toObjects(Greet::class.java) ?: emptyList()
            }
    }
}