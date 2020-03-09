//
//  SimpleCommandTestVO.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.command

/**
 * A utility class used by SimpleCommandTest.
 *
 * @see SimpleCommandTest SimpleCommandTest
 * @see org.puremvc.kotlin.multicore.patterns.command.SimpleCommandTestCommand SimpleCommandTestCommand
 */
class SimpleCommandTestVO(var input: Int) {
    var result: Int = 0
}