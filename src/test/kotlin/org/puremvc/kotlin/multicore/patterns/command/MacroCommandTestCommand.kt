//
//  MacroCommandTestCommand.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.command

/**
 * A MacroCommand subclass used by MacroCommandTest.
 *
 * @see MacroCommandTest MacroCommandTest
 * @see org.puremvc.kotlin.multicore.patterns.command.MacroCommandTestSub1Command MacroCommandTestSub1Command
 * @see org.puremvc.kotlin.multicore.patterns.command.MacroCommandTestSub2Command MacroCommandTestSub2Command
 * @see MacroCommandTestVO MacroCommandTestVO
 */
class MacroCommandTestCommand : MacroCommand() {

    /**
     * Initialize the MacroCommandTestCommand by adding
     * its 2 SubCommands.
     */
    override fun initializeMacroCommand() {
        addSubCommand { MacroCommandTestSub1Command() }
        addSubCommand { MacroCommandTestSub2Command() }
    }

}