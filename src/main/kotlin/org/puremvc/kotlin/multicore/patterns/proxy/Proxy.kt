//
//  Proxy.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.proxy

import org.puremvc.kotlin.multicore.interfaces.IProxy
import org.puremvc.kotlin.multicore.patterns.observer.Notifier

/**
 * <P>A base <code>IProxy</code> implementation.</P>
 *
 * <P>In PureMVC, <code>Proxy</code> classes are used to manage parts of the
 * application's data model.</P>
 *
 * <P>A <code>Proxy</code> might simply manage a reference to a local data object,
 * in which case interacting with it might involve setting and
 * getting of its data in synchronous fashion.</P>
 *
 * <P><code>Proxy</code> classes are also used to encapsulate the application's
 * interaction with remote services to save or retrieve data, in which case,
 * we adopt an asynchronous idiom; setting data (or calling a method) on the
 * <code>Proxy</code> and listening for a <code>Notification</code> to be sent
 * when the <code>Proxy</code> has retrieved the data from the service.</P>
 *
 * @see org.puremvc.kotlin.multicore.core.Model Model
 *
 * @constructor Creates a Proxy
 */

open class Proxy(override val name: String, override var data: Any?) : Notifier(), IProxy {

    /**
     * <P>Constructor.</P>
     */
    companion object {
        const val NAME = "Proxy"
    }

    constructor() : this(NAME, null)

    /**
     * <P>Constructor.</P>
     *
     * @param name proxy name
     */
    constructor(name: String) : this(name, null)

    /**
     * <P>Called by the Model when the Proxy is registered</P>
     */
    override fun onRegister() {

    }

    /**
     * <P>Called by the Model when the Proxy is removed</P>
     */
    override fun onRemove() {

    }

}