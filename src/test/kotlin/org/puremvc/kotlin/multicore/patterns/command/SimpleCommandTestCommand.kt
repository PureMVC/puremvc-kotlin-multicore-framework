//
//  SimpleCommandTestCommand.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
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