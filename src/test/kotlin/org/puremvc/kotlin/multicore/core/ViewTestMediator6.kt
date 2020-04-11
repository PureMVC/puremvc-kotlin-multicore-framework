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
import java.lang.ref.WeakReference

/**
 * A Mediator class used by ViewTest.
 *
 * @see ViewTest ViewTest
 */
class ViewTestMediator6(override val name: String, var view: WeakReference<ViewTest>) : Mediator(name, view as WeakReference<Any?>), IMediator {

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
        view.get()!!.counter++
    }

}
