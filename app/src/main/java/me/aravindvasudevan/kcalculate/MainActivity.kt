package me.aravindvasudevan.kcalculate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var userIsInTheMiddleOfTyping = false

    val operations = mapOf<String, (Double, Double) -> Double>(
            "+" to {a, b -> a + b},
            "–" to {a, b -> a - b},
            "÷" to {a, b -> a / b},
            "×" to {a, b -> a * b}
    )

    data class PendingOperation(val firstNumber: Double, val operation: (Double, Double) -> Double)
    var pendingOperation: PendingOperation? = null

    fun performPendingOperation(secondNumber: Double): Double {
        return pendingOperation?.operation!!.invoke(pendingOperation!!.firstNumber, secondNumber)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberPressed(view: View) {
        val button = view as Button
        val digit = button.text

        if(userIsInTheMiddleOfTyping) {
            display.append(digit)
        } else {
            display.text = button.text
            userIsInTheMiddleOfTyping = true
        }
    }

    fun performOperation(view: View) {
        val button = view as Button
        if(display.text != "") {
            if(pendingOperation == null) {
                if(button.text != "=") {
                    pendingOperation = PendingOperation(display.text.toString().toDouble(), operations[button.text]!!)
                    display.text = ""
                }
            } else {
                display.text = performPendingOperation(display.text.toString().toDouble()).toString()
                pendingOperation = null
                if(button.text != "=") {
                    pendingOperation = PendingOperation(display.text.toString().toDouble(), operations[button.text]!!)
                    display.text = ""
                }
            }
        }
    }

    fun clearDisplay(view: View) {
        if(userIsInTheMiddleOfTyping) {
            display.text = ""
        }
    }
}