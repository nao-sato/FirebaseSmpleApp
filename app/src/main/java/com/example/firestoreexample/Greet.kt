package com.example.firestoreexample

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@IgnoreExtraProperties
@Parcelize
data class Greet @RequiresApi(Build.VERSION_CODES.O) constructor(

        var id: Long = System.currentTimeMillis(),
        var greeting:String? = "",
        var createAt:String? = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
):Parcelable