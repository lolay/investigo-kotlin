package com.lolay.android.tracker

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class LolayLogTrackerTest {

    lateinit var instrumentationContext: Context

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }


    @Test
    fun testLogEvent() {
        val tracker = LolayLogTracker()

        tracker.logEvent(instrumentationContext, "TestEvent")

        // Verify that the correct log message was sent to the console
        verifyConsoleLogOutput(tracker, instrumentationContext::class.simpleName + " Event: TestEvent")
    }

    @Test
    fun testLogEventWithParams() {
        val tracker = LolayLogTracker()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val simpleName = context::class.simpleName

        val params = mapOf(
            "param1" to "value1",
            "param2" to "value2"
        ) as Map<Any, Any?>

        tracker.logEventWithParams(instrumentationContext, "TestEvent", params)

        // Verify that the correct log message was sent to the console, including parameters
        verifyConsoleLogOutput(tracker,instrumentationContext::class.simpleName + " Event: TestEvent?param1=value1&param2=value2")
    }

    @Test
    fun testLogView() {
        val tracker = LolayLogTracker()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val simpleName = context::class.simpleName

        tracker.logView(instrumentationContext, "TestView")

        // Verify that the correct log message was sent to the console
        verifyConsoleLogOutput(tracker,instrumentationContext::class.simpleName + " View: TestView")
    }

    @Test
    fun testLogViewWithParams() {
        val tracker = LolayLogTracker()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val simpleName = context::class.simpleName

        val params = mapOf(
            "param1" to "value1",
            "param2" to "value2"
        ) as Map<Any, Any?>

       tracker.logViewWithParams(instrumentationContext, "TestView", params)

        // Verify that the correct log message was sent to the console, including parameters
        verifyConsoleLogOutput(tracker, instrumentationContext::class.simpleName + " View: TestView?param1=value1&param2=value2")
    }

    @Test
    fun testLogException() {
        val tracker = LolayLogTracker()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val simpleName = context::class.simpleName
        val testException = RuntimeException("Test Exception")

        tracker.logException(instrumentationContext, testException)

        // Verify that the correct log message was sent to the console, including the exception stack trace
        verifyConsoleLogOutput(tracker, instrumentationContext::class.simpleName + " Exception", testException)
    }

    @Test
    fun testLogExceptionWithIdAndMessage() {
        val tracker = LolayLogTracker()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val simpleName = context::class.simpleName
        val testException = RuntimeException("Test Exception")

        tracker.logException(instrumentationContext, "ErrorId", "Test Message", testException)

        // Verify that the correct log message was sent to the console, including the exception stack trace, error ID, and message
        verifyConsoleLogOutput(tracker, instrumentationContext::class.simpleName + " Exception: ErrorId: Test Message", testException)
    }

    private fun verifyConsoleLogOutput(tracker: LolayLogTracker, expectedMessage: String, expectedThrowable: Throwable? = null) {


        if (expectedThrowable == null) {
            Assert.assertEquals(expectedMessage, tracker.lastLogMessage)
        } else {
            Assert.assertEquals(expectedMessage + "\n" + Log.getStackTraceString(expectedThrowable), tracker.lastLogMessage )
        }
    }
}
