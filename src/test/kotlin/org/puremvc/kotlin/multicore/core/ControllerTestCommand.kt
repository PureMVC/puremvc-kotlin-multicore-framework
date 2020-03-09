//
//  ControllerTestCommand.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.core

import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.patterns.command.SimpleCommand

/**
 * A SimpleCommand subclass used by ControllerTest.
 *
 * @see ControllerTest ControllerTest
 * @see ControllerTestVO ControllerTestVO
 */
class ControllerTestCommand : SimpleCommand() {

    /**
     * Fabricate a result by multiplying the input by 2
     *
     * @param notification the note carrying the ControllerTestVO
     */
    override fun execute(notification: INotification) {
        val vo = notification.body as ControllerTestVO

        // Fabricate a result
        vo.result = 2 * vo.input
    }

}