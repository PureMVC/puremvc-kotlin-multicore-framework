//
//  ViewTestMediator.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
//

package org.puremvc.kotlin.multicore.core

import org.puremvc.kotlin.multicore.interfaces.IMediator
import org.puremvc.kotlin.multicore.patterns.mediator.Mediator
import java.lang.ref.WeakReference

/**
 * A Mediator class used by ViewTest.
 *
 * @see ViewTest ViewTest
 */
class ViewTestMediator(view: ViewTest) : Mediator(NAME, WeakReference(view)), IMediator {

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

    @Suppress("UNCHECKED_CAST")
    private val view: ViewTest? get() = (viewComponent as? WeakReference<ViewTest>)?.get()
}
