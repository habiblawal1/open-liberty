/*
 * Copyright IBM Corp. 2023
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.ibm.ws.jndi.global.fat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ibm.websphere.simplicity.config.ServerConfiguration;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.impl.LibertyServerFactory;
import componenttest.topology.utils.HttpUtils;

/**
 * Test to make sure that JNDIEntry registers the correct services
 */
public class JNDIEntryTests {

    /**
     * This test makes sure that the JNDI Entry registers appropriate services for JNDI entries and also that they get unregistered.
     * 
     * @throws Exception
     */
    @Test
    public void testJNDIEntry() throws Exception {
        // Grab the server
        LibertyServer server = LibertyServerFactory.getStartedLibertyServer("jndi_entry_fat");
        try {
            /*
             * Wait for two debug level messages saying that the JNDI entries are registered.
             * Use a fairly short time out as we've already waited for the app to start
             * so this should already have appeared.
             */
            assertEquals("No debug message in the trace.log saying the two jndi entries defined in the server.xml were registered", 2,
                         server.waitForMultipleStringsInLog(2, ".*Registering JNDIEntry", 10000, server.getMatchingLogFile("trace.log")));

            // Now make sure that the OSGi services were registered using a test servlet
            HttpUtils.findStringInUrl(server, "/ReadJndiEntry/ReadJndiEntry",
                                      "JNDI Entry found for stringJndiEntry", "Value of JNDI Entry is: String Value",
                                      "JNDI Entry found for doubleJndiEntry", "Value of JNDI Entry is: 2.0");

            // Delete the entries and make sure they are unregistered
            ServerConfiguration serverConfig = server.getServerConfiguration();
            serverConfig.getJndiEntryElements().clear();
            server.updateServerConfiguration(serverConfig);

            // Wait for the update
            assertNotNull("The server configuration was not updated", server.waitForStringInLog("CWWKG0017I"));

            // Also wait for the debug trace messages saying that the services were unregistered
            assertEquals("No debug message in the trace.log saying the two jndi entries deleted from the server.xml were unregistered", 2,
                         server.waitForMultipleStringsInLog(2, ".*Unregistering JNDIEntry", 10000, server.getMatchingLogFile("trace.log")));

            // Now repeat the test with the servlet, it shouldn't find any entries anymore

            HttpUtils.findStringInUrl(server, "/ReadJndiEntry/ReadJndiEntry",
                                      "javax.naming.NameNotFoundException: stringJndiEntry",
                                      "javax.naming.NameNotFoundException: doubleJndiEntry");
        } finally {
            server.stopServer();
        }
    }

    @Test
    public void testJNDIDecode() throws Exception {
        LibertyServer server = LibertyServerFactory.getStartedLibertyServer("jndi_entry_decode");
        try {
            /*
             * Wait for a debug level message saying that the JNDI entry is registered.
             * Use a fairly short time out as we've already waited for the app to start
             * so this should already have appeared.
             */
            assertEquals("No debug message in the trace.log saying  a jndi entry defined in the server.xml was registered", 1,
                         server.waitForMultipleStringsInLog(1, ".*Registering JNDIEntry", 10000, server.getMatchingLogFile("trace.log")));

            String decryptedStringValue = "foobar";
            String decryptedIntValue = "12345";
            // Check to make sure the decrypted JndiEntry value for "{xor}OTAwPT4t" is returned
            HttpUtils.findStringInUrl(server, "/ReadJndiEntry/ReadJndiEntry",
                                      "JNDI Entry found for stringJndiEntry", "Value of JNDI Entry is: " + decryptedStringValue);
            HttpUtils.findStringInUrl(server, "/ReadJndiEntry/ReadJndiEntry",
                                      "JNDI Entry found for stringJndiEntry", "Value of JNDI Entry is: " + decryptedIntValue);

        } finally {
            server.stopServer();
        }
    }

