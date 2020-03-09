//
//  INotifier.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.interfaces

/**
 * <P>The interface definition for a PureMVC Notifier.</P>
 *
 * <P><code>MacroCommand, Command, Mediator</code> and <code>Proxy</code>
 * all have a need to send <code>Notifications</code>.</P>
 *
 * <P>The <code>INotifier</code> interface provides a common method called
 * <code>sendNotification</code> that relieves implementation code of
 * the necessity to actually construct <code>Notifications</code>.</P>
 *
 * <P>The <code>Notifier</code> class, which all of the above mentioned classes
 * extend, also provides an initialized reference to the <code>Facade</code>
 * Singleton, which is required for the convenience method
 * for sending <code>Notifications</code>, but also eases implementation as these
 * classes have frequent <code>Facade</code> interactions and usually require
 * access to the facade anyway.</P>
 *
 * @see IFacade IFacade
 * @see org.puremvc.kotlin.multicore.interfaces.INotification INotification
 */
interface INotifier {

    /**
     * <P>Send a <code>INotification</code>.</P>
     *
     * <P>Convenience method to prevent having to construct new
     * notification instances in our implementation code.</P>
     *
     * @param notificationName the name of the notification to send
     * @param body the body of the notification
     * @param type the type of the notification
     */
    fun sendNotification(notificationName: String, body: Any, type: String)

    /**
     * <P>Send a <code>INotification</code>.</P>
     *
     * <P>Convenience method to prevent having to construct new
     * notification instances in our implementation code.</P>
     *
     * @param notificationName the name of the notification to send
     * @param body the body of the notification
     */
    fun sendNotification(notificationName: String, body: Any)

    /**
     * <P>Send a <code>INotification</code>.</P>
     *
     * <P>Convenience method to prevent having to construct new
     * notification instances in our implementation code.</P>
     *
     * @param notificationName the name of the notification to send
     */
    fun sendNotification(notificationName: String)

    /**
     * <P>Initialize this INotifier instance.</P>
     *
     * <P>This is how a Notifier gets its multitonKey.
     * Calls to sendNotification or to access the
     * facade will fail until after this method
     * has been called.</P>
     *
     * @param key the multitonKey for this INotifier to use
     */
    fun initializeNotifier(key: String)

}