package com.example.learnedgetoedge.subPage

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnedgetoedge.databinding.ActivityImeOldBinding
import com.example.learnedgetoedge.utils.KeyBoardStatePopupWindow
import com.example.learnedgetoedge.utils.PxUtil

class ImeOldActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImeOldBinding

    private lateinit var keyBoardStatePopupWindow: KeyBoardStatePopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityImeOldBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.editText.post {
            keyBoardStatePopupWindow = KeyBoardStatePopupWindow(this).setHeightListener { height ->
                Log.d(TAG, "onCreate: $height")
                if (height > 200 && height < PxUtil.dp2px(resources, 400f)) {
                    binding.editText.translationY =
                        -height.toFloat() + binding.main.paddingBottom
                } else {
                    binding.editText.translationY = 0f
                }
            }
        }
        binding.editText.setOnClickListener {
            keyBoardStatePopupWindow.init()
        }
    }

    companion object {
        private const val TAG = "ImeOldActivity"
    }
}