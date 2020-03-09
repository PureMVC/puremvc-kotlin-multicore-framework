//
//  ViewTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.core

import org.junit.Assert
import org.junit.Test
import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.patterns.mediator.Mediator
import org.puremvc.kotlin.multicore.patterns.observer.Notification
import org.puremvc.kotlin.multicore.patterns.observer.Observer

/**
 * Test the PureMVC View class.
 */
class ViewTest {

    /**
     * A test variable that proves the viewTestMethod was
     * invoked by the View.
     */
    var viewTestVar = 0

    var lastNotification = ""
    var onRegisterCalled = false
    var onRemoveCalled = false
    var counter = 0

    companion object {
        val NOTE1 = "Notification1"
        val NOTE2 = "Notification2"
        val NOTE3 = "Notification3"
        val NOTE4 = "Notification4"
        val NOTE5 = "Notification5"
        val NOTE6 = "Notification6"
    }

    /**
     * Tests the View Multiton Factory Method
     */
    @Test
    fun testGetInstance() {
        // Test Factory Method
        val view = View.getInstance("ViewTestKey1") { key -> View(key) }

        // test assertions
        Assert.assertNotNull(view)
    }

    /**
     * Tests registration and notification of Observers.
     *
     * <P>An Observer is created to callback the viewTestMethod of
     * this ViewTest instance. This Observer is registered with
     * the View to be notified of 'ViewTestEvent' events. Such
     * an event is created, and a value set on its payload. Then
     * the View is told to notify interested observers of this
     * Event.</P>
     *
     * <P>The View calls the Observer's notifyObserver method
     * which calls the viewTestMethod on this instance
     * of the ViewTest class. The viewTestMethod method will set
     * an instance variable to the value passed in on the Event
     * payload. We evaluate the instance variable to be sure
     * it is the same as that passed out as the payload of the
     * original 'ViewTestEvent'.</P>
     */
    @Test
    fun testRegisterAndNotifyObserver() {
        // Get the Multiton View instance
        val view = View.getInstance("ViewTestKey2") { key -> View(key) }

        // Create observer, passing in notification method and context
        val observer = Observer(this::viewTestMethod, this)

        // Register Observer's interest in a particular Notification with the View
        view.registerObserver(ViewTestNote.NAME, observer)

        // Create a ViewTestNote, setting
        // a body value, and tell the View to notify
        // Observers. Since the Observer is this class
        // and the notification method is viewTestMethod,
        // successful notification will result in our local
        // viewTestVar being set to the value we pass in
        // on the note body.
        val note = ViewTestNote(ViewTestNote.NAME, 10);
        view.notifyObservers(note)

        // test assertions
        Assert.assertEquals(viewTestVar, 10)
    }

    /**
     * A utility method to test the notification of Observers by the view
     */
    fun viewTestMethod(note: INotification) {
        viewTestVar = note.body as Int
    }

    /**
     * Tests registering and retrieving a mediator with
     * the View.
     */
    @Test
    fun testRegisterAndRetrieveMediator() {
        // Get the Multiton View instance
        val view = View.getInstance("ViewTestKey3") { key -> View(key) }

        // Create and register the test mediator
        val viewTestMediator = ViewTestMediator(this)
        view.registerMediator(viewTestMediator)

        // Retrieve the component
        val mediator = view.retrieveMediator(ViewTestMediator.NAME)

        // test assertions
        Assert.assertNotNull(mediator as ViewTestMediator)
    }

    /**
     * Tests the hasMediator Method
     */
    @Test
    fun testHasMediator() {
        // Get the Multiton View instance
        val view = View.getInstance("ViewTestKey4") { key -> View(key) }

        // Create and register the test mediator
        val mediator = Mediator("hasMediatorTest", this)
        view.registerMediator(mediator)

        // assert that the view.hasMediator method returns true
        // for that mediator name
        Assert.assertTrue(view.hasMediator("hasMediatorTest"))

        view.removeMediator("hasMediatorTest")

        // assert that the view.hasMediator method returns false
        // for that mediator name
        Assert.assertFalse(view.hasMediator("hasMediatorTest"))
    }

