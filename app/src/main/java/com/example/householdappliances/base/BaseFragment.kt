package com.example.householdappliances.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Created by thevu2907@gmail.com on 22/02/2022.
 */
abstract class BaseFragment<BINDING : ViewDataBinding> :
    Fragment() {
    lateinit var binding: BINDING
    lateinit var loadingDialog : PopupWindow
    lateinit var  popupLayoutReject: View
    private var timeStartLoading = 0L
    private var timeDelayLoading = 1 //second

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        popupLayoutReject = inflater.inflate(R.layout.layout_loading, null)
//        loadingDialog = PopupWindow(
//            popupLayoutReject, WindowManager.LayoutParams.FILL_PARENT,
//            WindowManager.LayoutParams.WRAP_CONTENT, false
//        )
//        loadingDialog.isOutsideTouchable = false
//        val icLoading= popupLayoutReject.findViewById<LinearLayout>(R.id.ll_loading_dialog)
//        icLoading.addView(CustomIconLoadingDialog(requireContext(),null))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getContentLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getLayoutLoading()
        initListener()
        observerLiveData()
    }

    abstract fun getContentLayout(): Int

    abstract fun initView()

    abstract fun initListener()

    abstract fun observerLiveData()

    abstract fun getLayoutLoading(): View?

//    protected fun paddingStatusBar(view: View) {
//        view.setPadding(0, StatusBarApp.getStatusBarHeight(requireContext()), 0, 0)
//    }

//    protected fun showLoading(isShow: Boolean) {
//        if (getLayoutLoading() != null) {
//            checkConnectInternet(
//                callbackIfConnected = {
//                    if (isShow) {
//                        getLayoutLoading()?.visibility = View.VISIBLE
//                        if (getLayoutLoading() is ShimmerFrameLayout) {
//                            (getLayoutLoading() as ShimmerFrameLayout).apply {
//                                showShimmer(true)
//                                startShimmer()
//                            }
//                        }
//                    } else {
//                        if (getLayoutLoading() is ShimmerFrameLayout) {
//                            (getLayoutLoading() as ShimmerFrameLayout).apply {
//                                stopShimmer()
//                                hideShimmer()
//                            }
//                        }
//                        getLayoutLoading()?.visibility = View.GONE
//                    }
//                },
//                callbackNoInternet = {
//                    if (getLayoutLoading() is ShimmerFrameLayout) {
//                        (getLayoutLoading() as ShimmerFrameLayout).apply {
//                            stopShimmer()
//                            hideShimmer()
//                        }
//                    }
//                    getLayoutLoading()?.visibility = View.GONE
//                }
//            )
//        } else {
//            if (isShow) loadingDialog.showAtLocation(popupLayoutReject, Gravity.CENTER, 0, 0)
//            else if (loadingDialog.isShowing) loadingDialog.dismiss()
//        }
//
//    }

    override fun onResume() {
        super.onResume()
        checkConnectInternet({}, {})
    }

    protected fun <T : Any> handleResultWithLoading(result: Result<T>, onSuccess: (response: T) -> Unit) {
        when(result) {
            is Result.InProgress -> {
                Log.d("GET_ALL_CATEGORY","IN PROGRESS")
            }
            is Result.Success<T> -> {
//                val timeLoading = CommonUtils.milliSecondsToSeconds(System.currentTimeMillis() - timeStartLoading)
//                if (timeLoading < timeDelayLoading) {
//                    val timeDelayContinue: Long = ((timeDelayLoading - timeLoading) * 1000).toLong()
//                    CoroutineExt.runOnMainAfterDelay(timeDelayContinue) {
//                        showLoading(false)
                        onSuccess.invoke(result.data)
//                    }
//                }
//                else {
//                    showLoading(false)
//                    onSuccess.invoke(result.data)
//                }
            }
            is Result.Failure -> {
//                showLoading(false)
                showErrorSnackBar(result.toString())
            }
            is Result.Error -> {
//                showLoading(false)
                showErrorSnackBar(result.toString())
            }
            is Result.Failures<*> -> {
//                showLoading(false)
                showErrorSnackBar(result.toString())
            }
        }
    }

    protected fun <T : Any> handleResultWithoutLoading(result: Result<T>, onSuccess: (response: T) -> Unit) {
        when(result) {
            is Result.InProgress -> {
            }
            is Result.Success<T> -> {
                onSuccess.invoke(result.data)
            }
            is Result.Failure -> {
                showErrorSnackBar(result.toString())
            }
            is Result.Error -> {
                showErrorSnackBar(result.toString())
            }
            is Result.Failures<*> -> {
                showErrorSnackBar(result.toString())
            }
        }
    }

//    private fun showErrorDialog(@StringRes id: Int) {
//        if (loadingDialog.isShowing) loadingDialog.dismiss()
//        ErrorDialog(resources.getString(id)).show(parentFragmentManager, "error_dialog")
//    }

    private fun showErrorSnackBar(@StringRes id: Int) {
        Snackbar.make(binding.root, id, Snackbar.LENGTH_LONG).show()
    }

    private fun showErrorDialog(error: String) {
        if (loadingDialog.isShowing) loadingDialog.dismiss()
//        ErrorDialog(error).show(parentFragmentManager, "error_dialog")
    }

    private fun showErrorSnackBar(error: String) {
        (activity as BaseActivity<*>).showErrorSnackBar(error)
    }

    fun checkConnectInternet(callbackIfConnected: () -> Unit, callbackNoInternet: () -> Unit){
//        (activity as BaseActivity<*>).checkConnectInternet(callbackIfConnected, callbackNoInternet)
    }
    fun setExitSharedElementCallback(key: String, value: View?) {
        (activity as BaseActivity<*>).setExitSharedElementCallback(key, value)
    }
    fun showDialogNoInternet() {
//        (activity as BaseActivity<*>).showDialogNoInternet()
    }
}
