/*
 * WLP Copyright IBM Corp. 2023
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.ibm.ws.jndi.global.fat;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import componenttest.topology.impl.LibertyServer;
import componenttest.topology.impl.LibertyServerFactory;

/**
 * Regression tests for APAR PI24783 where parentLast classloading delegation caused the application classloader to have no hierarchy.
 */
public class ParentLastJndiTests {

    private static final LibertyServer server = LibertyServerFactory.getLibertyServer("parentlast_fat");

    // @ClassRule
    // public static TestRule startAndStopServerRule = startAndStopAutomatically(server);

    // @Rule
    // public TestRule runAll = runAllUsingTestNames(server).usingApp("parentLast").andServlet("ParentLastJndiServlet");

    @Test
    public void testSync() {}

    @Test
    public void testAsync() {}

}