    /**
     * Tests registering and removing a mediator
     */
    @Test
    fun testRegisterAndRemoveMediator() {
        // Get the Multiton View instance
        val view = View.getInstance("ViewTestKey5") { key -> View(key) }

        // Create and register the test mediator
        val mediator = Mediator("testing", this)
        view.registerMediator(mediator)

        // Remove the component
        val removedMediator = view.removeMediator("testing")

        // assert that we have removed the appropriate mediator
        Assert.assertEquals("testing", removedMediator!!.name)

        // assert that the mediator is no longer retrievable
        Assert.assertNull(view.removeMediator("testing"))
    }

    /**
     * Tests that the View callse the onRegister and onRemove methods
     */
    @Test
    fun testOnRegisterAndOnRemove() {
        // Get the Multiton View instance
        val view = View.getInstance("ViewTestKey6") { key -> View(key) }

        // Create and register the test mediator
        val mediator = ViewTestMediator4(this)
        view.registerMediator(mediator)

        // assert that onRegister was called, and the mediator responded by setting our boolean
        Assert.assertTrue(onRegisterCalled)

        // Remove the component
        view.removeMediator(ViewTestMediator4.NAME)

        // assert that onRemove was called, and the mediator responded by setting our boolean
        Assert.assertTrue(onRemoveCalled)
    }

    /**
     * Tests successive register and remove of same mediator.
     */
    @Test
    fun testSuccessiveRegisterAndRemoveMediator() {
        // Get the Multiton View instance
        val view = View.getInstance("ViewTestKey7") { key -> View(key) }

        // Create and register the test mediator,
        // but not so we have a reference to it
        view.registerMediator(ViewTestMediator(this))

        // test that we can retrieve it
        Assert.assertNotNull(view.retrieveMediator(ViewTestMediator.NAME) as ViewTestMediator)

        // Remove the Mediator
        view.removeMediator(ViewTestMediator.NAME)

        // test that retrieving it now returns null
        Assert.assertNull(view.retrieveMediator(ViewTestMediator.NAME))

        // test that removing the mediator again once its gone doesn't cause crash
        Assert.assertNull(view.retrieveMediator(ViewTestMediator.NAME))

        // Create and register another instance of the test mediator,
        view.registerMediator(ViewTestMediator(this))

        Assert.assertNotNull(view.retrieveMediator(ViewTestMediator.NAME))

        // Remove the Mediator
        view.removeMediator(ViewTestMediator.NAME)

        // test that retrieving it now returns null
        Assert.assertNull(view.retrieveMediator(ViewTestMediator.NAME))
    }

    /**
     * Tests registering a Mediator for 2 different notifications, removing the
     * Mediator from the View, and seeing that neither notification causes the
     * Mediator to be notified. Added for the fix deployed in version 1.7
     */
    @Test
    fun testRemoveMediatorAndSubsequentNotify() {
        // Get the Multiton View instance
        val view = View.getInstance("ViewTestKey8") { key -> View(key) }

        // Create and register the test mediator to be removed.
        view.registerMediator(ViewTestMediator2(this))

        // test that notifications work
        view.notifyObservers(Notification(NOTE1))
        Assert.assertEquals(NOTE1, lastNotification)

        view.notifyObservers(Notification(NOTE2))
        Assert.assertEquals(NOTE2, lastNotification)

        // Remove the Mediator
        view.removeMediator(ViewTestMediator2.NAME)

        // test that retrieving it now returns null
        Assert.assertNull(view.retrieveMediator(ViewTestMediator2.NAME))

        // test that notifications no longer work
        // (ViewTestMediator2 is the one that sets lastNotification
        // on this component, and ViewTestMediator)
        lastNotification = ""

        view.notifyObservers(Notification(NOTE1))
        Assert.assertTrue(lastNotification != NOTE1)

        view.notifyObservers(Notification(NOTE2))
        Assert.assertTrue(lastNotification != NOTE2)
    }

