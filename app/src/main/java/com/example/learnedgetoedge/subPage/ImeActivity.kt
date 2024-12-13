package com.example.learnedgetoedge.subPage

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnedgetoedge.databinding.ActivityImeBinding

class ImeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityImeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            Log.d(TAG, "onCreate: $systemBars $ime")
            insets
        }

        ViewCompat.setWindowInsetsAnimationCallback(
            binding.editText,
            object : WindowInsetsAnimationCompat.Callback(
                DISPATCH_MODE_STOP
            ) {
                var imeVisible = false
                var imeHeight = 0

                override fun onStart(
                    animation: WindowInsetsAnimationCompat,
                    bounds: WindowInsetsAnimationCompat.BoundsCompat,
                ): WindowInsetsAnimationCompat.BoundsCompat {
                    val insets = ViewCompat.getRootWindowInsets(binding.editText)
                    if (insets != null) {
                        imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
                        imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                    }
                    Log.d(TAG, "onStart: ")
                    return bounds
                }

                override fun onProgress(
                    insets: WindowInsetsCompat,
                    runningAnimations: MutableList<WindowInsetsAnimationCompat>,
                ): WindowInsetsCompat {
                    val imeAnimation = runningAnimations.find {
                        it.typeMask and WindowInsetsCompat.Type.ime() != 0
                    } ?: return insets
                    Log.d(TAG, "onProgress: ${imeAnimation.interpolatedFraction}")
                    if (imeVisible) {
                        binding.editText.translationY =
                            -(imeHeight - binding.main.paddingBottom) * imeAnimation.interpolatedFraction
                    } else {
                        binding.editText.translationY =
                            -(imeHeight - binding.main.paddingBottom) * (1 - imeAnimation.interpolatedFraction)
                    }
                    return insets
                }
            })
    }

    companion object {
        private const val TAG = "ImeActivity"
    }
}