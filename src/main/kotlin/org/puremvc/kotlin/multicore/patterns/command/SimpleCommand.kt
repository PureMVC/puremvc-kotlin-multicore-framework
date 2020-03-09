//
//  SimpleCommand.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.command

import org.puremvc.kotlin.multicore.interfaces.ICommand
import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.patterns.observer.Notifier

/**
 * <P>A base <code>ICommand</code> implementation.</P>
 *
 * <P>Your subclass should override the <code>execute</code>
 * method where your business logic will handle the <code>INotification</code>.</P>
 *
 * @see org.puremvc.kotlin.multicore.core.Controller Controller
 * @see org.puremvc.kotlin.multicore.patterns.observer.Notification Notification
 * @see MacroCommand MacroCommand
 *
 * @constructor Creates a SimpleCommand
 */

open class SimpleCommand : Notifier(), ICommand {

    /**
     * <P>Fulfill the use-case initiated by the given <code>INotification</code>.</P>
     *
     * <P>In the Command Pattern, an application use-case typically
     * begins with some user action, which results in an <code>INotification</code> being broadcast, which
     * is handled by business logic in the <code>execute</code> method of an
     * <code>ICommand</code>.</P>
     *
     * @param notification the <code>INotification</code> to handle.
     */
    override fun execute(notification: INotification) {

    }

}