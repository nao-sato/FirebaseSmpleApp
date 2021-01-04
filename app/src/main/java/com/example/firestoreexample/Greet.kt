package com.example.firestoreexample

import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize
import java.util.*


@IgnoreExtraProperties
@Parcelize
data class Greet (
        var id: Long = System.currentTimeMillis(),
        var greeting:String? = "",
        var createAt:String? = Date().toString()
):Parcelable