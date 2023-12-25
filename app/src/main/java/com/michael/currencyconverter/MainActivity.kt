package com.michael.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var nairaSymbolTextView: TextView
    private lateinit var currencyEditText: EditText
    private lateinit var converterButton: Button
    private lateinit var displayCurrencyTextView: TextView

    // Currency conversion rates:
    private val DOLLAR_RATE = 1_200
    private val POUND_RATE = 1_500
    private val EURO_RATE = 1_300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nairaSymbolTextView = findViewById(R.id.nairaSymbolTextView)
        currencyEditText = findViewById(R.id.currencyEditText)
        converterButton = findViewById(R.id.converterButton)
        displayCurrencyTextView = findViewById(R.id.displayCurrencyTextView)

        converterButton.setOnClickListener {
            val inputCurrency = currencyEditText.text.toString()
            if (inputCurrency.isNotBlank()) {
                val naira = inputCurrency.toInt()
                displayCurrencyTextView.text = currencyConverter(naira)
                displayCurrencyTextView.visibility = View.VISIBLE
            } else {
                displayCurrencyTextView.text = "Please enter a valid amount."
                displayCurrencyTextView.visibility = View.VISIBLE
            }

        }
    }

    private fun formatCurrency(value: Int, currencyCode: String): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
        formatter.currency = Currency.getInstance(currencyCode)
        return formatter.format(value / 100.0)
    }

    private fun currencyConverter(naira: Int): String {
       return if (naira >= 0) {
           val dollar = formatCurrency(naira * DOLLAR_RATE, "USD")
           val pound = formatCurrency(naira * POUND_RATE, "GBP")
           val euro = formatCurrency(naira * EURO_RATE, "EUR")
           """
               Dollar: $$dollar
               Pound: £$pound
               Euro: €$euro
           """.trimIndent()
       } else {
           "Your input is less than zero, try again."
       }
    }
}