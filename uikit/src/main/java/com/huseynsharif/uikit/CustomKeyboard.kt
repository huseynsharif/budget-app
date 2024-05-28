package com.huseynsharif.uikit

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.huseynsharif.uikit.databinding.CustomKeyboardBinding

class CustomKeyboard @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet? = null,
    defStyle:Int = 0
) : ConstraintLayout(context, attrs, defStyle) {



    private val binding : CustomKeyboardBinding = CustomKeyboardBinding.inflate(LayoutInflater.from(context), this, true)

    lateinit var onSubmit : ((String) -> Unit)

    fun getBinding() = binding

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomKeyboardView)

        typeArray.recycle()

        binding.btnSubmit.setOnClickListener{onSubmit.invoke(binding.btnOne.text.toString())}
    }

}