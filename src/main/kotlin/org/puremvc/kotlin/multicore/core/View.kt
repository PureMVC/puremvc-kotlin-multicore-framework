//
//  View.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.core

import org.puremvc.kotlin.multicore.interfaces.IMediator
import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.interfaces.IObserver
import org.puremvc.kotlin.multicore.interfaces.IView
import org.puremvc.kotlin.multicore.patterns.observer.Observer
import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentHashMap

/**
 * <P>A Multiton <code>IView</code> implementation.</P>
 *
 * <P>In PureMVC, the <code>View</code> class assumes these responsibilities:</P>
 *
 * <UL>
 * <LI>Maintain a cache of <code>IMediator</code> instances.</LI>
 * <LI>Provide methods for registering, retrieving, and removing <code>IMediators</code>.</LI>
 * <LI>Notifiying <code>IMediators</code> when they are registered or removed.</LI>
 * <LI>Managing the observer lists for each <code>INotification</code> in the application.</LI>
 * <LI>Providing a method for attaching <code>IObservers</code> to an <code>INotification</code>'s observer list.</LI>
 * <LI>Providing a method for broadcasting an <code>INotification</code>.</LI>
 * <LI>Notifying the <code>IObservers</code> of a given <code>INotification</code> when it broadcast.</LI>
 * </UL>
 *
 * @see org.puremvc.kotlin.multicore.patterns.mediator.Mediator Mediator
 * @see org.puremvc.kotlin.multicore.patterns.observer.Observer Observer
 * @see org.puremvc.kotlin.multicore.patterns.observer.Notification Notification
 *
 * @constructor Creates a View
 */

open class View(key: String): IView {

    companion object {
        // Message Constants
        const val MULTITON_MSG = "View instance for this Multiton key already constructed!"

        // The Multiton View instanceMap.
        private val instanceMap = ConcurrentHashMap<String, IView>()

        /**
         * <P>View Singleton Factory method.</P>
         *
         * @param key multitonKey
         * @param factory factory that returns <code>IView</code>
         * @return the Multiton instance of <code>View</code>
         */
        @Synchronized fun getInstance(key: String, factory: (key: String) -> IView): IView {
            if (instanceMap[key] == null) {
                instanceMap[key] = factory(key)
            }
            return instanceMap[key]!!
        }

        /**
         * <P>Remove an IView instance</P>
         *
         * @param key of IView instance to remove
         */
        @Synchronized fun removeView(key: String) {
            instanceMap.remove(key)
        }
    }

    // The Multiton Key for this Core
    protected var multitonKey: String

    // Mapping of Mediator names to Mediator instances
    protected val mediatorMap = ConcurrentHashMap<String, IMediator>()

    // Mapping of Notification names to Observer lists
    protected val observerMap = ConcurrentHashMap<String, MutableList<IObserver>>()

    /**
     * <P>Constructor.</P>
     *
     * <P>This <code>IView</code> implementation is a Multiton,
     * so you should not call the constructor
     * directly, but instead call the static Multiton
     * Factory method <code>View.getInstance( multitonKey )</code></P>
     *
     * @param key multitonKey
     * @throws Error Error if instance for this Multiton key has already been constructed
     *
     */
    init {
        if (instanceMap.containsKey(key)) throw Error(MULTITON_MSG)
        multitonKey = key
        instanceMap[key] = this;
        initializeView()
    }

    /**
     * <P>Initialize the Singleton View instance.</P>
     *
     * <P>Called automatically by the constructor, this
     * is your opportunity to initialize the Singleton
     * instance in your subclass without overriding the
     * constructor.</P>
     */
    override fun initializeView() {

    }

    /**
     * <P>Register an <code>IObserver</code> to be notified
     * of <code>INotifications</code> with a given name.</P>
     *
     * @param notificationName the name of the <code>INotifications</code> to notify this <code>IObserver</code> of
     * @param observer the <code>IObserver</code> to register
     */
    override fun registerObserver(notificationName: String, observer: IObserver) {
        if (observerMap.containsKey(notificationName)) {
            observerMap[notificationName]!!.add(observer)
        } else {
            observerMap[notificationName] = mutableListOf(observer)
        }
    }

