package com.lolay.android.tracker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder
import com.google.android.gms.analytics.Tracker
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class LolayFirebaseAnalyticsTracker(context: Context) : LolayBaseTracker() {

    private var analytics: FirebaseAnalytics
    private var tracker: Tracker
    private val platform: String
    private var globalParametersValue: Map<Any, Any>? = null

    init {
        platform = clientPlatform()
        analytics = Firebase.analytics
        // TODO: Just noticed this is GoogleAnalytics, needs a tracking ID
        tracker = GoogleAnalytics.getInstance(context).newTracker("UA-XXXXXXXXX-X")
        if (analytics == null) {
            analytics = Firebase.analytics
        }
    }

    override fun setIdentifier(identifier: String) {
        // You only need to set User ID on a tracker once. By setting it on the tracker, the ID will be
        // sent with all subsequent hits.
        analytics.setUserProperty("&uid", identifier)
    }

    fun setGlobalParametersValue(globalParametersValue: Map<Any, Any>?) {
        this.globalParametersValue = globalParametersValue
    }

    override fun logEvent(context: Context, name: String) {
        tracker.send(
            HitBuilders.EventBuilder()
                .setCategory("events")
                .setAction(name)
                .setLabel("label")
                .setValue(0)
                .build()
        )
    }

    override fun logEventWithParams(context: Context, name: String, parameters: Map<Any, Any?>?)  {
        tracker.send(
            HitBuilders.EventBuilder()
                .setCategory("events")
                .setAction(name)
                .setLabel("label")
                .setValue(0)
                .build()
        )
    }

    override fun logEventWithObjectsAndKeys(
        context: Context,
        name: String,
        vararg objectsAndKeys: Any
    )  {
        tracker.send(
            HitBuilders.EventBuilder()
                .setCategory("events")
                .setAction(name)
                .setLabel("label")
                .setValue(0)
                .build()
        )

    }

    override fun logView(context: Context, name: String)  {
        tracker.setScreenName(name)
        tracker.send(ScreenViewBuilder().build())  // TODO: AppViewBuilder into ScreenViewBUilder
    }

    override fun logViewWithParams(context: Context, name: String, parameters: Map<Any, Any?>?) {
        tracker.setScreenName(name)
        tracker.send(ScreenViewBuilder().build())
    }

    override fun logViewWithObjectsAndKeys(
        context: Context,
        name: String,
        vararg objectsAndKeys: Any
    ) {
        tracker.setScreenName(name)
        tracker.send(ScreenViewBuilder().build())

    }


    override fun logException(context: Context, throwable: Throwable){
        tracker.send (
            HitBuilders.ExceptionBuilder()
                .setDescription(throwable.javaClass.simpleName + ":" + throwable.message)
                .setFatal(false)
                .build()
        )

    }

    override fun logException(
        context: Context,
        errorId: String,
        message: String,
        throwable: Throwable
    ) {
        tracker.send(
            HitBuilders.ExceptionBuilder()
                .setDescription(String.format("(%s): %s", errorId, message))
                .setFatal(false)
                .build()
        )

    }

    @SuppressLint("VisibleForTests")
    override fun logTiming(context: Context?, timingData: Map<Any, Any?>)  {
        require(timingData.containsKey("category")) { "logTiming requires category key" }
        require(timingData.containsKey("value")) { "logTiming requires value key" }
        require(timingData.containsKey("name")) { "logTiming requires name key" }
        require(timingData.containsKey("label")) { "logTiming requires label key" }
        tracker.send(
            HitBuilders.TimingBuilder()
                .setCategory(timingData["category"] as String)
                .setValue(timingData["value"] as Long)
                .setVariable(timingData["name"] as String)
                .setLabel(timingData["label"] as String)
                .build()
        )

    }

    override fun logPurchase(context: Context?, purchaseData: Map<Any, Any?>)  {
        require(purchaseData.containsKey("transactionId")) { "logPurchase requires transactionId key" }
        require(purchaseData.containsKey("productName")) { "logPurchase requires productName key" }
        require(purchaseData.containsKey("productId")) { "logPurchase requires productId key" }
        require(purchaseData.containsKey("categoryName")) { "logPurchase requires categoryName key" }
        require(purchaseData.containsKey("price")) { "logPurchase requires price key" }
        require(purchaseData.containsKey("quantity")) { "logPurchase requires quantity key" }
        require(purchaseData.containsKey("currency")) { "logPurchase requires currency key" }
        // This class has been deprecated in favor of a richer set of APIs on all the HitBuilder
        // classes. With the new approach, simply use addProduct, addImpression, addPromo and
        // setAction to add ecommerce data to any of the hits.
        tracker.send(
            HitBuilders.ItemBuilder()
                .setTransactionId(purchaseData["transactionId"] as String)
                .setName(purchaseData["productName"] as String)
                .setSku(purchaseData["productId"] as String)
                .setCategory(purchaseData["categoryName"] as String)
                .setPrice(purchaseData["price"] as Double)
                .setQuantity(purchaseData["quantity"] as Long)
                .setCurrencyCode(purchaseData["currency"] as String)
                .build()
        )
    }

    override fun logRegistration(context: Context?, registrationData: Map<Any, Any?>)  {
        require(registrationData.containsKey("userName")) { "logRegistration requires userName key" }
        val userName =
            if (registrationData.containsKey("userName")) registrationData["userName"] as String? else null
        setIdentifier(userName!!)
        tracker.send(
            HitBuilders.EventBuilder()
                .setCategory("UX")
                .setAction("User Register")
                .setLabel("")       // TODO: was null
                .setValue(0)
                .build()
        )
    }

    private fun clientPlatform(): String {
        val manufacturer: String = Build.MANUFACTURER
        val product: String = Build.PRODUCT
        val model: String = Build.MODEL
        val systemVersion: String = Build.VERSION.RELEASE
        val sdk: Int = Build.VERSION.SDK_INT
        return String.format("%s %s (%s): %s %d", manufacturer, product, model, systemVersion, sdk)
    }

    override fun onStart(activity: Activity?) {
        super.onStart(activity)
    }

    override fun onStop(activity: Activity?) {
        super.onStop(activity)
    }

    companion object {
        private val TAG = LolayFirebaseAnalyticsTracker::class.java.simpleName
        private val instance: LolayFirebaseAnalyticsTracker? = null
    }
}