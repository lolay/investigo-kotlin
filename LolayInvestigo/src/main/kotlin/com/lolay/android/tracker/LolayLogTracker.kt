package com.lolay.android.tracker

import android.content.Context
import android.util.Log
import kotlin.math.log


class LolayLogTracker : LolayBaseTracker() {

    init {
        i(TAG, String.format("Intialized LolayLogTracker"))
    }

    private fun buildParameters(parameters: Map<Any, Any?>?): String {
        if (parameters == null || parameters.size == 0) {
            return ""
        }
        val builder = StringBuilder()
        builder.append("?")
        var first = true
        for ((key, value) in parameters) {
            if (first) {
                first = false
            } else {
                builder.append("&")
            }
            builder.append(key)
            builder.append("=")
            builder.append(value)
        }
        return builder.toString()
    }

    override fun logEvent(context: Context, name: String) {
        val logMessage = context::class.simpleName + " Event: " + name
        i(TAG, logMessage)
    }

    override fun logEventWithParams(context: Context, name: String, parameters: Map<Any, Any?>?) {
        val logMessage = context::class.simpleName + " Event: " + name + buildParameters(parameters)
        i(TAG, logMessage)
    }

    override fun logView(context: Context, name: String) {
        val logMessage = context::class.simpleName + " View: " + name
        i(TAG, logMessage)
    }

    override fun logViewWithParams(context: Context, name: String, parameters: Map<Any, Any?>?) {
        val logMessage = context::class.simpleName + " View: " + name + buildParameters(parameters)
        i(TAG, logMessage)
    }

    override fun logException(context: Context, throwable: Throwable) {
        val logMessage = context::class.simpleName + " Exception"
        i(TAG, logMessage, throwable)
    }

    override fun logException(
        context: Context,
        errorId: String,
        message: String,
        throwable: Throwable
    ) {
        val logMessage = context::class.simpleName + " Exception: " + errorId + ": " + message
        i(
            TAG,
            logMessage,
            throwable
        )
    }


    private fun wtf(TAG: String, logMessage: String, throwable: Throwable? = null) {
        if (throwable == null) {
            Log.wtf(TAG, logMessage)
            lastLogMessage = logMessage
        } else {
            Log.wtf(TAG, logMessage, throwable)
            lastLogMessage = logMessage + "\n" + Log.getStackTraceString(throwable)
        }
    }

    private fun e(TAG: String, logMessage: String, throwable: Throwable? = null) {
        if (throwable == null) {
            Log.e(TAG, logMessage)
            lastLogMessage = logMessage
        } else {
            Log.e(TAG, logMessage, throwable)
            lastLogMessage = logMessage + "\n" + Log.getStackTraceString(throwable)
        }

    }

    fun w(TAG: String, logMessage: String, throwable: Throwable? = null) {
        if (throwable == null) {
            Log.w(TAG, logMessage)
            lastLogMessage = logMessage
        } else {
            Log.w(TAG, logMessage, throwable)
            lastLogMessage = logMessage + "\n" + Log.getStackTraceString(throwable)
        }
    }

    fun i(TAG: String, logMessage: String, throwable: Throwable? = null) {
        if (throwable == null) {
            Log.i(TAG, logMessage)
            lastLogMessage = logMessage
        } else {
            Log.i(TAG, logMessage, throwable)
            lastLogMessage = logMessage + "\n" + Log.getStackTraceString(throwable)
        }

    }

    fun d(TAG: String, logMessage: String, throwable: Throwable? = null) {
        if (throwable == null) {
            Log.d(TAG, logMessage)
            lastLogMessage = logMessage
        } else {
            Log.d(TAG, logMessage, throwable)
            lastLogMessage = logMessage + "\n" + Log.getStackTraceString(throwable)
        }
    }

    fun v(TAG: String, logMessage: String, throwable: Throwable? = null) {
        if (throwable == null) {
            Log.v(TAG, logMessage)
            lastLogMessage = logMessage
        } else {
            Log.v(TAG, logMessage, throwable)
            lastLogMessage = logMessage + "\n" + Log.getStackTraceString(throwable)
        }
    }

    companion object {
        private val TAG = LolayLogTracker::class.java.simpleName
    }

}