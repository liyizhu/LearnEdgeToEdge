package com.example.learnedgetoedge

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View.OnClickListener
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learnedgetoedge.databinding.ActivityMainBinding
import com.example.learnedgetoedge.subPage.DefaultActivity
import com.example.learnedgetoedge.subPage.DisplayCutoutActivity
import com.example.learnedgetoedge.subPage.EdgeToEdgeActivity
import com.example.learnedgetoedge.subPage.FitsSystemWindowsActivity
import com.example.learnedgetoedge.subPage.GesturesActivity
import com.example.learnedgetoedge.subPage.ImeActivity
import com.example.learnedgetoedge.subPage.ImeOldActivity
import com.example.learnedgetoedge.subPage.ImeSystemDefaultActivity
import com.example.learnedgetoedge.subPage.ImmersiveModeActivity
import com.example.learnedgetoedge.subPage.NavigationBarContrastEnforcedActivity
import com.example.learnedgetoedge.subPage.SystemBarInsetsActivity
import com.example.learnedgetoedge.subPage.SystemBarInsetsAndDisplayCutoutActivity
import com.example.learnedgetoedge.utils.PxUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addButton("Default") {
            gotoActivity(DefaultActivity::class.java)
        }

        addButton("NavigationBarContrastEnforced") {
            gotoActivity(NavigationBarContrastEnforcedActivity::class.java)
        }

        addButton("EdgeToEdge") {
            gotoActivity(EdgeToEdgeActivity::class.java)
        }

        addButton("SystemBarInsets") {
            gotoActivity(SystemBarInsetsActivity::class.java)
        }

        addButton("DisplayCutout") {
            gotoActivity(DisplayCutoutActivity::class.java)
        }

        addButton("SystemBarInsetsAndDisplayCutout") {
            gotoActivity(SystemBarInsetsAndDisplayCutoutActivity::class.java)
        }

        addButton("Gestures") {
            gotoActivity(GesturesActivity::class.java)
        }

        addButton("FitsSystemWindows") {
            gotoActivity(FitsSystemWindowsActivity::class.java)
        }

        addButton("ImmersiveMode") {
            gotoActivity(ImmersiveModeActivity::class.java)
        }

        addButton("ImeSystemDefault") {
            gotoActivity(ImeSystemDefaultActivity::class.java)
        }

        addButton("ImeOld") {
            gotoActivity(ImeOldActivity::class.java)
        }

        addButton("Ime") {
            gotoActivity(ImeActivity::class.java)
        }
    }

    private fun addButton(text: String, onClickListener: OnClickListener) {
        val textView = TextView(this)
        textView.gravity = Gravity.CENTER
        textView.text = text
        textView.setTextColor(Color.parseColor("#000000"))
        textView.textSize = 20f
        textView.setTypeface(Typeface.DEFAULT_BOLD)
        val layoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                PxUtil.dp2px(resources, 50f)
            )
        textView.setOnClickListener(onClickListener)
        binding.llButtons.addView(textView, layoutParams)
    }

    private fun gotoActivity(activityClazz: Class<*>) {
        val intent = Intent(this, activityClazz)
        startActivity(intent)
    }


}