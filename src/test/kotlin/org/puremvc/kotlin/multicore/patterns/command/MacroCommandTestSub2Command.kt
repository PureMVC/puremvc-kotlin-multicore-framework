//
//  MacroCommandTestSub2Command.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
//

package org.puremvc.kotlin.multicore.patterns.command

import org.puremvc.kotlin.multicore.interfaces.INotification

/**
 * A SimpleCommand subclass used by MacroCommandTestCommand.
 *
 * @see MacroCommandTest MacroCommandTest
 * @see MacroCommandTestCommand MacroCommandTestCommand
 * @see MacroCommandTestVO MacroCommandTestVO
 */
class MacroCommandTestSub2Command : SimpleCommand() {

    /**
     * Fabricate a result by multiplying the input by itself
     *
     * @param notification the <code>IEvent</code> carrying the <code>MacroCommandTestVO</code>
     */
    override fun execute(notification: INotification) {
        val vo = notification.body as MacroCommandTestVO

        // Fabricate a result
        vo.result2 = vo.input * vo.input
    }
}