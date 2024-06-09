package com.huseynsharif.common

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getResourceIdByName(context: Context, name: String, defType:String = "drawable"): Int {
    return context.resources.getIdentifier(name, defType, context.packageName)
}

fun showShortToast(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT)
        .show()
}

fun convertLongToDateString(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return format.format(date)
}