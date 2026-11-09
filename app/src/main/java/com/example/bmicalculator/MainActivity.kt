package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.maleImg.setOnClickListener {
            val intent = Intent(this@MainActivity, MaleBmiCalculateActivity::class.java)
            startActivity(intent)
        }
        binding.femaleImg.setOnClickListener {
            val intent = Intent(this@MainActivity, FemaleBmiCalculatorActivity::class.java)
            startActivity(intent)
        }

    }
}