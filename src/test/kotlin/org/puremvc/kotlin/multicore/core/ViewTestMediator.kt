//
//  ViewTestMediator.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.core

import org.puremvc.kotlin.multicore.interfaces.IMediator
import org.puremvc.kotlin.multicore.patterns.mediator.Mediator

/**
 * A Mediator class used by ViewTest.
 *
 * @see ViewTest ViewTest
 */
class ViewTestMediator(view: Any) : Mediator(NAME, view), IMediator {

    companion object {
        /**
         * The Mediator name
         */
        const val NAME = "ViewTestMediator"
    }

    override fun listNotificationInterests(): Array<String> {
        // be sure that the mediator has some Observers created
        // in order to test removeMediator
        return arrayOf("ABC", "DEF", "GHI")
    }

}
