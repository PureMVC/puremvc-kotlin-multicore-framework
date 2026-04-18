//
//  IMediator.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
//

package org.puremvc.kotlin.multicore.interfaces

/**
 * <P>The interface definition for a PureMVC Mediator.</P>
 *
 * <P>In PureMVC, <code>IMediator</code> implementors assume these responsibilities:</P>
 *
 * <UL>
 * <LI>Implement a common method which returns a list of all <code>INotification</code>'s
 * the <code>IMediator</code> has interest in.</LI>
 * <LI>Implement a notification callback method.</LI>
 * <LI>Implement methods that are called when the IMediator is registered or removed from the View.</LI>
 * </UL>
 *
 * <P>Additionally, <code>IMediator</code>'s typically:</P>
 *
 * <UL>
 * <LI>Act as an intermediary between one or more view components such as text boxes or
 * list controls, maintaining references and coordinating their behavior.</LI>
 * <LI>In Flash-based apps, this is often the place where event listeners are
 * added to view components, and their handlers implemented.</LI>
 * <LI>Respond to and generate <code>INotifications</code>, interacting with of
 * the rest of the PureMVC app.
 * </UL>
 *
 * <P>When an <code>IMediator</code> is registered with the <code>IView</code>,
 * the <code>IView</code> will call the <code>IMediator</code>'s
 * <code>listNotificationInterests</code> method. The <code>IMediator</code> will
 * return an <code>Array</code> of <code>INotification</code> names which
 * it wishes to be notified about.</P>
 *
 * <P>The <code>IView</code> will then create an <code>Observer</code> object
 * encapsulating that <code>IMediator</code>'s (<code>handleNotification</code>) method
 * and register it as an Observer for each <code>INotification</code> name returned by
 * <code>listNotificationInterests</code>.</P>
 * </pre>
 *
 * @see org.puremvc.kotlin.multicore.interfaces.INotification INotification
 */
interface IMediator : INotifier {

    val name: String

    var viewComponent: Any?

    /**
     * <P>List <code>INotification</code> interests.</P>
     *
     * @return an <code>Array</code> of the <code>INotification</code> names this <code>IMediator</code> has an interest in.
     */
    fun listNotificationInterests(): Array<String>

    /**
     * <P>Handle an <code>INotification</code>.</P>
     *
     * @param notification the <code>INotification</code> to be handled
     */
    fun handleNotification(notification: INotification)

    /**
     * <P>Called by the View when the Mediator is registered</P>
     */
    fun onRegister()

    /**
     * <P>Called by the View when the Mediator is removed</P>
     */
    fun onRemove()
}
