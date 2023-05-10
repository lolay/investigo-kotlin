package com.lolay.android.tracker

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LolayTrackerGenderTest  {
    @Test
    fun `fromCode should return the corresponding enum value`() {
        assertEquals(LolayTrackerGender.UNKNOWN, LolayTrackerGender.fromCode(0))
        assertEquals(LolayTrackerGender.MALE, LolayTrackerGender.fromCode(1))
        assertEquals(LolayTrackerGender.FEMALE, LolayTrackerGender.fromCode(2))
    }

    @Test
    fun `fromCode should return UNKNOWN for unknown code`() {
        assertEquals(LolayTrackerGender.UNKNOWN, LolayTrackerGender.fromCode(-1))
        assertEquals(LolayTrackerGender.UNKNOWN, LolayTrackerGender.fromCode(3))
    }
}