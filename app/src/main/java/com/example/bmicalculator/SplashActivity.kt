package com.example.bmicalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.bmicalculator.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        playEntranceAnimation()
        navigateToMainAfterDelay()
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun playEntranceAnimation() {
        // 1. Logo card: 180-degree spin + scale-up + fade-in
        binding.logoCard.apply {
            alpha = 0f
            scaleX = 0.7f
            scaleY = 0.7f
            rotation = -180f
            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .rotation(0f)
                .setDuration(650L)
                .setInterpolator(android.view.animation.OvershootInterpolator(1.1f))
                .start()
        }

        // 2. Title and subtitle: staggered slide-up + fade-in
        listOf(binding.appTitle, binding.appSubtitle).forEachIndexed { index, view ->
            view.alpha = 0f
            view.translationY = 20f
            view.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(450L)
                .setStartDelay(220L + (index * 100L))
                .start()
        }
    }

    private fun navigateToMainAfterDelay() {
     /*   lifecycleScope.launch {
            delay(1600L.milliseconds)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }*/
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close SplashActivity so user cannot navigate back to it
        }, 1600)
    }
}