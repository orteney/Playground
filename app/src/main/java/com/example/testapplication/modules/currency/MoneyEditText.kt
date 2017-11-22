package com.example.testapplication.modules.currency

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import com.example.testapplication.R
import java.util.*


class MoneyEditText : AppCompatEditText,
                      MoneyTextWatcher.OnRawValueChangedListener {

    private val DEFAULT_CURRENCY_CODE = "RUB"
    private val DEFAULT_CURRENCY_SIGN = "₽"

    private var currencySign: String? = null
    private var currencyCode: String? = null

    private var moneyTextWatcher: MoneyTextWatcher? = null

    var rawPriceValue: Double? = null

    var isShowCurrencyHintIfFocused: Boolean = false
        set(value) {
            field = value
            hint = if (value) currencySign else ""
            updateCurrencyHintVisibility()
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        handleAttributes(context, attrs, defStyleAttr)
    }

    private fun handleAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.MoneyEditText, defStyleAttr, 0)

        var sign = attributes.getString(R.styleable.MoneyEditText_currencySign)
        var code = attributes.getString(R.styleable.MoneyEditText_currencyCode)

        if (code.isNullOrEmpty()) {
            code = DEFAULT_CURRENCY_CODE
        }

        if (sign.isNullOrEmpty()) {
            sign = if (DEFAULT_CURRENCY_CODE == code) DEFAULT_CURRENCY_SIGN else Currency.getInstance(code).symbol
        }

        setCurrency(code, sign)

        isShowCurrencyHintIfFocused = attributes.getBoolean(R.styleable.MoneyEditText_showCurrencyHintIfFocused, false)

        attributes.recycle()
    }

    private fun updateCurrencyHintVisibility() {
        setHintTextColor(if (isShowCurrencyHintIfFocused && isFocused) currentHintTextColor else Color.TRANSPARENT)
    }

    fun setCurrency(code: String, sign: String) {
        currencyCode = code
        currencySign = sign

        if (currencySign != null && !currencySign!!.startsWith(" ")) {
            currencySign = " " + currencySign
        }

        removeTextChangedListener(moneyTextWatcher)
        moneyTextWatcher = MoneyTextWatcher(this, currencySign!!, currencyCode!!)
        moneyTextWatcher?.rawValueChangedListener = this
        addTextChangedListener(moneyTextWatcher)
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)

        if (!focused && rawPriceValue == null) {
            removeTextChangedListener(moneyTextWatcher)
            setText("")
            addTextChangedListener(moneyTextWatcher)
        }

        updateCurrencyHintVisibility()
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        var selEnd = selEnd
        val text = text.toString()

        if (currencySign == null || !text.contains(currencySign!!) || selEnd != selStart || selStart == 0) {
            super.onSelectionChanged(selStart, selEnd)
            return
        }

        val currencyLength = currencySign!!.length
        val textLength = text.length
        val maxAllowedCursorPosition = if (textLength - currencyLength >= 0)
            textLength - currencyLength
        else
            0

        if (selEnd > maxAllowedCursorPosition) {
            selEnd = maxAllowedCursorPosition
            setSelection(selEnd)
        } else {
            super.onSelectionChanged(selStart, selEnd)
        }
    }

    override fun onRawValueChanged(value: Double?) {
        rawPriceValue = value
    }
}