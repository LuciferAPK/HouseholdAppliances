package com.example.householdappliances.utils

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*
import java.util.concurrent.TimeUnit


object CommonUtils {
    fun animateContainer(view: View, duration: Long, valueY: Float) {
        view.animate()
            .translationY(valueY)
            .duration = duration
    }

    fun dpToPx(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun randomColor() =
        Color.argb(180, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))

    fun String.getColor() : Int = try {
        Color.parseColor(this)
    } catch (t: Throwable) {
        randomColor()
    }

    fun animationSetHeight(
        heightStart: Int,
        heightEnd: Int,
        duration: Long,
        viewSet: View,
        callBackEndAnimation: () -> Unit
    ) {
        val anim = ValueAnimator.ofInt(heightStart, heightEnd)
        anim.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = viewSet.layoutParams
            layoutParams.height = value
            viewSet.layoutParams = layoutParams

            /** End animation*/
            if (value == heightEnd) {
                callBackEndAnimation.invoke()
            }
        }
        anim.duration = duration
        anim.start()
    }

    fun animationSetWidth(
        widthStart: Int,
        widthEnd: Int,
        duration: Long,
        viewSet: View,
        callBackStartAnimation: () -> Unit,
        callBackEndAnimation: () -> Unit
    ) {
        val anim = ValueAnimator.ofInt(widthStart, widthEnd)
        anim.apply {
            this.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int
                val layoutParams = viewSet.layoutParams
                if (value != 0) layoutParams.width = value
                else layoutParams.width = 1
                viewSet.layoutParams = layoutParams
                Log.d("CHECK_WIDTH", value.toString())
            }
            this.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    callBackStartAnimation.invoke()
                }

                override fun onAnimationEnd(p0: Animator?) {
                    callBackEndAnimation.invoke()
                }

                override fun onAnimationCancel(p0: Animator?) {}

                override fun onAnimationRepeat(p0: Animator?) {}

            })
            this.duration = duration
            this.start()
        }
    }

    fun getMobileIPAddress(): String? {
        try {
            val interfaces: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())
            for (item in interfaces) {
                val address: List<InetAddress> = Collections.list(item.inetAddresses)
                for (add in address) {
                    if (!add.isLoopbackAddress) {
                        return add.hostAddress
                    }
                }
            }
        } catch (ex: Exception) { }
        return ""
    }

    fun getScreen(context: Context): DisplayMetrics {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(dm)
        return dm
    }

    fun milliSecondsToSeconds(milliSeconds: Long): Int =
        TimeUnit.MILLISECONDS.toSeconds(milliSeconds).toInt()

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}

