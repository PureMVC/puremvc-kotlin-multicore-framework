//
//  MacroCommand.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.command

import org.puremvc.kotlin.multicore.interfaces.ICommand
import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.patterns.observer.Notifier
import java.util.*

/**
 * <P>A base <code>ICommand</code> implementation that executes other <code>ICommand</code>s.</P>
 *
 * <P>A <code>MacroCommand</code> maintains an list of
 * <code>ICommand</code> Class references called <i>SubCommands</i>.</P>
 *
 * <P>When <code>execute</code> is called, the <code>MacroCommand</code>
 * instantiates and calls <code>execute</code> on each of its <i>SubCommands</i> turn.
 * Each <i>SubCommand</i> will be passed a reference to the original
 * <code>INotification</code> that was passed to the <code>MacroCommand</code>'s
 * <code>execute</code> method.</P>
 *
 * <P>Unlike <code>SimpleCommand</code>, your subclass
 * should not override <code>execute</code>, but instead, should
 * override the <code>initializeMacroCommand</code> method,
 * calling <code>addSubCommand</code> once for each <i>SubCommand</i>
 * to be executed.</P>
 *
 * @see org.puremvc.kotlin.multicore.core.Controller Controller
 * @see org.puremvc.kotlin.multicore.patterns.observer.Notification Notification
 * @see org.puremvc.kotlin.multicore.patterns.command.SimpleCommand SimpleCommand
 *
 * @constructor Creates a MacroCommand
 */

open class MacroCommand : Notifier(), ICommand {

    protected val subcommands = Vector<() -> ICommand>()

    /**
     * <P>Constructor.</P>
     *
     * <P>You should not need to define a constructor,
     * instead, override the <code>initializeMacroCommand</code>
     * method.</P>
     *
     * <P>If your subclass does define a constructor, be
     * sure to call <code>super()</code>.</P>
     */
    init {
        initializeMacroCommand()
    }

    /**
     * <P>Initialize the <code>MacroCommand</code>.</P>
     *
     * <P>In your subclass, override this method to
     * initialize the <code>MacroCommand</code>'s <i>SubCommand</i>
     * list with <code>ICommand</code> class references like
     * this:</P>
     *
     * <pre>
     * {@code // Initialize MyMacroCommand
     * open fun initializeMacroCommand()
     * {
     *      addSubCommand { com.me.myapp.controller.FirstCommand() };
     *      addSubCommand { com.me.myapp.controller.SecondCommand() };
     *      addSubCommand { com.me.myapp.controller.ThirdCommand() ];
     * }
     * }
     * </pre>
     *
     * <P>Note that <i>SubCommand</i>s may be any <code>ICommand</code> implementor,
     * <code>MacroCommand</code>s or <code>SimpleCommands</code> are both acceptable.</P>
     */
    open fun initializeMacroCommand() {

    }

    /**
     * <P>Add a <i>SubCommand</i>.</P>
     *
     * <P>The <i>SubCommands</i> will be called in First In/First Out (FIFO)
     * order.</P>
     *
     * @param factory a reference to the factory of the <code>ICommand</code>.
     */
    open fun addSubCommand(factory: () -> ICommand) {
        subcommands.add(factory)
    }

    /**
     * <P>Execute this <code>MacroCommand</code>'s <i>SubCommands</i>.</P>
     *
     * <P>The <i>SubCommands</i> will be called in First In/First Out (FIFO)
     * order.</P>
     *
     * @param notification the <code>INotification</code> object to be passsed to each <i>SubCommand</i>.
     */
    override fun execute(notification: INotification) {
        while (subcommands.isNotEmpty()) {
            val factory = subcommands.removeAt(0)
            val commandInstance = factory()
            commandInstance.initializeNotifier(multitonKey)
            commandInstance.execute(notification)
        }
    }

}