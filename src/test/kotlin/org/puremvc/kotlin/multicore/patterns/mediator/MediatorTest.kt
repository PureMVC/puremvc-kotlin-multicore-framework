//
//  MediatorTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
//

package org.puremvc.kotlin.multicore.patterns.mediator

import java.lang.ref.WeakReference
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Test the PureMVC Mediator class.
 *
 * @see org.puremvc.kotlin.multicore.interfaces.IMediator IMediator
 * @see Mediator Mediator
 */
class MediatorTest {

    /**
     * Tests getting the name using Mediator class accessor method.
     */
    @Test
    fun testNameAccessor() {
        // Create a new Mediator and use accessors to set the mediator name
        val mediator = Mediator()

        // test assertions
        assertEquals(Mediator.NAME, mediator.name)
    }

    /**
     * Tests getting the name using Mediator class accessor method.
     */
    @Test
    fun testViewAccessor() {
        // Create a view object
        val view = Any()

        // Create a new Mediator and pass the view object
        val mediator = Mediator(Mediator.NAME, WeakReference(view))

        // test assertions
        assertNotNull(mediator.viewComponent)
    }
}