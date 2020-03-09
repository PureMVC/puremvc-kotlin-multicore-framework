//
//  MediatorTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.mediator

import org.junit.Assert
import org.junit.Test

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
        Assert.assertEquals(Mediator.NAME, mediator.name)
    }

    /**
     * Tests getting the name using Mediator class accessor method.
     */
    @Test
    fun testViewAccessor() {
        // Create a view object
        val view = Any()

        // Create a new Mediator and pass the view object
        val mediator = Mediator(Mediator.NAME, view)

        // test assertions
        Assert.assertNotNull(mediator.viewComponent)
    }
}