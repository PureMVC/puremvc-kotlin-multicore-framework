//
//  FacadeTest.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.patterns.facade

import org.junit.Assert
import org.junit.Test
import org.puremvc.kotlin.multicore.interfaces.IProxy
import org.puremvc.kotlin.multicore.patterns.mediator.Mediator
import org.puremvc.kotlin.multicore.patterns.proxy.Proxy

/**
 * Test the PureMVC Facade class.
 *
 * @see org.puremvc.kotlin.multicore.patterns.facade.FacadeTestVO FacadeTestVO
 * @see org.puremvc.kotlin.multicore.patterns.facade.FacadeTestCommand FacadeTestCommand
 */
class FacadeTest {

    /**
     * Tests the Facade Multiton Factory Method
     */
    @Test
    fun testGetInstance() {
        // Test Factory Method
        val facade = Facade.getInstance("FacadeTestKey1") { key -> Facade(key) }

        // test assertions
        Assert.assertNotNull(facade)
    }

    /**
     * Tests Command registration and execution via the Facade.
     *
     * <P>This test gets a Multiton Facade instance
     * and registers the FacadeTestCommand class
     * to handle 'FacadeTest' Notifcations.<P>
     *
     * <P>It then sends a notification using the Facade.
     * Success is determined by evaluating
     * a property on an object placed in the body of
     * the Notification, which will be modified by the Command.</P>
     *
     */
    @Test
    fun testRegisterCommandAndSendNotification() {
        // Create the Facade, register the FacadeTestCommand to
        // handle 'FacadeTest' notifications
        val facade = Facade.getInstance("FacadeTestKey2") { key -> Facade(key) }
        facade.registerCommand("FacadeTestNote") { FacadeTestCommand() }

        // Send notification. The Command associated with the event
        // (FacadeTestCommand) will be invoked, and will multiply
        // the vo.input value by 2 and set the result on vo.result
        val vo = FacadeTestVO(32)
        facade.sendNotification("FacadeTestNote", vo)

        // test assertions
        Assert.assertEquals(64, vo.result)
    }

    /**
     * Tests Command removal via the Facade.
     *
     * <P>This test gets a Multiton Facade instance
     * and registers the FacadeTestCommand class
     * to handle 'FacadeTest' Notifcations. Then it removes the command.<P>
     *
     * <P>It then sends a Notification using the Facade.
     * Success is determined by evaluating
     * a property on an object placed in the body of
     * the Notification, which will NOT be modified by the Command.</P>
     *
     */
    @Test
    fun testRegisterAndRemoveCommandAndSendNotification() {
        // Create the Facade, register the FacadeTestCommand to
        // handle 'FacadeTest' events
        val facade = Facade.getInstance("FacadeTestKey3") { key -> Facade(key) }
        facade.registerCommand("FacadeTestNote") { FacadeTestCommand() }
        facade.removeCommand("FacadeTestNote")

        // Send notification. The Command associated with the event
        // (FacadeTestCommand) will NOT be invoked, and will NOT multiply
        // the vo.input value by 2
        val vo = FacadeTestVO(32)
        facade.sendNotification("FacadeTestNote", vo)

        // test assertions
        Assert.assertEquals(0, vo.result)
    }

    /**
     * Tests the regsitering and retrieving Model proxies via the Facade.
     *
     * <P>Tests <code>registerProxy</code> and <code>retrieveProxy</code> in the same test.
     * These methods cannot currently be tested separately
     * in any meaningful way other than to show that the
     * methods do not throw exception when called. </P>
     */
    @Test
    fun testRegisterAndRetrieveProxy() {
        // register a proxy and retrieve it.
        val facade = Facade.getInstance("FacadeTestKey4") { key -> Facade(key) }
        facade.registerProxy(Proxy("colors", arrayOf("red", "green", "blue")))
        val proxy: IProxy? = facade.retrieveProxy("colors")

        // test assertions
        Assert.assertNotNull(proxy)

        // retrieve data from proxy
        val data = (proxy?.data as? Array<*>)?.filterIsInstance<String>()

        // test assertions
        Assert.assertNotNull(data)
        Assert.assertEquals(3, data?.size)
        Assert.assertEquals(data?.get(0), "red")
        Assert.assertEquals(data?.get(1), "green")
        Assert.assertEquals(data?.get(2), "blue")
    }

