//
//  ProxyTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
//

package org.puremvc.kotlin.multicore.patterns.proxy

import org.puremvc.kotlin.multicore.interfaces.IProxy
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test the PureMVC Proxy class.
 *
 * @see org.puremvc.kotlin.multicore.interfaces.IProxy IProxy
 * @see Proxy Proxy
 */
class ProxyTest {

    /**
     * Tests getting the name using Proxy class accessor method. Setting can only be done in constructor.
     */
    @Test
    fun testNameAccessors() {
        val proxy: IProxy = Proxy("TestProxy")

        // test assertions
        assertEquals("TestProxy", proxy.name)
    }

    /**
     * Tests setting and getting the data using Proxy class accessor methods.
     */
    @Test
    fun testDataAccessors() {
        // Create a new Proxy and use accessors to set the data
        val proxy: IProxy = Proxy("colors")
        proxy.data = arrayOf("red", "green", "blue")

        val data = proxy.data as Array<*>

        // test assertions
        assertEquals(3, data.size)
        assertEquals("red", data[0])
        assertEquals("green", data[1])
        assertEquals("blue", data[2])
    }

    /**
     * Tests setting the name and body using the Notification class Constructor.
     */
    @Test
    fun testConstructor() {
        // Create a new Proxy using the Constructor to set the name and data
        val proxy: IProxy = Proxy("colors", arrayOf("red", "green", "blue"))
        val data = proxy.data as Array<*>

        // test assertions
        assertEquals("colors", proxy.name)
        assertEquals("red", data[0])
        assertEquals("green", data[1])
        assertEquals("blue", data[2])
    }

    @Test
    fun testDefaultConstructor() {
        val proxy: IProxy = Proxy()

        // test assertions
        assertEquals(Proxy.NAME, proxy.name)
    }

}