//
//  ModelTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020-2026 Saad Shams <saad.shams@puremvc.org>
//  Licensed under the BSD 3-Clause License
//

package org.puremvc.kotlin.multicore.core

import org.puremvc.kotlin.multicore.interfaces.IProxy
import org.puremvc.kotlin.multicore.patterns.proxy.Proxy
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Test the PureMVC Model class.
 */
class ModelTest {

    /**
     * Tests the Model Multiton Factory Method
     */
    @Test
    fun testGetInstance() {
        // Test Factory Method
        val model = Model.getInstance("ModelTestKey1") { key -> Model(key) }

        // test assertions
        assertNotNull(model)
    }

    /**
     * Tests the proxy registration and retrieval methods.
     *
     * <P>Tests <code>registerProxy</code> and <code>retrieveProxy</code> in the same test.
     * These methods cannot currently be tested separately
     * in any meaningful way other than to show that the
     * methods do not throw exception when called. </P>
     */
    @Test
    fun testRegisterAndRetrieveProxy() {
        // register a proxy and retrieve it.
        val model = Model.getInstance("ModelTestKey2") { key -> Model(key) }
        model.registerProxy(Proxy("colors", arrayOf("red", "green", "blue")))
        val proxy = model.retrieveProxy("colors")
        val data = (proxy!!.data as? Array<*>)?.filterIsInstance<String>()

        // test assertions
        assertNotNull(data)
        assertEquals(3, data?.size)
        assertEquals("red", data?.get(0))
        assertEquals("green", data?.get(1))
        assertEquals("blue", data?.get(2))
    }

    /**
     * Tests the proxy removal method.
     */
    @Test
    fun testRegisterAndRemoveProxy() {
        // register a proxy, remove it, then try to retrieve it
        val model = Model.getInstance("ModelTestKey4") { key -> Model(key) }
        var proxy = Proxy("sizes", arrayOf("7", "13", "21"))
        model.registerProxy(proxy)

        // remove the proxy
        val removedProxy: IProxy? = model.removeProxy("sizes")

        // assert that we removed the appropriate proxy
        assertEquals("sizes", removedProxy!!.name)

        // ensure that the proxy is no longer retrievable from the model
        assertNull(model.retrieveProxy("sizes"))
    }

    /**
     * Tests the hasProxy Method
     */
    @Test
    fun testHasProxy() {
        // register a proxy
        val model = Model.getInstance("ModelTestKey4") { key -> Model(key) }
        val proxy = Proxy("aces", arrayOf("clubs", "spades", "hearts", "diamonds"))
        model.registerProxy(proxy)

        // assert that the model.hasProxy method returns true
        // for that proxy name
        assertTrue(model.hasProxy("aces"))

        // remove the proxy
        model.removeProxy("aces")

        // assert that the model.hasProxy method returns false
        // for that proxy name
        assertFalse(model.hasProxy("aces"))
    }

    /**
     * Tests that the Model calls the onRegister and onRemove methods
     */
    @Test
    fun testOnRegisterAndOnRemove() {
        // Get a Multiton Model instance
        val model = Model.getInstance("ModelTestKey5") { key -> Model(key) }

        // Create and register the test mediator
        val proxy = ModelTestProxy()
        model.registerProxy(proxy)

        // assert that onRegister was called, and the proxy responded by setting its data accordingly
        assertEquals(ModelTestProxy.ON_REGISTER_CALLED, proxy.data)

        // Remove the component
        model.removeProxy(ModelTestProxy.NAME)

        // assert that onRemove was called, and the proxy responded by setting its data accordingly
        assertEquals(ModelTestProxy.ON_REMOVE_CALLED, proxy.data)
    }

    @Test
    fun testRemoveModel() {
        // Get a Multiton Model instance
        Model.getInstance("ModelTestKey6") { key -> Model(key) }

        // remove the model
        Model.removeModel("ModelTestKey6")

        // re-create the model without throwing an exception
        Model("ModelTestKey6")
    }

}