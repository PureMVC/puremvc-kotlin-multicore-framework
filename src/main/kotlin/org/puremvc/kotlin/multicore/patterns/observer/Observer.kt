//
//  Observer.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.observer

import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.interfaces.IObserver
import java.lang.ref.WeakReference

/**
 * <P>A base <code>IObserver</code> implementation.</P>
 *
 * <P>An <code>Observer</code> is an object that encapsulates information
 * about an interested object with a method that should
 * be called when a particular <code>INotification</code> is broadcast.</P>
 *
 * <P>In PureMVC, the <code>Observer</code> class assumes these responsibilities:</P>
 *
 * <UL>
 * <LI>Encapsulate the notification (callback) method of the interested object.</LI>
 * <LI>Encapsulate the notification context (this) of the interested object.</LI>
 * <LI>Provide methods for setting the notification method and context.</LI>
 * <LI>Provide a method for notifying the interested object.</LI>
 * </UL>
 *
 * @see org.puremvc.kotlin.multicore.core.View View
 * @see org.puremvc.kotlin.multicore.patterns.observer.Notification Notification
 *
 * @constructor Creates an Observer
 */

open class Observer(override var notifyMethod: ((INotification) -> Unit)?, override var notifyContext: WeakReference<Any?>?) : IObserver {

    /**
     * <P>Notify the interested object.</P>
     *
     * @param notification the <code>INotification</code> to pass to the interested object's notification method.
     */
    override fun notifyObserver(notification: INotification) {
        notifyMethod?.let { it(notification) }
    }

    /**
     * <P>Compare an object to the notification context.</P>
     *
     * @param object the object to compare
     * @return boolean indicating if the object and the notification context are the same
     */
    override fun compareNotifyContext(context: Any): Boolean {
        return context == notifyContext?.get()
    }

}