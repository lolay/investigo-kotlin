package com.lolay.android.tracker

import com.datadog.android.privacy.TrackingConsent

enum class LolayTrackingConsent {
    GRANTED,
    NOT_GRANTED,
    PENDING;

    companion object {
        @JvmStatic
        fun toDataDogConsent(value: LolayTrackingConsent): TrackingConsent {
            return when (value) {
                GRANTED -> TrackingConsent.GRANTED
                NOT_GRANTED-> TrackingConsent.NOT_GRANTED
                PENDING -> TrackingConsent.PENDING
            }
        }

    }
}