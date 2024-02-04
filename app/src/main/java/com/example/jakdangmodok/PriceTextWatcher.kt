package com.example.jakdangmodok

import android.icu.text.DecimalFormat
import android.text.Editable
import android.text.Selection
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText

// 요금 천 단위 콤마 설정
internal class PriceTextWatcher(private val editText: EditText) : TextWatcher {
    private var strAmount = ""

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (!TextUtils.isEmpty(s.toString()) && s.toString() != strAmount) {
            strAmount = makeStringComma(s.toString().replace(",", ""))
            editText.setText(strAmount)
            val editable = editText.text
            Selection.setSelection(editable, strAmount.length)
        }
    }

    override fun afterTextChanged(s: Editable) {}
    private fun makeStringComma(str: String): String {    // 천 단위 콤마 설정
        if (str.isEmpty()) {
            return ""
        }
        val value = str.toLong()
        val format = DecimalFormat("###,###")
        return format.format(value)
    }
}