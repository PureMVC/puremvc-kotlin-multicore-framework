//
//  ViewTestNote.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
//

package org.puremvc.kotlin.multicore.core

import org.puremvc.kotlin.multicore.interfaces.INotification
import org.puremvc.kotlin.multicore.patterns.observer.Notification

/**
 * A Notification class used by ViewTest.
 *
 * @see ViewTest ViewTest
 */
class ViewTestNote(override val name: String, override var body: Any?) : Notification(name, body), INotification {

    companion object {
        /**
         * The name of this Notification.
         */
        const val NAME = "ViewTestNote"
    }

}