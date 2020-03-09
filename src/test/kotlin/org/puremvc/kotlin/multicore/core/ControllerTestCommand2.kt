//
//  ControllerTestCommand2.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
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