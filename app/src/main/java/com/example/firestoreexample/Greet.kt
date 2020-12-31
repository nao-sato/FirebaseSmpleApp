package com.example.firestoreexample

import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
class Greet {
    var id = System.currentTimeMillis()
    var greeting:String = ""
    var createAt:String = Date().toString()
}