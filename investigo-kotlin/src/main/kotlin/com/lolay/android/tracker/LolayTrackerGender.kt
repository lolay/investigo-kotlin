package com.lolay.android.tracker

enum class LolayTrackerGender(val code: Int) {
    UNKNOWN(0), MALE(1), FEMALE(2);

    companion object {
        fun fromCode(code: Int): LolayTrackerGender {
            return when (code) {
                1 -> MALE
                2 -> FEMALE
                else -> UNKNOWN
            }
        }
    }
}