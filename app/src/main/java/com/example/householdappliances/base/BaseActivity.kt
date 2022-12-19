package com.example.householdappliances.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<BINDING : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: BINDING

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = DataBindingUtil.setContentView(this, getContentLayout())
        setContentView(binding.root)
        initView()
        initListener()
        observerLiveData()
    }

    abstract fun getContentLayout(): Int

    abstract fun initView()

    abstract fun initListener()

    abstract fun observerLiveData()

    //clear focus edittext when touch outside
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val view: View? = currentFocus
            if (view is EditText) {
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect)
            }
        }
        return super.dispatchTouchEvent(event)
    }

    protected fun <T : Any> handleResult(
        result: Result<T>,
        onSuccess: (response: T) -> Unit
    ) {
        when (result) {
            is Result.InProgress -> {}
            is Result.Success<T> -> {
                onSuccess.invoke(result.data)
            }
            is Result.Failure -> {}
            is Result.Error -> {}
            is Result.Failures<*> -> {}
        }
    }
}
