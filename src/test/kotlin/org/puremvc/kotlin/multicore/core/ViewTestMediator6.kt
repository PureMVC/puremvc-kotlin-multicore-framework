//
//  ViewTestMediator6.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.core

import org.puremvc.kotlin.multicore.interfaces.IMediator
import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.patterns.mediator.Mediator

/**
 * A Mediator class used by ViewTest.
 *
 * @see ViewTest ViewTest
 */
class ViewTestMediator6(override val name: String, override var viewComponent: Any?) : Mediator(name, viewComponent), IMediator {

    companion object {
        /**
         * The Mediator base name
         */
        const val NAME = "ViewTestMediator6"
    }

    override fun listNotificationInterests(): Array<String> {
        return arrayOf(ViewTest.NOTE6)
    }

    override fun handleNotification(notification: INotification) {
        facade.removeMediator(name)
    }

    override fun onRemove() {
        viewTest().counter++
    }

    fun viewTest(): ViewTest {
        return viewComponent as ViewTest
    }

}
