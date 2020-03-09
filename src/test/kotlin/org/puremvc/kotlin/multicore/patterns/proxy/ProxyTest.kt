//
//  ProxyTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.proxy

import org.junit.Assert
import org.junit.Test
import org.puremvc.kotlin.multicore.interfaces.IProxy

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
        Assert.assertEquals("TestProxy", proxy.name)
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
        Assert.assertEquals(3, data.size)
        Assert.assertEquals("red", data[0])
        Assert.assertEquals("green", data[1])
        Assert.assertEquals("blue", data[2])
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
        Assert.assertEquals("colors", proxy.name)
        Assert.assertEquals("red", data[0])
        Assert.assertEquals("green", data[1])
        Assert.assertEquals("blue", data[2])
    }

    @Test
    fun testDefaultConstructor() {
        val proxy: IProxy = Proxy()

        // test assertions
        Assert.assertEquals(Proxy.NAME, proxy.name)
    }

}