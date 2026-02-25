//
//  ViewTestMediator5.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
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
class ViewTestMediator5(var view: WeakReference<ViewTest>) : Mediator(NAME, view as WeakReference<Any?>), IMediator {

    companion object {
        /**
         * The Mediator name
         */
        const val NAME = "ViewTestMediator5"
    }

    override fun listNotificationInterests(): Array<String> {
        return arrayOf(ViewTest.NOTE5)
    }

    override fun handleNotification(notification: INotification) {
        view.get()!!.counter++
    }

}
