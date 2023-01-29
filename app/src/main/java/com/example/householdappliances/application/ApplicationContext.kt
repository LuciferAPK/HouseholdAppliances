package com.example.householdappliances.application

import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.model.Customer

object ApplicationContext {
    private val sessionContext = SessionContext()
    var customer : Customer ?= null
    var cart : Cart?= null
    fun sessionContext() : SessionContext = sessionContext
}