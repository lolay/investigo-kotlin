//
//  Copyright 2011, 2012, 2013 Lolay, Inc.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//
package com.lolay.android.tracker

import android.app.Activity
import android.content.Context
import android.os.Bundle


interface LolayTracker {

    fun setIdentifier(identifier: String)
    fun setVersion(version: String?)
    fun setAge(age: Int)
    fun setGender(gender: LolayTrackerGender?)
    fun setState(state: String)
    fun setZip(zip: String)
    fun setCampaign(campaign: String)
    fun setChannel(channel: String)
    fun setGlobalParameters(globalParameters: Map<Any, Any?>?)
    fun setGlobalParameter(key: Any, `object`: Any)
    fun removeGlobalParameter(key: Any)
    fun logRegistration(context: Context?, registrationData: Map<Any, Any?>)
    fun logPurchase(context: Context?, purchaseData: Map<Any, Any?>)
    fun logTiming(context: Context?, timingData: Map<Any, Any?>)
    fun logEvent(context: Context, name: String)
    fun logEventWithParams(context: Context, name: String, parameters: Map<Any, Any?>?)
    fun logEventWithObjectsAndKeys(context: Context, name: String, vararg objectsAndKeys: Any)
    fun logView(context: Context, name: String)
    fun logViewWithParams(context: Context, name: String, parameters: Map<Any, Any?>?)
    fun logViewWithObjectsAndKeys(context: Context, name: String, vararg objectsAndKeys: Any)
    fun logException(context: Context, exception: Throwable)
    fun logException(context: Context, errorId: String, message: String, throwable: Throwable)
    fun getTrackerId(): String?
    fun getTrackerId(clazz: Class<*>): String?

    // TODO: Activity life cycle hooks.
    fun onCreate(activity: Activity?, savedInstanceState: Bundle?)
    fun onStart(activity: Activity?)
    fun onRestart(activity: Activity?)
    fun onResume(activity: Activity?)
    fun onPause(activity: Activity?)
    fun onStop(activity: Activity?)
    fun onDestroy(activity: Activity?)




}