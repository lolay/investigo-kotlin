package com.lolay.android.tracker

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log

class LolayMultipleTracker(private val trackers: Collection<LolayTracker>) : LolayBaseTracker() {
    init {
        Log.i(TAG, String.format("Intialized with %s trackers", trackers.size))
    }

    override fun setIdentifier(identifier: String) {
        for (tracker in trackers) {
            tracker.setIdentifier(identifier)
        }
    }

    override fun setVersion(version: String?) {
        for (tracker in trackers) {
            tracker.setVersion(version)
        }
    }

    override fun setAge(age: Int) {
        for (tracker in trackers) {
            tracker.setAge(age)
        }
    }

    override fun setGender(gender: LolayTrackerGender?) {
        for (tracker in trackers) {
            tracker.setGender(gender)
        }
    }

    override fun setState(state: String) {
        for (tracker in trackers) {
            tracker.setState(state)
        }
    }

    override fun setZip(zip: String) {
        for (tracker in trackers) {
            tracker.setZip(zip)
        }
    }

    override fun setCampaign(campaign: String) {
        for (tracker in trackers) {
            tracker.setCampaign(campaign)
        }
    }

    override fun getTrackerId(clazz: Class<*>): String? {
        for (tracker in trackers) {
            val id = tracker.getTrackerId(clazz)
            if (id != null) {
                return id
            }
        }
        return null
    }

    override fun setGlobalParameters(globalParameters: Map<Any, Any?>?) {
        for (tracker in trackers) {
            tracker.setGlobalParameters(globalParameters)
        }
    }

    override fun setGlobalParameter(key: Any, `object`: Any) {
        for (tracker in trackers) {
            tracker.setGlobalParameter(key, `object`)
        }
    }

    override fun removeGlobalParameter(key: Any) {
        for (tracker in trackers) {
            tracker.removeGlobalParameter(key)
        }
    }

    override fun logTiming(context: Context?, timingData: Map<Any, Any?>) {
        for (tracker in trackers) {
            tracker.logTiming(context, timingData)
        }
    }

    override fun logEvent(context: Context, name: String) {
        for (tracker in trackers) {
            tracker.logEvent(context, name)
        }

    }

    override fun logEventWithParams(context: Context, name: String, parameters: Map<Any, Any?>?) {
        for (tracker in trackers) {
            tracker.logEventWithParams(context, name, parameters)
        }

    }

    override fun logRegistration(context: Context?, parameters: Map<Any, Any?>) {
        for (tracker in trackers) {
            tracker.logRegistration(context, parameters)
        }

    }

    override fun logPurchase(context: Context?, parameters: Map<Any, Any?>) {
        for (tracker in trackers) {
            tracker.logPurchase(context, parameters)
        }

    }

    override fun logView(context: Context, name: String) {
        for (tracker in trackers) {
            tracker.logView(context, name)
        }

    }

    override fun logViewWithParams(context: Context, name: String, parameters: Map<Any, Any?>?) {
        for (tracker in trackers) {
            tracker.logViewWithParams(context, name, parameters)
        }

    }

    override fun logException(context: Context, throwable: Throwable) {
        for (tracker in trackers) {
            tracker.logException(context, throwable)
        }

    }

    override fun logException(
        context: Context,
        errorId: String,
        message: String,
        throwable: Throwable
    ) {
        for (tracker in trackers) {
            tracker.logException(context, errorId, message, throwable)
        }
    }

    override fun onCreate(activity: Activity?, savedInstanceState: Bundle?) {
        for (tracker in trackers) {
            tracker.onCreate(activity, savedInstanceState)
        }
    }

    override fun onStart(activity: Activity?) {
        for (tracker in trackers) {
            tracker.onStart(activity)
        }
    }

    override fun onRestart(activity: Activity?) {
        for (tracker in trackers) {
            tracker.onRestart(activity)
        }
    }

    override fun onResume(activity: Activity?) {
        for (tracker in trackers) {
            tracker.onResume(activity)
        }
    }

    override fun onPause(activity: Activity?) {
        for (tracker in trackers) {
            tracker.onPause(activity)
        }
    }

    override fun onStop(activity: Activity?) {
        for (tracker in trackers) {
            tracker.onStop(activity)
        }
    }

    override fun onDestroy(activity: Activity?) {
        for (tracker in trackers) {
            tracker.onDestroy(activity)
        }
    }

    companion object {
        private val TAG = LolayMultipleTracker::class.java.simpleName
    }
}
