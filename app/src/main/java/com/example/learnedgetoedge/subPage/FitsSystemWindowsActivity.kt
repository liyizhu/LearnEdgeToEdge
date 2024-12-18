package com.example.learnedgetoedge.subPage

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.learnedgetoedge.databinding.ActivityFitsSystemWindowsBinding

class FitsSystemWindowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFitsSystemWindowsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFitsSystemWindowsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.main.post {
            Log.d(TAG, "onCreate: ${binding.main.paddingTop} ${binding.main.paddingBottom}")
        }
    }

    companion object {
        private const val TAG = "FitsSystemWindowsActivi"
    }
}