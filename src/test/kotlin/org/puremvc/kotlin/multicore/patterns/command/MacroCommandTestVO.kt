//
//  MacroCommandTestVO.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.command

/**
 * A utility class used by MacroCommandTest.
 *
 * @see MacroCommandTest MacroCommandTest
 * @see org.puremvc.kotlin.multicore.patterns.command.MacroCommandTestCommand MacroCommandTestCommand
 * @see org.puremvc.kotlin.multicore.patterns.command.MacroCommandTestSub1Command MacroCommandTestSub1Command
 * @see org.puremvc.kotlin.multicore.patterns.command.MacroCommandTestSub2Command MacroCommandTestSub2Command
 */
class MacroCommandTestVO(val input: Int) {

    var result1: Int = 0

    var result2: Int = 0

}