package com.example.learnedgetoedge.utils

import android.content.res.Resources

object PxUtil {

    fun dp2px(resources: Resources, dpValue: Float): Int {
        val scale: Float = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}