//
//  MacroCommandTestSub1Command.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
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
class MacroCommandTestSub1Command : SimpleCommand() {

    /**
     * Fabricate a result by multiplying the input by 2
     *
     * @param notification the <code>IEvent</code> carrying the <code>MacroCommandTestVO</code>
     */
    override fun execute(notification: INotification) {
        val vo = notification.body as MacroCommandTestVO

        // Fabricate a result
        vo.result1 = 2 * vo.input
    }

}