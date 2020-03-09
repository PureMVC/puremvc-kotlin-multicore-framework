//
//  ObserverTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.observer

import org.junit.Assert
import org.junit.Test
import org.puremvc.kotlin.multicore.interfaces.INotification

/**
 * Tests PureMVC Observer class.
 *
 * <P>Since the Observer encapsulates the interested object's
 * callback information, there are no getters, only setters.
 * It is, in effect write-only memory.</P>
 *
 * <P>Therefore, the only way to test it is to set the
 * notification method and context and call the notifyObserver
 * method.</P>
 *
 */
class ObserverTest {

    /**
     * A test variable that proves the notify method was
     * executed with 'this' as its exectution context
     */
    var observerTestVar: Int = 0

    /**
     * Tests observer class when initialized by accessor methods.
     */
    @Test
    fun testObserverAccessors() {
        // Create observer with null args, then
        // use accessors to set notification method and context
        val observer = Observer(null, null)
        observer.notifyContext = this
        observer.notifyMethod = this::handleNotification

        // create a test event, setting a payload value and notify
        // the observer with it. since the observer is this class
        // and the notification method is observerTestMethod,
        // successful notification will result in our local
        // observerTestVar being set to the value we pass in
        // on the note body.
        val note = Notification("ObserverTestNote", 10)
        observer.notifyObserver(note)

        // test assertions
        Assert.assertEquals(10, observerTestVar)
    }

    /**
     * Tests the compareNotifyContext method of the Observer class
     */
    @Test
    fun testCompareNotifyContext() {
        // Create observer passing in notification method and context
        val observer = Observer(this::handleNotification, this)

        val negTestObj = Any()

        // test assertions
        Assert.assertFalse(observer.compareNotifyContext(negTestObj))
        Assert.assertTrue(observer.compareNotifyContext(this))
    }

    /**
     * A function that is used as the observer notification
     * method. It multiplies the input number by the
     * observerTestVar value
     */
    fun handleNotification(notification: INotification) {
        observerTestVar = notification.body as Int
    }

}