//
//  ViewTestMediator5.kt
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
class ViewTestMediator5(view: ViewTest) : Mediator(NAME, view), IMediator {

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
        getViewTest().counter++
    }

    fun getViewTest(): ViewTest {
        return viewComponent as ViewTest
    }

}
