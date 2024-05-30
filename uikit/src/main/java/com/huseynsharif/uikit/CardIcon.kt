package com.huseynsharif.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.huseynsharif.uikit.databinding.CardIconBinding

class CardIcon @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle)  {

    val binding: CardIconBinding =
        CardIconBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.IconCardView)
        typeArray.recycle()
    }
}