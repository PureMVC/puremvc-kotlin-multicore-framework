//
//  SimpleCommandTestVO.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
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