    /**
     * <P>Notify the <code>IObservers</code> for a particular <code>INotification</code>.</P>
     *
     * <P>All previously attached <code>IObservers</code> for this <code>INotification</code>'s
     * list are notified and are passed a reference to the <code>INotification</code> in
     * the order in which they were registered.</P>
     *
     * @param notification the <code>INotification</code> to notify <code>IObservers</code> of.
     */
    override fun notifyObservers(notification: INotification) {
        observerMap[notification.name]?.let { observers ->
            // Copy observers from reference array to working array,
            // since the reference array may change during the notification loop
            val observersRef = observers.toMutableList()

            // Notify Observers from the working array
            for (observer in observersRef) {
                observer.notifyObserver(notification)
            }
        }
    }

    /**
     * <P>Remove the observer for a given notifyContext from an observer list for a given Notification name.</P>
     *
     * @param notificationName which observer list to remove from
     * @param notifyContext remove the observer with this object as its notifyContext
     */
    override fun removeObserver(notificationName: String, notifyContext: Any) {
        // the observer list for the notification under inspection
        observerMap[notificationName]?.let { observers ->
            for (observer in observers) {
                if (observer.compareNotifyContext(notifyContext)) {
                    // there can only be one Observer for a given notifyContext
                    // in any given Observer list, so remove it and break
                    observers.remove(observer)
                    break
                }
            }

            // Also, when a Notification's Observer list length falls to
            // zero, delete the notification key from the observer map
            if (observers.size == 0) {
                observerMap.remove(notificationName)
            }
        }
    }

    /**
     * <P>Register an <code>IMediator</code> instance with the <code>View</code>.</P>
     *
     * <P>Registers the <code>IMediator</code> so that it can be retrieved by name,
     * and further interrogates the <code>IMediator</code> for its
     * <code>INotification</code> interests.</P>
     *
     * <P>If the <code>IMediator</code> returns any <code>INotification</code>
     * names to be notified about, an <code>Observer</code> is created encapsulating
     * the <code>IMediator</code> instance's <code>handleNotification</code> method
     * and registering it as an <code>Observer</code> for all <code>INotifications</code> the
     * <code>IMediator</code> is interested in.</P>
     *
     * @param mediator a reference to the <code>IMediator</code> instance
     */
    override fun registerMediator(mediator: IMediator) {
        // do not allow re-registration (you must to removeMediator fist)
        if (mediatorMap.containsKey(mediator.name)) return

        mediator.initializeNotifier(multitonKey)

        // Register the Mediator for retrieval by name
        mediatorMap[mediator.name] = mediator

        // Get Notification interests, if any.
        val interests = mediator.listNotificationInterests()

        // Register Mediator as an observer for each notification of interests
        if (interests.isNotEmpty()) {
            // Create Observer referencing this mediator's handleNotification method
            val observer = Observer(mediator::handleNotification, WeakReference(mediator))

            // Register Mediator as Observer for its list of Notification interests
            for(notificationName in interests) {
                registerObserver(notificationName, observer)
            }
        }

        // alert the mediator that it has been registered
        mediator.onRegister()
    }

    /**
     * <P>Retrieve an <code>IMediator</code> from the <code>View</code>.</P>
     *
     * @param mediatorName the name of the <code>IMediator</code> instance to retrieve.
     * @return the <code>IMediator</code> instance previously registered with the given <code>mediatorName</code>.
     */
    override fun retrieveMediator(mediatorName: String): IMediator? {
        return mediatorMap[mediatorName]
    }

    /**
     * <P>Check if a Mediator is registered or not</P>
     *
     * @param mediatorName mediator name
     * @return whether a Mediator is registered with the given <code>mediatorName</code>.
     */
    override fun hasMediator(mediatorName: String): Boolean {
        return mediatorMap.containsKey(mediatorName)
    }

    /**
     * <P>Remove an <code>IMediator</code> from the <code>View</code>.</P>
     *
     * @param mediatorName name of the <code>IMediator</code> instance to be removed.
     * @return the <code>IMediator</code> that was removed from the <code>View</code>
     */
    override fun removeMediator(mediatorName: String): IMediator? {
        return mediatorMap[mediatorName]?.let { mediator ->
            // for every notification this mediator is interested in...
            val interests = mediator.listNotificationInterests()
            for (notificationName in interests) {
                // remove the observer linking the mediator
                // to the notification interest
                removeObserver(notificationName, mediator)
            }

            // remove the mediator from the map
            mediatorMap.remove(mediatorName)

            // alert the mediator that it has been removed
            mediator.onRemove()
            return mediator
        }
    }

}