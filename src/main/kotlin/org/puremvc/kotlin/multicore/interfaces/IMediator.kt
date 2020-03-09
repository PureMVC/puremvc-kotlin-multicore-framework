//
//  IMediator.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.interfaces

/**
 * <P>The interface definition for a PureMVC Mediator.</P>
 *
 * <P>In PureMVC, <code>IMediator</code> implementors assume these responsibilities:</P>
 *
 * <UL>
 * <LI>Implement a common method which returns a list of all <code>INotification</code>s
 * the <code>IMediator</code> has interest in.</LI>
 * <LI>Implement a notification callback method.</LI>
 * <LI>Implement methods that are called when the IMediator is registered or removed from the View.</LI>
 * </UL>
 *
 * <P>Additionally, <code>IMediator</code>s typically:</P>
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
 *
 * <P>A concrete IMediator implementor usually looks something like this:</P>
 *
 * <pre>
 * {@code import org.puremvc.kotlin.multicore.patterns.mediator.*;
 * import org.puremvc.kotlin.multicore.patterns.observer.*;
 * import org.puremvc.kotlin.multicore.core.view.*;
 *
 * import com.me.myapp.model.*;
 * import com.me.myapp.view.*;
 * import com.me.myapp.controller.*;
 *
 * import javax.swing.JComboBox;
 * import java.awt.event.ActionListener;
 *
 * class ViewTestMediator(viewComponent: Any) : Mediator(NAME, viewComponent), IMediator {
 *
 *     init {
 *         combo.addActionListener( this )
 *     }
 *
 *     override fun listNotificationInterests(): Array<String> {
 *         return arrayOf(MyFacade.SET_SELECTION,
 *                  MyFacade.SET_DATAPROVIDER)
 *     }
 *
 *     override fun handleNotification(notification: INotification) {
 *         switch ( notification.name ) {
 *             MyFacade.SET_SELECTION -> setSelection(notification)
 *             MyFacade.SET_DATAPROVIDER -> setDataProvider(notification);
 *         }
 *     }
 *
 *     // Set the data provider of the combo box
 *     fun setDataProvider( notification: INotification ) {
 *         combo.setModel(notification.body)
 *     }
 *
 *     // Invoked when the combo box dispatches a change event, we send a
 *     // notification with the
 *     fun actionPerformed(event: ActionEvent) {
 *         sendNotification( MyFacade.MYCOMBO_CHANGED, this )
 *     }
 *
 *     // A private getter for accessing the view object by class
 *     fun combo(): JComboBox {
 *         return viewComponent
 *     }
 *
 * }
 * }
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