//
//  IObserver.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.interfaces

/**
 * <P>The interface definition for a PureMVC Observer.</P>
 *
 * <P>In PureMVC, <code>IObserver</code> implementors assume these responsibilities:</P>
 *
 * <UL>
 * <LI>Encapsulate the notification (callback) method of the interested object.</LI>
 * <LI>Encapsulate the notification context (this) of the interested object.</LI>
 * <LI>Provide methods for setting the interested object' notification method and context.</LI>
 * <LI>Provide a method for notifying the interested object.</LI>
 * </UL>
 *
 * <P>PureMVC does not rely upon underlying event
 * models such as the one provided with Flash,
 * and ActionScript 3 does not have an inherent
 * event model.</P>
 *
 * <P>The Observer Pattern as implemented within
 * PureMVC exists to support event driven communication
 * between the application and the actors of the
 * MVC triad.</P>
 *
 * <P>An Observer is an object that encapsulates information
 * about an interested object with a notification method that
 * should be called when an <code>INotification</code> is broadcast. The Observer then
 * acts as a proxy for notifying the interested object.</P>
 *
 * <P>Observers can receive <code>Notification</code>s by having their
 * <code>notifyObserver</code> method invoked, passing
 * in an object implementing the <code>INotification</code> interface, such
 * as a subclass of <code>Notification</code>.</P>
 *
 * @see IView IView
 * @see org.puremvc.kotlin.multicore.interfaces.INotification INotification
 */
interface IObserver {

    /**
     * <P>Get the notification method.</P>
     *
     * @param notifyMethod the notification consumer method of the interested object
     */
    val notifyMethod: ((INotification) -> Unit)?

    /**
     * <P>Get the notification context.</P>
     *
     * @param notifyContext the notification context (this) of the interested object
     */
    val notifyContext: Any?

    /**
     * <P>Notify the interested object.</P>
     *
     * @param notification the <code>INotification</code> to pass to the interested object's notification method
     */
    fun notifyObserver(notification: INotification)

    /**
     * <P>Compare the given object to the notificaiton context object.</P>
     *
     * @param object the object to compare.
     * @return boolean indicating if the notification context and the object are the same.
     */
    fun compareNotifyContext(context: Any): Boolean

}