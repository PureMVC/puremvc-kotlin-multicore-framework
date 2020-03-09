//
//  IController.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.interfaces

/**
 * <P>The interface definition for a PureMVC Controller.</P>
 *
 * <P>In PureMVC, an <code>IController</code> implementor
 * follows the 'Command and Controller' strategy, and
 * assumes these responsibilities:</P>
 *
 * <UL>
 * <LI> Remembering which <code>ICommand</code>s
 * are intended to handle which <code>INotifications</code>.</LI>
 * <LI> Registering itself as an <code>IObserver</code> with
 * the <code>View</code> for each <code>INotification</code>
 * that it has an <code>ICommand</code> mapping for.</LI>
 * <LI> Creating a new instance of the proper <code>ICommand</code>
 * to handle a given <code>INotification</code> when notified by the <code>View</code>.</LI>
 * <LI> Calling the <code>ICommand</code>'s <code>execute</code>
 * method, passing in the <code>INotification</code>.</LI>
 * </UL>
 *
 * @see org.puremvc.kotlin.multicore.interfaces INotification
 * @see org.puremvc.kotlin.multicore.interfaces ICommand
 */
interface IController {

    /**
     * <P>Initialize the Multiton <code>Controller</code> instance.</P>
     */
    fun initializeController()

    /**
     * <P>Register a particular <code>ICommand</code> class as the handler
     * for a particular <code>INotification</code>.</P>
     *
     * @param notificationName the name of the <code>INotification</code>
     * @param factory a reference to <code>ICommand</code> factory
     */
    fun registerCommand(notificationName: String, factory: () -> ICommand)

    /**
     * <P>Execute the <code>ICommand</code> previously registered as the
     * handler for <code>INotification</code>s with the given notification name.</P>
     *
     * @param notification the <code>INotification</code> to execute the associated <code>ICommand</code> for
     */
    fun executeCommand(notification: INotification)

    /**
     * <P>Check if a Command is registered for a given Notification</P>
     *
     * @param notificationName notification name
     * @return whether a Command is currently registered for the given <code>notificationName</code>.
     */
    fun hasCommand(notificationName: String): Boolean

    /**
     * <P>Remove a previously registered <code>ICommand</code> to <code>INotification</code> mapping.</P>
     *
     * @param notificationName the name of the <code>INotification</code> to remove the <code>ICommand</code> mapping for
     */
    fun removeCommand(notificationName: String)

}