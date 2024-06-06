package com.huseynsharif.common

import android.content.Context
import android.widget.Toast

fun getResourceIdByName(context: Context, name: String, defType:String = "drawable"): Int {
    return context.resources.getIdentifier(name, defType, context.packageName)
}

fun showShortToast(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT)
        .show()
}