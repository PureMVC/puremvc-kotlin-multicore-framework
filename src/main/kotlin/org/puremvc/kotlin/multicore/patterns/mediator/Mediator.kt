//
//  Mediator.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.mediator

import org.puremvc.kotlin.multicore.interfaces.IMediator
import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.patterns.observer.Notifier
import java.lang.ref.WeakReference

/**
 * <P>A base <code>IMediator</code> implementation.</P>
 *
 * @see org.puremvc.kotlin.multicore.core.View View
 *
 * @constructor Creates a Mediator
 */

open class Mediator(override val name: String, override var viewComponent: WeakReference<*>?) : Notifier(), IMediator {

    companion object {
        /**
         * <P>The name of the <code>Mediator</code>.</P>
         *
         * <P>Typically, a <code>Mediator</code> will be written to serve
         * one specific control or group controls and so,
         * will not have a need to be dynamically named.</P>
         */
        const val NAME = "Mediator"
    }

    /**
     * <P>Constructor.</P>
     */
    constructor() : this(NAME, null)

    /**
     * <P>Constructor.</P>
     *
     * @param name mediator name
     */
    constructor(name: String) : this(name, null)

    /**
     * <P>List the <code>INotification</code> names this
     * <code>Mediator</code> is interested in being notified of.</P>
     *
     * @return Array the list of <code>INotification</code> names
     */
    override fun listNotificationInterests(): Array<String> {
        return arrayOf()
    }

    /**
     * <P>Handle <code>INotification</code>s.</P>
     *
     * <P>Typically this will be handled in a switch statement,
     * with one 'case' entry per <code>INotification</code>
     * the <code>Mediator</code> is interested in.</P>
     */
    override fun handleNotification(notification: INotification) {

    }

    /**
     * <P>Called by the View when the Mediator is registered</P>
     */
    override fun onRegister() {

    }

    /**
     * <P>Called by the View when the Mediator is removed</P>
     */
    override fun onRemove() {

    }

}