package com.lolay.android.tracker

import android.content.Context
import com.datadog.android.Datadog
import com.datadog.android.core.configuration.Configuration
import com.datadog.android.core.configuration.Credentials
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.GlobalRum
import com.datadog.android.rum.RumMonitor
import com.datadog.android.tracing.AndroidTracer
import io.opentracing.util.GlobalTracer


class LolayDataDogTraceTracker(
    context: Context,
    configuration: Configuration,
    credentials: Credentials,
    trackingConsent: TrackingConsent
) : LolayBaseTracker() {
    // Overloaded constructor, so the client doesn't have to import the Datadog Configuration / Credentials classes
    constructor(
        context: Context,
        clientToken: String,
        envName: String,
        appVariantName: String,
        applicationId: String,
        trackingConsent: LolayTrackingConsent = LolayTrackingConsent.NOT_GRANTED,
        traceEnabled: Boolean = false,
        rumEnabled: Boolean = false,
        collectLogsEnabled: Boolean = false,
        crashReportEnabled: Boolean = false,
        backgroundTrackingEventEnabled: Boolean = false
    ) : this(
        context = context,
        configuration = Configuration.Builder(
            tracesEnabled = traceEnabled,
            rumEnabled = rumEnabled,
            logsEnabled = collectLogsEnabled,
            crashReportsEnabled = crashReportEnabled,
        ).trackBackgroundRumEvents(backgroundTrackingEventEnabled).build(),
        credentials = Credentials(clientToken, envName, appVariantName, applicationId),
        trackingConsent = LolayTrackingConsent.toDataDogConsent(trackingConsent)
    )



    init {
        Datadog.initialize(context, credentials, configuration, trackingConsent)
        // RUM ENABLED?
        if (1==1) {
            val monitor = RumMonitor.Builder().build()
            GlobalRum.registerIfAbsent(monitor)
        }

        // TODO: setup spans & scopes https://docs.datadoghq.com/tracing/trace_collection/dd_libraries/android/?tab=kotlin
        if (1==1) {
            val tracer = AndroidTracer.Builder().build()
            GlobalTracer.registerIfAbsent(tracer)
        }



    }

}
