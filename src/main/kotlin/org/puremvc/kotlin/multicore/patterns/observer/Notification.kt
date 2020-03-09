//
//  Notification.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.observer

import org.puremvc.kotlin.multicore.interfaces.INotification
import java.lang.StringBuilder

/**
 * <P>A base <code>INotification</code> implementation.</P>
 *
 * <P>PureMVC does not rely upon underlying event models such
 * as the one provided with Flash, and ActionScript 3 does
 * not have an inherent event model.</P>
 *
 * <P>The Observer Pattern as implemented within PureMVC exists
 * to support event-driven communication between the
 * application and the actors of the MVC triad.</P>
 *
 * <P>Notifications are not meant to be a replacement for Events
 * in Flex/Flash/Apollo. Generally, <code>IMediator</code> implementors
 * place event listeners on their view components, which they
 * then handle in the usual way. This may lead to the broadcast of <code>Notification</code>s to
 * trigger <code>ICommand</code>s or to communicate with other <code>IMediators</code>. <code>IProxy</code> and <code>ICommand</code>
 * instances communicate with each other and <code>IMediator</code>s
 * by broadcasting <code>INotification</code>s.</P>
 *
 * <P>A key difference between Flash <code>Event</code>s and PureMVC
 * <code>Notification</code>s is that <code>Event</code>s follow the
 * 'Chain of Responsibility' pattern, 'bubbling' up the display hierarchy
 * until some parent component handles the <code>Event</code>, while
 * PureMVC <code>Notification</code>s follow a 'Publish/Subscribe'
 * pattern. PureMVC classes need not be related to each other in a
 * parent/child relationship in order to communicate with one another
 * using <code>Notification</code>s.</P>
 *
 * @see Observer Observer
 *
 * @constructor Creates a Notification
 */
open class Notification(override val name: String, override var body: Any?, override var type: Any?) : INotification {

    /**
     * <P>Constructor.</P>
     *
     * @param name name of the <code>Notification</code> instance. (required)
     */
    constructor(name: String) : this(name, null, null)

    /**
     * <P>Constructor.</P>
     *
     * @param name name of the <code>Notification</code> instance. (required)
     * @param body the <code>Notification</code> body.
     */
    constructor(name: String, body: Any?) : this(name, body, null)

    /**
     * <P>Get the string representation of the <code>Notification</code> instance.</P>
     *
     * @return the string representation of the <code>Notification</code> instance.
     */
    override fun toString(): String {
        return StringBuilder("Notification Name: $name").append("\nBody:" + if (body == null) "null" else body)
            .append("\nType:" + if (type == null) "null" else type).toString()
    }

}