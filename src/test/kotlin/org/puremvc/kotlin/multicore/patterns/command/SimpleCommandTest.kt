//
//  SimpleCommandTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.command

import org.junit.Assert
import org.junit.Test
import org.puremvc.kotlin.multicore.patterns.observer.Notification

/**
 * Test the PureMVC SimpleCommand class.
 *
 * @see org.puremvc.kotlin.multicore.patterns.command.SimpleCommandTestVO SimpleCommandTestVO
 * @see org.puremvc.kotlin.multicore.patterns.command.SimpleCommandTestCommand SimpleCommandTestCommand
 */
class SimpleCommandTest {

    /**
     * Tests the <code>execute</code> method of a <code>SimpleCommand</code>.
     *
     * <P>This test creates a new <code>Notification</code>, adding a
     * <code>SimpleCommandTestVO</code> as the body.
     * It then creates a <code>SimpleCommandTestCommand</code> and invokes
     * its <code>execute</code> method, passing in the note.</P>
     *
     * <P>Success is determined by evaluating a property on the
     * object that was passed on the Notification body, which will
     * be modified by the SimpleCommand</P>.
     *
     */
    @Test
    fun testSimpleCommandExecute() {
        // Create the VO
        val vo = SimpleCommandTestVO(5)

        // Create the Notification (note)
        val note = Notification("SimpleCommandTestNote", vo)

        // Create the SimpleCommand
        val command = SimpleCommandTestCommand()

        // Execute the SimpleCommand
        command.execute(note)

        // test assertions
        Assert.assertEquals(10, vo.result)
    }
}