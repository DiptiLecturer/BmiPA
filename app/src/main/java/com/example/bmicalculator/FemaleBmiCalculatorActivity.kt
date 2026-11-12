package com.example.bmicalculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityFemaleBmiCalculatorBinding
import java.util.Locale

class FemaleBmiCalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFemaleBmiCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFemaleBmiCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }



    private fun setupListeners() {
        binding.calculate.setOnClickListener {
            calculateAndDisplayBmi()
        }
    }

    private fun calculateAndDisplayBmi() {
        val height = binding.heightET.text?.toString()?.trim()?.toDoubleOrNull()
        val weight = binding.weightET.text?.toString()?.trim()?.toDoubleOrNull()

        if (height == null || weight == null) {

            Toast.makeText(this, "Enter valid numbers in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (height <= 0.0 || weight <= 0.0) {
            Toast.makeText(this, "Height and weight must be greater than zero", Toast.LENGTH_SHORT).show()
            return
        }

        val bmi = weight / (height * height)
        displayResult(bmi)
    }

    private fun displayResult(bmi: Double) {
        val (colorRes, statusText) = when {
            bmi < 18.5 -> R.color.under_weight to "You are Underweight"
            bmi < 25.0 -> R.color.normal to "You are Healthy"
            bmi < 30.0 -> R.color.over_weight to "You are Overweight"
            else -> R.color.obese to "You are Obese"
        }

        binding.resultBox.visibility = View.VISIBLE
        binding.resultTV.text = String.format(Locale.US, "%.2f", bmi)
        binding.resultTV.setTextColor(ContextCompat.getColor(this, colorRes))
        binding.resulttext.text = statusText
    }
}