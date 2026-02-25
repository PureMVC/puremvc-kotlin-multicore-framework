//
//  MacroCommandTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
//

package org.puremvc.kotlin.multicore.patterns.command

import org.puremvc.kotlin.multicore.patterns.observer.Notification
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test the PureMVC SimpleCommand class.
 *
 * @see org.puremvc.kotlin.multicore.patterns.command.MacroCommandTestVO MacroCommandTestVO
 * @see org.puremvc.kotlin.multicore.patterns.command.MacroCommandTestCommand MacroCommandTestCommand
 */
class MacroCommandTest {

    /**
     * Tests operation of a <code>MacroCommand</code>.
     *
     * <P>This test creates a new <code>Notification</code>, adding a
     * <code>MacroCommandTestVO</code> as the body.
     * It then creates a <code>MacroCommandTestCommand</code> and invokes
     * its <code>execute</code> method, passing in the
     * <code>Notification</code>.<P>
     *
     * <P>The <code>MacroCommandTestCommand</code> has defined an
     * <code>initializeMacroCommand</code> method, which is
     * called automatically by its constructor. In this method
     * the <code>MacroCommandTestCommand</code> adds 2 SubCommands
     * to itself, <code>MacroCommandTestSub1Command</code> and
     * <code>MacroCommandTestSub2Command</code>.
     *
     * <P>The <code>MacroCommandTestVO</code> has 2 result properties,
     * one is set by <code>MacroCommandTestSub1Command</code> by
     * multiplying the input property by 2, and the other is set
     * by <code>MacroCommandTestSub2Command</code> by multiplying
     * the input property by itself.
     *
     * <P>Success is determined by evaluating the 2 result properties
     * on the <code>MacroCommandTestVO</code> that was passed to
     * the <code>MacroCommandTestCommand</code> on the Notification
     * body.</P>
     */
    @Test
    fun testMacroCommandExecute() {
        // Create the VO
        val vo = MacroCommandTestVO(5)

        // Create the Notification (note)
        val note = Notification("MacroCommandTest", vo)

        // Create the SimpleCommand
        val command = MacroCommandTestCommand()
        command.initializeNotifier("test")

        // Execute the SimpleCommand
        command.execute(note)

        // test assertions
        assertEquals(10, vo.result1)
        assertEquals(25, vo.result2)
    }

}