    /**
     * Tests registering one of two registered Mediators and seeing
     * that the remaining one still responds.
     */
    @Test
    fun testRemoveOneOfTwoMediatorsAndSubsequentNotify() {
        // Get the Multiton View instance
        val view = View.getInstance("ViewTestKey9") { key -> View(key) }

        // Create and register that responds to notifications 1 and 2
        view.registerMediator(ViewTestMediator2(this))

        // Create and register that responds to notification 3
        view.registerMediator(ViewTestMediator3(this))

        // test that all notifications work
        view.notifyObservers(Notification(NOTE1))
        Assert.assertEquals(NOTE1, lastNotification)

        view.notifyObservers(Notification(NOTE2))
        Assert.assertEquals(NOTE2, lastNotification)

        view.notifyObservers(Notification(NOTE3))
        Assert.assertEquals(NOTE3, lastNotification)

        // Remove the Mediator that responds to 1 and 2
        view.removeMediator(ViewTestMediator2.NAME)

        // test that retrieving it now returns null
        Assert.assertNull(view.retrieveMediator(ViewTestMediator2.NAME))

        // test that notifications no longer work
        // for notifications 1 and 2, but still work for 3
        lastNotification = ""

        view.notifyObservers(Notification(NOTE1))
        Assert.assertNotEquals(NOTE1, lastNotification)

        view.notifyObservers(Notification(NOTE2))
        Assert.assertNotEquals(NOTE2, lastNotification)

        view.notifyObservers(Notification(NOTE3))
        Assert.assertEquals(NOTE3, lastNotification)
    }

    /**
     * Tests registering the same mediator twice.
     * A subsequent notification should only illicit
     * one response. Also, since reregistration
     * was causing 2 observers to be created, ensure
     * that after removal of the mediator there will
     * be no further response.
     */
    @Test
    fun testMediatorReregistration() {
        // Get the Singleton View instance
        val view = View.getInstance("ViewTestKey10") { key -> View(key) }

        // Create and register that responds to notification 5
        view.registerMediator(ViewTestMediator5(this))

        // try to register another instance of that mediator (uses the same NAME constant).
        view.registerMediator(ViewTestMediator5(this))

        // test that the counter is only incremented once (mediator 5's response)
        counter = 0
        view.notifyObservers(Notification(NOTE5))
        Assert.assertEquals(1, counter)

        // Remove the Mediator
        view.removeMediator(ViewTestMediator5.NAME)

        // test that retrieving it now returns null
        Assert.assertNull(view.retrieveMediator(ViewTestMediator5.NAME))

        // test that the counter is no longer incremented
        counter = 0
        view.notifyObservers(Notification(NOTE5))
        Assert.assertEquals(0, counter)
    }

    /**
     * Tests the ability for the observer list to
     * be modified during the process of notification,
     * and all observers be properly notified. This
     * happens most often when multiple Mediators
     * respond to the same notification by removing
     * themselves.
     */
    @Test
    fun testModifyObserverListDuringNotification() {
        // Get the Singleton View instance
        val view = View.getInstance("ViewTestKey11") { key -> View(key) }

        // Create and register several mediator instances that respond to notification 6
        // by removing themselves, which will cause the observer list for that notification
        // to change.
        view.registerMediator((ViewTestMediator6(ViewTestMediator6.NAME + "/1", this)))
        view.registerMediator((ViewTestMediator6(ViewTestMediator6.NAME + "/2", this)))
        view.registerMediator((ViewTestMediator6(ViewTestMediator6.NAME + "/3", this)))
        view.registerMediator((ViewTestMediator6(ViewTestMediator6.NAME + "/4", this)))
        view.registerMediator((ViewTestMediator6(ViewTestMediator6.NAME + "/5", this)))
        view.registerMediator((ViewTestMediator6(ViewTestMediator6.NAME + "/6", this)))
        view.registerMediator((ViewTestMediator6(ViewTestMediator6.NAME + "/7", this)))
        view.registerMediator((ViewTestMediator6(ViewTestMediator6.NAME + "/8", this)))

        // clear the counter
        counter = 0;

        // send the notification. each of the above mediators will respond by removing
        // themselves and incrementing the counter by 1. This should leave us with a
        // count of 8, since 8 mediators will respond.
        view.notifyObservers(Notification(NOTE6))
        // verify the count is correct
        Assert.assertEquals(8, counter)

        // clear the counter
        counter = 0
        view.notifyObservers(Notification(NOTE6))
        // verify the count is 0
        Assert.assertEquals(0, counter)
    }
}