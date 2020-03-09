//
//  SimpleCommandTestCommand.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.command

import org.puremvc.kotlin.multicore.interfaces.INotification

/**
 * A SimpleCommand subclass used by SimpleCommandTest.
 *
 * @see SimpleCommandTest SimpleCommandTest
 * @see SimpleCommandTestVO SimpleCommandTestVO
 */
class SimpleCommandTestCommand : SimpleCommand() {

    /**
     * Fabricate a result by multiplying the input by 2
     *
     * @param notification the <code>INotification</code> carrying the <code>SimpleCommandTestVO</code>
     */
    override fun execute(notification: INotification) {
        super.execute(notification)

        val vo: SimpleCommandTestVO = notification.body as SimpleCommandTestVO

        // Fabricate a result
        vo.result = 2 * vo.input
    }

}