    /**
     * Tests the removing Proxies via the Facade.
     */
    @Test
    fun testRegisterAndRemoveProxy() {
        // register a proxy, remove it, then try to retrieve it
        val facade = Facade.getInstance("FacadeTestKey5") { key -> Facade(key) }
        val proxy: IProxy = Proxy("sizes", arrayOf("7", "13", "21"))
        facade.registerProxy(proxy)

        // remove the proxy
        val removeProxy = facade.removeProxy("sizes")

        // assert that we removed the appropriate proxy
        Assert.assertEquals("sizes", removeProxy?.name)

        // test assertions - make sure we can no longer retrieve the proxy from the model
        Assert.assertNull(facade.retrieveProxy("sizes"))
    }

    /**
     * Tests registering, retrieving and removing Mediators via the Facade.
     */
    @Test
    fun testRegisterRetrieveAndRemoveMediator() {
        // register a mediator, remove it, then try to retrieve it
        val facade = Facade.getInstance("FacadeTestKey6") { key -> Facade(key) }
        facade.registerMediator(Mediator(Mediator.NAME, Any()))

        // retrieve the mediator
        Assert.assertNotNull(facade.retrieveMediator(Mediator.NAME))

        // remove the mediator
        val removedMediator = facade.removeMediator(Mediator.NAME)

        // assert that we have removed the appropriate mediator
        Assert.assertEquals(Mediator.NAME, removedMediator?.name)

        // assert that the mediator is no longer retrievable
        Assert.assertNull(facade.retrieveMediator(Mediator.NAME))
    }

    @Test
    fun testHasProxy() {
        // register a Proxy
        val facade = Facade.getInstance("FacadeTestKey7") { key -> Facade(key) }
        facade.registerProxy(Proxy("hasProxyTest", arrayOf(1, 2, 3)))

        // assert that the model.hasProxy method returns true
        // for that proxy name
        Assert.assertTrue(facade.hasProxy("hasProxyTest"))
    }

    /**
     * Tests the hasMediator Method
     */
    @Test
    fun testHasMediator() {
        // register a Mediator
        val facade = Facade.getInstance("FacadeTestKey8") { key -> Facade(key) }
        facade.registerMediator(Mediator("facadeHasMediatorTest", Any()))

        // assert that the facade.hasMediator method returns true
        // for that mediator name
        Assert.assertTrue(facade.hasMediator("facadeHasMediatorTest"))

        facade.removeMediator("facadeHasMediatorTest")

        // assert that the facade.hasMediator method returns false
        // for that mediator name
        Assert.assertFalse(facade.hasMediator("facadeHasMediatorTest"))
    }

    /**
     * Test hasCommand method.
     */
    @Test
    fun testHasCommand() {
        // register the ControllerTestCommand to handle 'hasCommandTest' notes
        val facade = Facade.getInstance("FacadeTestKey10") { key -> Facade(key) }
        facade.registerCommand("facadeHasCommandTest") { FacadeTestCommand() }

        // test that hasCommand returns true for hasCommandTest notifications
        Assert.assertTrue(facade.hasCommand("facadeHasCommandTest"))

        // Remove the Command from the Controller
        facade.removeCommand("facadeHasCommandTest")

        // test that hasCommand returns false for hasCommandTest notifications
        Assert.assertFalse(facade.hasCommand("facadeHasCommandTest"))
    }

    /**
     * Tests the hasCore and removeCore methods
     */
    @Test
    fun testHasCoreAndRemoveCore() {
        // assert that the Facade.hasCore method returns false first
        Assert.assertFalse(Facade.hasCore("FacadeTestKey11"))

        // register a Core
        Facade.getInstance("FacadeTestKey11") { key -> Facade(key) }

        Assert.assertTrue(Facade.hasCore("FacadeTestKey11"))

        // remove the Core
        Facade.removeCore("FacadeTestKey11")

        // assert that the Facade.hasCore method returns false now that the core has been removed.
        Assert.assertFalse(Facade.hasCore("FacadeTestKey11"))
    }

}