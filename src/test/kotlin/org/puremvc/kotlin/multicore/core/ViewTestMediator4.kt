//
//  ViewTestMediator4.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
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
class ViewTestMediator4(var view: WeakReference<ViewTest>) : Mediator(NAME, view as WeakReference<Any?>), IMediator {

    companion object {
        /**
         * The Mediator name
         */
        const val NAME = "ViewTestMediator4"
    }

    override fun onRegister() {
        view.get()?.onRegisterCalled = true
    }

    override fun onRemove() {
        view.get()?.onRemoveCalled = true
    }

}
