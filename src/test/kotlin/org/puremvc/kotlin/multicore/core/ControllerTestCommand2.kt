//
//  ControllerTestCommand2.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
//

package org.puremvc.kotlin.multicore.core

import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.patterns.command.SimpleCommand

class ControllerTestCommand2 : SimpleCommand() {

    /**
     * Fabricate a result by multiplying the input by 2 and adding to the existing result
     *
     * <P>This tests accumulation effect that would show if the command were executed more than once.</P>
     *
     * @param notification the note carrying the ControllerTestVO
     */
    override fun execute(notification: INotification) {
        val vo = notification.body as ControllerTestVO

        // Fabricate a result
        vo.result = vo.result + (2 * vo.input)
    }

}