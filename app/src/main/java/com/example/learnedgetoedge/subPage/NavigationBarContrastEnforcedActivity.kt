package com.example.learnedgetoedge.subPage

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.learnedgetoedge.databinding.ActivityNavigationBarContrastEnforcedBinding

class NavigationBarContrastEnforcedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBarContrastEnforcedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNavigationBarContrastEnforcedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }
}