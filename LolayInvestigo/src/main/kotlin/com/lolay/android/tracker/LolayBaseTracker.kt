package com.lolay.android.tracker

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.annotation.VisibleForTesting


open class LolayBaseTracker : LolayTracker {

    private val TAG = LolayBaseTracker::class.java.simpleName
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    lateinit var lastLogMessage: String

    override fun setIdentifier(identifier: String) {
        TODO("Not yet implemented")
    }

    override fun setVersion(version: String?) {
        TODO("Not yet implemented")
    }

    override fun setAge(age: Int) {
        TODO("Not yet implemented")
    }

    override fun setGender(gender: LolayTrackerGender?) {
        TODO("Not yet implemented")
    }

    override fun setState(state: String) {
        TODO("Not yet implemented")
    }

    override fun setZip(zip: String) {
        TODO("Not yet implemented")
    }

    override fun setCampaign(campaign: String) {
        TODO("Not yet implemented")
    }

    override fun setChannel(channel: String) {
        TODO("Not yet implemented")
    }

    override fun setGlobalParameters(globalParameters: Map<Any, Any?>?) {
        TODO("Not yet implemented")
    }

    override fun setGlobalParameter(key: Any, `object`: Any) {
        TODO("Not yet implemented")
    }

    override fun removeGlobalParameter(key: Any) {
        TODO("Not yet implemented")
    }

    override fun logRegistration(context: Context?, registrationData: Map<Any, Any?>) {
        lastLogMessage = TAG + "logRegistration"
    }

    override fun logPurchase(context: Context?, purchaseData: Map<Any, Any?>) {
        lastLogMessage = TAG + "logPurchase"
    }

    override fun logTiming(context: Context?, timingData: Map<Any, Any?>) {
        lastLogMessage = TAG + "logTiming"
    }

    override fun logEvent(context: Context, name: String) {
        lastLogMessage = TAG + "logEvent"
    }

    override fun logEventWithParams(context: Context, name: String, parameters: Map<Any, Any?>?) {
        lastLogMessage = TAG + "logEventWithParams"
    }

    override fun logEventWithObjectsAndKeys(
        context: Context,
        name: String,
        vararg objectsAndKeys: Any
    ) {
        lastLogMessage = TAG + "logEventWithObjectsAndKeys"
    }

    override fun logView(context: Context, name: String) {
        lastLogMessage = TAG + "logView"
    }

    override fun logViewWithParams(context: Context, name: String, parameters: Map<Any, Any?>?) {
        lastLogMessage = TAG + "logViewWithParams"
    }

    override fun logViewWithObjectsAndKeys(
        context: Context,
        name: String,
        vararg objectsAndKeys: Any
    ) {
        lastLogMessage = TAG + "logViewWithObjectsAndKeys"
    }

    /**
     * For an Object array of [k1, v1, k2, v2, k3, v3, ...], take each k[i]/v[i]
     * @param objectsAndKeys
     * @return
     */
    fun buildParamsFromObjectArray(vararg objectsAndKeys: Any): Map<Any, Any?>? {
        val length = objectsAndKeys.size / 2
        val parameters: MutableMap<Any, Any> = HashMap(length)
        for (i in 0 until length) {
            val keyIndex = 2 * i
            val valueIndex = keyIndex + 1
            val key = objectsAndKeys[keyIndex]
            val value = objectsAndKeys[valueIndex]
            if (key != null) {
                parameters[key] = value
            }
        }
        return parameters
    }

    override fun logException(context: Context, exception: Throwable) {
        lastLogMessage = TAG + "logException(1)"
    }

    override fun logException(
        context: Context,
        errorId: String,
        message: String,
        throwable: Throwable
    ) {
        lastLogMessage = TAG + "logException(2)"
    }

    override fun getTrackerId(): String? {
        return null;
    }

    override fun getTrackerId(clazz: Class<*>): String? {
        return if (clazz.isInstance(this)) {
            getTrackerId()
        } else {
            null
        }
    }


    // Activity Lifecycle Events
    override fun onCreate(activity: Activity?, savedInstanceState: Bundle?) {
        //TODO("Not yet implemented")
    }

    override fun onStart(activity: Activity?) {
        //TODO("Not yet implemented")
    }

    override fun onRestart(activity: Activity?) {
        //TODO("Not yet implemented")
    }

    override fun onResume(activity: Activity?) {
        //TODO("Not yet implemented")
    }

    override fun onPause(activity: Activity?) {
        //TODO("Not yet implemented")
    }

    override fun onStop(activity: Activity?) {
        //TODO("Not yet implemented")
    }

    override fun onDestroy(activity: Activity?) {
        //TODO("Not yet implemented")
    }

}