/*
 * WLP Copyright IBM Corp. 2013, 2023
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.ibm.ws.jndi.global.fat;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.ibm.websphere.simplicity.config.ServerConfiguration;
import com.ibm.ws.jndi.global.fat.data.AppName;
import com.ibm.ws.jndi.global.fat.data.ServletName;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.impl.LibertyServerFactory;

/**
 * This test is to ensure that we get the expected behavior when the jndi
 * feature is not enabled.
 */
public class JNDIFeatureTests {

    // private static LibertyServer server = LibertyServerFactory.getLibertyServer("jndi_fat");

    // @ClassRule
    // public static TestRule stopServerRule = stopAutomatically(server);
    // private static ServletMethodRunner runner;

    // @BeforeClass
    // public static void setup() {
    //     runner = new ServletMethodRunner(AppName.JNDI_GLOBAL, ServletName.JNDI_TEST_SERVLET, server);
    // }

    // /**
    //  * Verify that behavior is expected with and without the jndi feature
    //  * enabled dynamically. Start with jndi not enabled and dynamically modify.
    //  * 
    //  * 1. Run method without jndi enabled - verify correct exception message
    //  * 2. Run method with jndi enabled - verify it works properly
    //  * 3. Run method without jndi enabled - verify correct exception message
    //  */
    // @Test
    // public void testNoJNDIFeature() throws Exception {
    //     // Remove jndi feature
    //     ServerConfiguration serverConfig = server.getServerConfiguration();
    //     serverConfig.getFeatureManager().getFeatures().remove("jndi-1.0");
    //     server.updateServerConfiguration(serverConfig);

    //     server.startServer();

    //     // Run test expecting the error message be returned
    //     runErrorCase();

    //     // Enable jndi feature
    //     modifyFeatureAndWait(true);

    //     // Run test, expecting clean run now that jndi is enabled
    //     runner.run("testRebind");

    //     // Remove jndi feature
    //     modifyFeatureAndWait(false);

    //     // Make sure in the same method invocation we get the message again
    //     runErrorCase();

    //     // Stop the server and reset the jndi feature to be enabled again (just in case tests are not run
    //     // in order)
    //     server.stopServer();

    //     serverConfig = server.getServerConfiguration();
    //     serverConfig.getFeatureManager().getFeatures().add("jndi-1.0");
    //     server.updateServerConfiguration(serverConfig);
    // }

    // /**
    //  * Verify that behavior is expected with and without the jndi feature
    //  * enabled dynamically. Start with jndi enabled and dynamically modify.
    //  * 
    //  * 1. Run method with jndi enabled - verify it works properly
    //  * 2. Run method without jndi enabled - verify correct exception message
    //  * 3. Run method with jndi enabled - verify it works properly
    //  */
    // @Test
    // public void testDynamicFeature() throws Exception {
    //     server.startServer();

    //     // Hit servlet and verify that new InitialContext.lookup() works
    //     runner.run("testRebind");

    //     // Remove jndi feature
    //     modifyFeatureAndWait(false);

    //     // Hit the same servlet method, looking for the expected error message
    //     runErrorCase();

    //     // Enable jndi feature
    //     modifyFeatureAndWait(true);

    //     // Verify that same servlet request now works again
    //     runner.run("testRebind");

    //     server.stopServer();
    // }

    // /**
    //  * Wait for the feature and config update to finish. This depends
    //  * upon a previous call to a setMark* method.
    //  * 
    //  * @throws Exception
    //  */
    // private void modifyFeatureAndWait(boolean add) throws Exception {
    //     ServerConfiguration serverConfig = server.getServerConfiguration();
    //     if (add) {
    //         serverConfig.getFeatureManager().getFeatures().add("jndi-1.0");
    //     } else {
    //         serverConfig.getFeatureManager().getFeatures().remove("jndi-1.0");
    //     }

    //     server.setMarkToEndOfLog();
    //     server.updateServerConfiguration(serverConfig);

    //     Assert.assertNotNull("Wait for feature update did not succeed.",
    //                          server.waitForStringInLogUsingMark("CWWKF0008I"));
    //     Assert.assertNotNull("Wait for config update did not succeed.",
    //                          server.waitForStringInLogUsingMark("CWWKG0017I"));
    // }

    // /**
    //  * Run the test using the method that returns the result, since it won't be
    //  * "SUCCESS" in the error case. Verify that the message from the fake factory
    //  * was observed (and not some other exception).
    //  */
    // private void runErrorCase() throws Exception {
    //     String errorMessage = runner.getResponseFor("testRebind");
    //     Assert.assertTrue("Did not get the correct error message from servlet invocation: " + errorMessage,
    //                       errorMessage.matches(".*CWWKE0800W.*JNDITestServlet.*"));
    // }

    @Test 
    public void sayHello(){
        System.out.println("I am saying hello");
    }
}
