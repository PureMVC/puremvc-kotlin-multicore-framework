//
//  Controller.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.core

import org.puremvc.kotlin.multicore.interfaces.ICommand
import org.puremvc.kotlin.multicore.interfaces.IController
import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.interfaces.IView
import org.puremvc.kotlin.multicore.patterns.observer.Observer
import java.util.concurrent.ConcurrentHashMap

/**
 * <P>A Multiton <code>IController</code> implementation.</P>
 *
 * <P>In PureMVC, the <code>Controller</code> class follows the
 * 'Command and Controller' strategy, and assumes these
 * responsibilities:</P>
 *
 * <UL>
 * <LI> Remembering which <code>ICommand</code>
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
 * <P>Your application must register <code>ICommands</code> with the
 * Controller.</P>
 *
 * <P>The simplest way is to subclass <code>Facade</code>,
 * and use its <code>initializeController</code> method to add your
 * registrations.</P>
 *
 * @see View View
 * @see org.puremvc.kotlin.multicore.patterns.observer.Observer Observer
 * @see org.puremvc.kotlin.multicore.patterns.observer.Notification Notification
 * @see org.puremvc.kotlin.multicore.patterns.command.SimpleCommand SimpleCommand
 * @see org.puremvc.kotlin.multicore.patterns.command.MacroCommand MacroCommand
 *
 * @constructor Creates a Controller
 */

open class Controller(key: String): IController {

    companion object {
        // Message Constants
        const val MULTITON_MSG = "Controller instance for this Multiton key already constructed!"

        // The Multiton Controller instanceMap.
        private val instanceMap = ConcurrentHashMap<String, IController>()

        /**
         * <P><code>Controller</code> Multiton Factory method.</P>
         *
         * @param key multitonKey
         * @param factory factory that returns <code>IController</code>
         * @return the Multiton instance of <code>Controller</code>
         */
        @Synchronized fun getInstance(key: String, factory: (key: String) -> IController): IController {
            if (instanceMap[key] == null) {
                instanceMap[key] = factory(key)
            }
            return instanceMap[key]!!
        }

        /**
         * <P>Remove an IController instance</P>
         *
         * @param key multitonKey of IController instance to remove
         */
        @Synchronized fun removeController(key: String) {
            instanceMap.remove(key)
        }
    }

    // The Multiton Key for this Core
    protected var multitonKey: String

    // Local reference to View
    protected lateinit var view: IView

    // Mapping of Notification names to Command Factories
    protected var commandMap = ConcurrentHashMap<String, () -> ICommand>()

    /**
     * <P>Constructor.</P>
     *
     * <P>This <code>IController</code> implementation is a Multiton,
     * so you should not call the constructor
     * directly, but instead call the static Factory method,
     * passing the unique key and a supplier for this instance
     * {@code Controller.getInstance(multitonKey) { key -> Controller(key) }}</P>
     *
     * @param key multitonKey
     * @throws Error Error if instance for this Multiton key has already been constructed
     *
     */
    init {
        if (instanceMap.containsKey(key)) throw Error(MULTITON_MSG)
        multitonKey = key
        instanceMap[key] = this;
        initializeController()
    }

    /**
     * <P>Initialize the Multiton <code>Controller</code> instance.</P>
     *
     * <P>Called automatically by the constructor.</P>
     *
     * <P>Note that if you are using a subclass of <code>View</code>
     * in your application, you should <i>also</i> subclass <code>Controller</code>
     * and override the <code>initializeController</code> method in the
     * following way:</P>
     *
     * <pre>
     * {@code // ensure that the Controller is talking to my IView implementation
     * override fun initializeController()
     * {
     *     view = MyView.getInstance(multitonKey) { key -> MyView(key)) };
     * }
     * }
     * </pre>
     */
    override fun initializeController() {
        view = View.getInstance(multitonKey) { key -> View(key) }
    }

    /**
     * <P>Register a particular <code>ICommand</code> class as the handler
     * for a particular <code>INotification</code>.</P>
     *
     * <P>If an <code>ICommand</code> has already been registered to
     * handle <code>INotification</code>s with this name, it is no longer
     * used, the new <code>ICommand</code> is used instead.</P>
     *
     * <P>The Observer for the new ICommand is only created if this the
     * first time an ICommand has been registered for this Notification name.</P>
     *
     * @param notificationName the name of the <code>INotification</code>
     * @param factory a reference to <code>ICommand</code> factory
     */
    override fun registerCommand(notificationName: String, factory: () -> ICommand) {
        if (!commandMap.containsKey(notificationName)) {
            view.registerObserver(notificationName, Observer(this::executeCommand, this))
        }
        commandMap[notificationName] = factory
    }

    /**
     * <P>If an <code>ICommand</code> has previously been registered
     * to handle a the given <code>INotification</code>, then it is executed.</P>
     *
     * @param notification an <code>INotification</code>
     */
    override fun executeCommand(notification: INotification) {
        commandMap[notification.name]?.let { factory ->
            val commandInstance = factory()
            commandInstance.initializeNotifier(multitonKey)
            commandInstance.execute(notification)
        }
    }

    /**
     * <P>Check if a Command is registered for a given Notification</P>
     *
     * @param notificationName notification name
     * @return whether a Command is currently registered for the given <code>notificationName</code>.
     */
    override fun hasCommand(notificationName: String): Boolean {
        return commandMap.containsKey(notificationName)
    }

    /**
     * <P>Remove a previously registered <code>ICommand</code> to <code>INotification</code> mapping.</P>
     *
     * @param notificationName the name of the <code>INotification</code> to remove the <code>ICommand</code> mapping for
     */
    override fun removeCommand(notificationName: String) {
        if (hasCommand(notificationName)) {
            view.removeObserver(notificationName, this)
            commandMap.remove(notificationName)
        }
    }

}