//
//  Notifier.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.observer

import org.puremvc.kotlin.multicore.interfaces.IFacade
import org.puremvc.kotlin.multicore.interfaces.INotifier
import org.puremvc.kotlin.multicore.patterns.facade.Facade

/**
 * <P>A Base <code>INotifier</code> implementation.</P>
 *
 * <P><code>MacroCommand, Command, Mediator</code> and <code>Proxy</code>
 * all have a need to send <code>Notifications</code>.</P>
 *
 * <P>The <code>INotifier</code> interface provides a common method called
 * <code>sendNotification</code> that relieves implementation code of
 * the necessity to actually construct <code>Notifications</code>.</P>
 *
 * <P>The <code>Notifier</code> class, which all of the above mentioned classes
 * extend, provides an initialized reference to the <code>Facade</code>
 * Multiton, which is required for the convienience method
 * for sending <code>Notifications</code>, but also eases implementation as these
 * classes have frequent <code>Facade</code> interactions and usually require
 * access to the facade anyway.</P>
 *
 * <P>NOTE: In the MultiCore version of the framework, there is one caveat to
 * notifiers, they cannot send notifications or reach the facade until they
 * have a valid multitonKey.</P>
 *
 * <P>The multitonKey is set:
 *   * on a Command when it is executed by the Controller
 *   * on a Mediator is registered with the View
 *   * on a Proxy is registered with the Model.</P>
 *
 * @see org.puremvc.kotlin.multicore.patterns.proxy.Proxy Proxy
 * @see org.puremvc.kotlin.multicore.patterns.facade.Facade Facade
 * @see org.puremvc.kotlin.multicore.patterns.mediator.Mediator Mediator
 * @see org.puremvc.kotlin.multicore.patterns.command.MacroCommand MacroCommand
 * @see org.puremvc.kotlin.multicore.patterns.command.SimpleCommand SimpleCommand
 *
 * @constructor Creates a Notifier
 */

open class Notifier : INotifier {

    companion object {
        val MULTITON_MSG = "multitonKey for this Notifier not yet initialized!"
    }

    protected lateinit var multitonKey: String

    protected val facade: IFacade by lazy {
        if (multitonKey.isNullOrEmpty()) throw RuntimeException(MULTITON_MSG)
        Facade.getInstance(multitonKey) { key -> Facade(key) }
    }

    /**
     * <P>Create and send an <code>INotification</code>.</P>
     *
     * <P>Keeps us from having to construct new INotification
     * instances in our implementation code.</P>
     *
     * @param notificationName the name of the notiification to send
     * @param body the body of the notification
     * @param type the type of the notification
     */
    override fun sendNotification(notificationName: String, body: Any, type: String) {
        facade.sendNotification(notificationName, body, type)
    }

    /**
     * <P>Create and send an <code>INotification</code>.</P>
     *
     * <P>Keeps us from having to construct new INotification
     * instances in our implementation code.</P>
     *
     * @param notificationName the name of the notiification to send
     * @param body the body of the notification
     */
    override fun sendNotification(notificationName: String, body: Any) {
        facade.sendNotification(notificationName, body)
    }

    /**
     * <P>Create and send an <code>INotification</code>.</P>
     *
     * <P>Keeps us from having to construct new INotification
     * instances in our implementation code.</P>
     *
     * @param notificationName the name of the notiification to send
     */
    override fun sendNotification(notificationName: String) {
        facade.sendNotification(notificationName)
    }

    /**
     * <P>Initialize this INotifier instance.</P>
     *
     * <P>This is how a Notifier gets its multitonKey.
     * Calls to sendNotification or to access the
     * facade will fail until after this method
     * has been called.</P>
     *
     * <P>Mediators, Commands or Proxies may override
     * this method in order to send notifications
     * or access the Multiton Facade instance as
     * soon as possible. They CANNOT access the facade
     * in their constructors, since this method will not
     * yet have been called.</P>
     *
     * @param key the multitonKey for this INotifier to use
     */
    override fun initializeNotifier(key: String) {
        multitonKey = key
    }

}