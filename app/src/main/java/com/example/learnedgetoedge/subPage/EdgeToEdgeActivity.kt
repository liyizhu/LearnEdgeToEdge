package com.example.learnedgetoedge.subPage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.learnedgetoedge.databinding.ActivityEdgeToEdgeBinding

class EdgeToEdgeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEdgeToEdgeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEdgeToEdgeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}