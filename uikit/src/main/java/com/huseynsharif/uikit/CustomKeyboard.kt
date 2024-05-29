package com.huseynsharif.uikit

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.huseynsharif.uikit.databinding.CustomKeyboardBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CustomKeyboard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding: CustomKeyboardBinding =
        CustomKeyboardBinding.inflate(LayoutInflater.from(context), this, true)

    private val inputList = mutableListOf<String>()

    lateinit var onSubmit: (String) -> Unit

    fun getBinding() = binding

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomKeyboardView)
        typeArray.recycle()

        initButtons()
    }

    private fun initButtons() {


        binding.apply {
            listOf(
                btnZero,
                btnOne,
                btnTwo,
                btnThree,
                btnFour,
                btnFive,
                btnSix,
                btnSeven,
                btnEight,
                btnNine
            ).forEach { button ->
                button.setOnClickListener {
                    inputList.add(button.text.toString())
                    renderInput()
                }

            }

            listOf(
                dot,
                btnPlus,
                btnMinus
            )
                .forEach { button ->
                    button.setOnClickListener {
                        try {
                            if (".+-".contains(inputList.last())) {
                                inputList.removeLast()
                            }
                            inputList.add(button.text.toString())
                            renderInput()
                        } catch (_: NoSuchElementException) {

                        }

                    }
                }

            btnBack.setOnClickListener {
                try {
                    inputList.removeLast()
                } catch (_: NoSuchElementException) {

                }
                renderInput()
            }

            btnSubmit.setOnClickListener {
                onSubmit.invoke(binding.input.text.toString())
            }

            btnDate.setOnClickListener {
                showDatePickerDialog { day, month, year ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, day)
                    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                    val formattedDate = dateFormat.format(calendar.time)
                    btnDate.text = formattedDate
                }
            }
        }
    }

    private fun renderInput() {
        binding.input.text = inputList.joinToString("")
    }

    private fun showDatePickerDialog(onDateSet: (day: Int, month: Int, year: Int) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                onDateSet(selectedDay, selectedMonth, selectedYear)
            }, year, month, day)

        datePickerDialog.show()
    }

}