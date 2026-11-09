package com.example.bmicalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.example.bmicalculator.databinding.ActivityMaleBmiCalculateBinding

class MaleBmiCalculateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMaleBmiCalculateBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMaleBmiCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun clear(){
            binding.heightET.text.clear()
            binding.weightET.text.clear()
        }
        binding.calculate.setOnClickListener {
            binding.resultTV.visibility = View.VISIBLE
            val inputA = binding.heightET.text.toString().trim()
            val inputB = binding.weightET.text.toString().trim()

            if (inputA.isEmpty() || inputB.isEmpty()){
                Toast.makeText(this, "enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val height = inputA.toDouble()
            val weight = inputB.toDouble()
            val bmi = weight / (height*height)

            binding.resultTV.text = "Result : ${bmi}"


        }

    }
}