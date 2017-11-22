package com.example.testapplication.modules.currency

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.ParseException
import java.util.*


class MoneyTextWatcher(
    editText: EditText,
    private val currencySign: String,
    private val currencyCode: String) : TextWatcher {

    var rawValueChangedListener: OnRawValueChangedListener? = null

    private val editTextWeakReference = WeakReference(editText)

    private val lastEnteredText: String? = null

    override fun afterTextChanged(s: Editable?) = Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val editText = editTextWeakReference.get() ?: return
        val text = s.toString()
        if (text != lastEnteredText) {
            val value = parseValue(text)

            rawValueChangedListener?.onRawValueChanged(value)

            if (value == null) {
                setValue(editText, "")
            } else {
                val cursorPosition = editText.text.length - editText.selectionEnd

                val formattedText = getFormattedPrice(value, currencyCode, currencySign)
                setValue(editText, formattedText)

                val textLength = editText.text.length
                val newSelectionEndIndex = if (textLength - cursorPosition >= 0) textLength - cursorPosition else 0

                editText.setSelection(newSelectionEndIndex)
            }
        }
    }

    private fun setValue(editText: EditText, value: String) {
        editText.removeTextChangedListener(this)
        editText.setText(value)
        editText.addTextChangedListener(this)
    }

    private fun getFormattedPrice(value: Double, currencyCode: String, currencySign: String): String {
        return String.format("%s%s", format(value, currencyCode), currencySign)
    }

    private fun format(value: Number, currencyCode: String): String {
        val currency = Currency.getInstance(currencyCode)
        val numberFormat = NumberFormat.getInstance(Locale.getDefault())
        numberFormat.currency = currency
        return numberFormat.format(value)
    }

    private fun parseValue(value: String?): Double? {
        if (value == null || value.isEmpty())
            return null

        val format = NumberFormat.getNumberInstance(Locale.getDefault())

        val symbols = DecimalFormatSymbols(Locale.getDefault())
        if (format is DecimalFormat) {
            format.isParseBigDecimal = true
        }

        try {
            val newValue = value
                .replace(symbols.groupingSeparator, Character.MIN_VALUE)
                .replace("[^\\d.,]".toRegex(), "")

            return format.parse(newValue).toDouble()
        } catch (e: ParseException) {
            e.printStackTrace()
            return null
        }
    }

    interface OnRawValueChangedListener {
        fun onRawValueChanged(value: Double?)
    }
}