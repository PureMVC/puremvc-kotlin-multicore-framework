//
//  ControllerTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.core

import org.junit.Assert
import org.junit.Test
import org.puremvc.kotlin.multicore.patterns.observer.Notification

/**
 * Test the PureMVC Controller class.
 *
 * @see org.puremvc.kotlin.multicore.core.ControllerTestVO ControllerTestVO
 * @see org.puremvc.kotlin.multicore.core.ControllerTestCommand ControllerTestCommand
 */
class ControllerTest {

    /**
     * Tests the Controller Multiton Factory Method
     */
    @Test
    fun testGetInstance() {
        // Test Factory Method
        val controller = Controller.getInstance("ControllerTestKey1") { key -> Controller(key) }

        // test assertions
        Assert.assertNotNull(controller)
    }

    /**
     * Tests Command registration and execution.
     *
     * <P>This test gets a Multiton Controller instance
     * and registers the ControllerTestCommand class
     * to handle 'ControllerTest' Notifications.<P>
     *
     * <P>It then constructs such a Notification and tells the
     * Controller to execute the associated Command.
     * Success is determined by evaluating a property
     * on an object passed to the Command, which will
     * be modified when the Command executes.</P>
     */
    @Test
    fun testRegisterAndExecuteCommand() {
        // Create the controller, register the ControllerTestCommand to handle 'ControllerTest' notes
        val controller = Controller.getInstance("ControllerTestKey2") { key -> Controller(key) }
        controller.registerCommand("ControllerTest") { ControllerTestCommand() }

        // Create a 'ControllerTest' note
        val vo = ControllerTestVO(12)
        val note = Notification("ControllerTest", vo)

        // Tell the controller to execute the Command associated with the note
        // the ControllerTestCommand invoked will multiply the vo.input value
        // by 2 and set the result on vo.result
        controller.executeCommand(note)

        // test assertions
        Assert.assertEquals(24, vo.result)
    }

    /**
     * Tests Command registration and removal.
     *
     * <P>Tests that once a Command is registered and verified
     * working, it can be removed from the Controller.</P>
     */
    @Test
    fun testRegisterAndRemoveCommand() {
        // Create the controller, register the ControllerTestCommand to handle 'ControllerTest' notes
        val controller = Controller.getInstance("ControllerTestKey3") { key -> Controller(key) }
        controller.registerCommand("ControllerRemoveTest") { ControllerTestCommand() }

        // Create a 'ControllerTest' note
        val vo = ControllerTestVO(12)
        val note = Notification("ControllerRemoveTest", vo)

        // Tell the controller to execute the Command associated with the note
        // the ControllerTestCommand invoked will multiply the vo.input value
        // by 2 and set the result on vo.result
        controller.executeCommand(note)

        // test assertions
        Assert.assertEquals(24, vo.result)

        // Reset result
        vo.result = 0

        // Remove the Command from the Controller
        controller.removeCommand("ControllerRemoveTest")

        // Tell the controller to execute the Command associated with the
        // note. This time, it should not be registered, and our vo result
        // will not change
        controller.executeCommand(note)

        // test assertions
        Assert.assertEquals(0, vo.result)
    }

    /**
     * Test hasCommand method.
     */
    @Test
    fun testHasCommand() {
        // register the ControllerTestCommand to handle 'hasCommandTest' notes
        val controller = Controller.getInstance("ControllerTestKey4") { key -> Controller(key) }
        controller.registerCommand("hasCommandTest") { ControllerTestCommand() }

        // test that hasCommand returns true for hasCommandTest notifications
        Assert.assertTrue(controller.hasCommand("hasCommandTest"))

        // Remove the Command from the Controller
        controller.removeCommand("hasCommandTest")

        // test that hasCommand returns false for hasCommandTest notifications
        Assert.assertFalse(controller.hasCommand("hasCommandTest"))
    }

    /**
     * Tests Removing and Reregistering a Command
     *
     * <P>Tests that when a Command is re-registered that it isn't fired twice.
     * This involves, minimally, registration with the controller but
     * notification via the View, rather than direct execution of
     * the Controller's executeCommand method as is done above in
     * testRegisterAndRemove. </P>
     */
    @Test
    fun testReRegisterAndExecuteCommand() {
        // Fetch the controller, register the ControllerTestCommand2 to handle 'ControllerTest2' notes
        val controller = Controller.getInstance("ControllerTestKey5") { key -> Controller(key) }
        controller.registerCommand("ControllerTest2") { ControllerTestCommand2() }

        // Remove the Command from the Controller
        controller.removeCommand("ControllerTest2")

        // Re-register the Command with the Controller
        controller.registerCommand("ControllerTest2") { ControllerTestCommand2() }

        // Create a 'ControllerTest2' note
        val vo = ControllerTestVO(12)
        val note = Notification("ControllerTest2", vo)

        // retrieve a reference to the View from the same core.
        val view = View.getInstance("ControllerTestKey5") { key -> View(key) }

        // send the Notification
        view.notifyObservers(note)

        // test assertions
        // if the command is executed once the value will be 24
        Assert.assertEquals(24, vo.result)

        // Prove that accumulation works in the VO by sending the notification again
        view.notifyObservers(note)

        // if the command is executed twice the value will be 48
        Assert.assertEquals(48, vo.result)
    }

}