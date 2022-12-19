package com.example.householdappliances.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<BINDING : ViewDataBinding> :
    Fragment() {
    lateinit var binding: BINDING

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getContentLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observerLiveData()
    }

    abstract fun getContentLayout(): Int

    abstract fun initView()

    abstract fun initListener()

    abstract fun observerLiveData()


    protected fun <T : Any> handleResult(result: Result<T>, onSuccess: (response: T) -> Unit) {
        when(result) {
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
