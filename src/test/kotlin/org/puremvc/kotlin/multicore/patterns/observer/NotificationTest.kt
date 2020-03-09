//
//  NotificationTest.kt
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
 * Test the PureMVC Notification class.
 *
 * @see Notification Notification
 */
class NotificationTest {

    /**
     * Tests setting and getting the name using Notification class accessor methods.
     */
    @Test
    fun testNameAccessor() {
        // Create a new Notification and use accessors to set the note name
        val note: INotification = Notification("TestNote")

        // test assertions
        Assert.assertEquals(note.name, "TestNote")
    }

    /**
     * Tests setting and getting the body using Notification class accessor methods.
     */
    @Test
    fun testBodyAccessors() {
        // Create a new Notification and use accessors to set the body
        val note: INotification = Notification("TestNote", null)
        note.body = 5

        // test assertions
        Assert.assertEquals(note.body, 5)
    }

    /**
     * Tests setting the name and body using the Notification class Constructor.
     */
    @Test
    fun testConstructor() {
        // Create a new Notification using the Constructor to set the note name and body
        val note: INotification = Notification("TestNote", 5, "TestNoteType")

        // test assertions
        Assert.assertEquals(note.name, "TestNote")
        Assert.assertEquals(note.body, 5)
        Assert.assertEquals(note.type, "TestNoteType")
    }

    /**
     * Tests the toString method of the notification
     */
    @Test
    fun testToString() {
        // Create a new Notification and use accessors to set the note name
        val note: INotification = Notification("TestNote", "1,3,5", "TestType")
        val ts = "Notification Name: TestNote\nBody:1,3,5\nType:TestType"

        // test assertions
        Assert.assertTrue(note.toString() == ts)

    }

}