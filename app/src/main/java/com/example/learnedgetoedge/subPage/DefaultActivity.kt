package com.example.learnedgetoedge.subPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnedgetoedge.databinding.ActivityDefaultBinding

class DefaultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDefaultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}