    /**
     * This test makes sure that changing the ID of the JNDI entry does not prevent it being registered correctly.
     * 
     * @throws Exception
     */
    @Test
    public void testJNDIEntryIDUpate() throws Exception {
        // Grab the server
        LibertyServer server = LibertyServerFactory.getStartedLibertyServer("jndi_entry_id_update");
        try {
            /*
             * Wait for two debug level messages saying that the JNDI entries are registered.
             * Use a fairly short time out as we've already waited for the app to start
             * so this should already have appeared.
             */
            assertEquals("No debug message in the trace.log saying the two jndi entries defined in the server.xml were registered, found "
                         + server.getServerConfiguration().getJndiEntryElements(), 2,
                         server.waitForMultipleStringsInLog(2, ".*Registering JNDIEntry", 10000, server.getMatchingLogFile("trace.log")));

            // Now make sure that the OSGi services were registered using a test servlet
            HttpUtils.findStringInUrl(server, "/ReadJndiEntry/ReadJndiEntry",
                                      "JNDI Entry found for stringJndiEntry", "Value of JNDI Entry is: String Value",
                                      "JNDI Entry found for doubleJndiEntry", "Value of JNDI Entry is: 2.0");

            // Now switch the server configuration to check that we don't get a non-unique attribute value error
            server.setServerConfigurationFile("JNDIEntryUpdate/IDUpdate.xml");
            ServerConfiguration config = server.getServerConfiguration();
            server.updateServerConfiguration(config);

            // Wait for the update
            assertNotNull("The server configuration was not updated", server.waitForStringInLog("CWWKG0017I"));

            // Now make sure that the OSGi services are still correctly registered
            HttpUtils.findStringInUrl(server, "/ReadJndiEntry/ReadJndiEntry",
                                      "JNDI Entry found for stringJndiEntry", "Value of JNDI Entry is: 2.0",
                                      "JNDI Entry found for doubleJndiEntry", "Value of JNDI Entry is: String Value");

        } finally {
            server.stopServer();
        }
    }

    /**
     * This test is to check that creating a new JNDI entry gets registered correctly.
     * 
     * @throws Exception
     */
    @Test
    public void testDynamicJNDIEntryUpdate() throws Exception {
        // Grab the server
        LibertyServer server = LibertyServerFactory.getStartedLibertyServer("jndi_entry_dynamic_update");
        try {
            /*
             * Wait for two debug level messages saying that the JNDI entries are registered.
             * Use a fairly short time out as we've already waited for the app to start
             * so this should already have appeared.
             */
            assertEquals("No debug message in the trace.log saying the two jndi entries defined in the server.xml were registered, found "
                         + server.getServerConfiguration().getJndiEntryElements(), 1,
                         server.waitForMultipleStringsInLog(1, ".*Registering JNDIEntry", 10000, server.getMatchingLogFile("trace.log")));

            // Now make sure that the OSGi services were registered using a test servlet
            HttpUtils.findStringInUrl(server, "/ReadJndiEntry/ReadJndiEntry",
                                      "javax.naming.NameNotFoundException: stringJndiEntry",
                                      "JNDI Entry found for doubleJndiEntry", "Value of JNDI Entry is: 2.0");

            // Now switch the server configuration to check that we don't get a non-unique attribute value error
            server.setServerConfigurationFile("JNDIEntryUpdate/uncommentedServer.xml");
            ServerConfiguration config = server.getServerConfiguration();
            server.updateServerConfiguration(config);

            // Wait for the update
            assertNotNull("The server configuration was not updated", server.waitForStringInLog("CWWKG0017I"));

            // Now make sure that the OSGi services are still correctly registered
            HttpUtils.findStringInUrl(server, "/ReadJndiEntry/ReadJndiEntry",
                                      "JNDI Entry found for stringJndiEntry", "Value of JNDI Entry is: 2.0",
                                      "JNDI Entry found for doubleJndiEntry", "Value of JNDI Entry is: String Value");

        } finally {
            server.stopServer();
        }
    }
}
