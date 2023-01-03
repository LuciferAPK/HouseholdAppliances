package com.example.householdappliances.application

object ApplicationContext {
    private val sessionContext = SessionContext()

    fun sessionContext() : SessionContext = sessionContext
}