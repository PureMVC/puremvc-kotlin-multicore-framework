//
//  FacadeTestCommand.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.facade

import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.patterns.command.SimpleCommand

/**
 * A SimpleCommand subclass used by FacadeTest.
 *
 * @see FacadeTest FacadeTest
 * @see FacadeTestVO FacadeTestVO
 */
class FacadeTestCommand : SimpleCommand() {

    /**
     * Fabricate a result by multiplying the input by 2
     *
     * @param notification the Notification carrying the FacadeTestVO
     */
    override fun execute(notification: INotification) {
        val vo = notification.body as FacadeTestVO

        // Fabricate a result
        vo.result = 2 * vo.input
    }

}