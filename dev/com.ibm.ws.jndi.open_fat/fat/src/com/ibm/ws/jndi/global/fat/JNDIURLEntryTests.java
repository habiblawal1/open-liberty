/*
 * =============================================================================
 * Copyright (c) 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 * =============================================================================
 */
package com.ibm.ws.jndi.global.fat;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ibm.websphere.simplicity.config.ServerConfiguration;

import componenttest.annotation.ExpectedFFDC;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.impl.LibertyServerFactory;
import componenttest.topology.utils.HttpUtils;

/**
 * Test to make sure that JNDIEntry registers the correct services
 */
public class JNDIURLEntryTests {

    /**
     * This test makes sure that the JNDI Entry registers appropriate services for JNDI entries and also that they get unregistered.
     *
     * @throws Exception
     */
    @Test
    public void testJNDIURLEntry() throws Exception {
        // Grab the server
        LibertyServer server = LibertyServerFactory.getStartedLibertyServer("jndi_url_entry_fat");
        try {
            /*
             * Wait for two debug level messages saying that the JNDI entries are registered.
             * Use a fairly short time out as we've already waited for the app to start
             * so this should already have appeared.
             */
            assertNotNull("No debug message in the trace.log saying the single jndi url entry defined in the server.xml was registered",
                          server.waitForStringInLog(".*Registering JNDIURLEntry", 10000, server.getMatchingLogFile("trace.log")));

            // Now make sure that the OSGi services were registered using a test servlet
            HttpUtils.findStringInUrl(server, "/ReadJndiURLEntry/ReadJndiURLEntry?jndiName=stringJndiURLEntry",
                                      "JNDI URL Entry found for stringJndiURLEntry", "Value of JNDI URL Entry is: http://w3.ibm.com");

            // Delete the entries and make sure they are unregistered
            ServerConfiguration serverConfig = server.getServerConfiguration();
            serverConfig.getJndiURLEntryElements().clear();
            server.updateServerConfiguration(serverConfig);

            // Wait for the update
            assertNotNull("The server configuration was not updated", server.waitForStringInLog("CWWKG0017I"));

            // Also wait for the debug trace messages saying that the services were unregistered
            assertNotNull("No debug message in the trace.log saying the jndi url entry deleted from the server.xml was unregistered",
                          server.waitForStringInLog(".*Unregistering JNDIURLEntry", 10000, server.getMatchingLogFile("trace.log")));

            // Now repeat the test with the servlet, it shouldn't find any entries anymore

            HttpUtils.findStringInUrl(server, "/ReadJndiURLEntry/ReadJndiURLEntry?jndiName=stringJndiURLEntry",
                                      "javax.naming.NameNotFoundException: stringJndiURLEntry");
        } finally {
            server.stopServer();
        }
    }

    /**
     * This tests that we print a useful error message when a jndiURLEntry's url contains
     * a protocol that is unknown - i.e. there is no registered protocol handler.
     */
    @Test
    @ExpectedFFDC("java.net.MalformedURLException")
    public void testUnknownProtocol() throws Exception {
        // Grab the server
        LibertyServer server = LibertyServerFactory.getStartedLibertyServer("jndi_url_entry_unknown_fat");
        try {
            /*
             * Wait for two debug level messages saying that the JNDI entries are registered.
             * Use a fairly short time out as we've already waited for the app to start
             * so this should already have appeared.
             */
            assertNotNull("No debug message in the trace.log saying the single jndi url entry defined in the server.xml was registered",
                          server.waitForStringInLog(".*Registering JNDIURLEntry", 10000, server.getMatchingLogFile("trace.log")));

            // Check that the error was logged:
            // (WI 234130) message matching must only use non-translated arguments, and in any order, because some translations reorder arguments.
            assertNotNull("Expected error message indicating failure to bind URL to JNDI was not found",
                          server.waitForStringInLog("CWWKN0010E(?=.*unknownProtocolJndiURLEntry)(?=.*java.net.MalformedURLException)", 10000));
        } finally {
            server.stopServer("CWWKN0010E");
        }
    }
}
