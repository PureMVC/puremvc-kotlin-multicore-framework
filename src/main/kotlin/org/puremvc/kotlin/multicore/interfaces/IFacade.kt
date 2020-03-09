//
//  IFacade.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.interfaces

/**
 * <P>The interface definition for a PureMVC Facade.</P>
 *
 * <P>The Facade Pattern suggests providing a single
 * class to act as a central point of communication
 * for a subsystem. </P>
 *
 * <P>In PureMVC, the Facade acts as an interface between
 * the core MVC actors (Model, View, Controller) and
 * the rest of your application.</P>
 *
 * @see IModel IModel
 * @see IView IView
 * @see org.puremvc.kotlin.multicore.interfaces.IController IController
 * @see org.puremvc.kotlin.multicore.interfaces.ICommand ICommand
 * @see org.puremvc.kotlin.multicore.interfaces.INotification INotification
 */

interface IFacade : INotifier {

    /**
     * <P>Register an <code>IProxy</code> with the <code>Model</code> by name.</P>
     *
     * @param proxy the <code>IProxy</code> to be registered with the <code>Model</code>.
     */
    fun registerProxy(proxy: IProxy)

    /**
     * <P>Retrieve a <code>IProxy</code> from the <code>Model</code> by name.</P>
     *
     * @param proxyName the name of the <code>IProxy</code> instance to be retrieved.
     * @return the <code>IProxy</code> previously regisetered by <code>proxyName</code> with the <code>Model</code>.
     */
    fun retrieveProxy(proxyName: String): IProxy?

    /**
     * <P>Check if a Proxy is registered</P>
     *
     * @param proxyName proxy name
     * @return whether a Proxy is currently registered with the given <code>proxyName</code>.
     */
    fun hasProxy(proxyName: String): Boolean

    /**
     * <P>Remove an <code>IProxy</code> instance from the <code>Model</code> by name.</P>
     *
     * @param proxyName the <code>IProxy</code> to remove from the <code>Model</code>.
     * @return the <code>IProxy</code> that was removed from the <code>Model</code>
     */
    fun removeProxy(proxyName: String): IProxy?

    /**
     * <P>Register an <code>ICommand</code> with the <code>Controller</code>.</P>
     *
     * @param notificationName the name of the <code>INotification</code> to associate the <code>ICommand</code> with.
     * @param factory a reference to <code>ICommand</code> factory
     */
    fun registerCommand(notificationName: String, factory: () -> ICommand)

    /**
     * <P>Check if a Command is registered for a given Notification</P>
     *
     * @param notificationName notification name
     * @return whether a Command is currently registered for the given <code>notificationName</code>.
     */
    fun hasCommand(notificationName: String): Boolean

    /**
     * <P>Remove a previously registered <code>ICommand</code> to <code>INotification</code> mapping from the Controller.</P>
     *
     * @param notificationName the name of the <code>INotification</code> to remove the <code>ICommand</code> mapping for
     */
    fun removeCommand(notificationName: String)

    /**
     * <P>Register an <code>IMediator</code> instance with the <code>View</code>.</P>
     *
     * @param mediator a reference to the <code>IMediator</code> instance
     */
    fun registerMediator(mediator: IMediator)

    /**
     * <P>Retrieve an <code>IMediator</code> instance from the <code>View</code>.</P>
     *
     * @param mediatorName the name of the <code>IMediator</code> instance to retrievve
     * @return the <code>IMediator</code> previously registered with the given <code>mediatorName</code>.
     */
    fun retrieveMediator(mediatorName: String): IMediator?

    /**
     * <P>Check if a Mediator is registered or not</P>
     *
     * @param mediatorName mediator name
     * @return whether a Mediator is registered with the given <code>mediatorName</code>.
     */
    fun hasMediator(mediatorName: String): Boolean

    /**
     * <P>Remove a <code>IMediator</code> instance from the <code>View</code>.</P>
     *
     * @param mediatorName name of the <code>IMediator</code> instance to be removed.
     * @return the <code>IMediator</code> instance previously registered with the given <code>mediatorName</code>.
     */
    fun removeMediator(mediatorName: String): IMediator?

    /**
     * <P>Notify <code>Observer</code>s.</P>
     *
     * <P>This method is left public mostly for backward
     * compatibility, and to allow you to send custom
     * notification classes using the facade.</P>
     *
     * <P>Usually you should just call sendNotification
     * and pass the parameters, never having to
     * construct the notification yourself.</P>
     *
     * @param notification the <code>INotification</code> to have the <code>View</code> notify <code>Observers</code> of.
     */
    fun notifyObservers(notification: INotification)

}