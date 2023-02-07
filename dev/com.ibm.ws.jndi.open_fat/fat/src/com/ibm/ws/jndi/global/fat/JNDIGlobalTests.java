/*
 * Copyright IBM Corp. 2012, 2023
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

public class JNDIGlobalTests {

    private static LibertyServer server = LibertyServerFactory.getLibertyServer("jndi_fat");

    // @ClassRule
    // public static TestRule startAndStopServerRule = startAndStopAutomatically(server);

    // @Rule
    // public TestRule runAll = runAllUsingTestNames(server).usingApp("jndi-global").andServlet("JNDITestServlet");

    @Test
    public void testServletIsReachable() {}

    @Test
    public void testCreateInitialContext() {}

    @Test
    public void testCreateSubcontext() {}

    @Test
    public void testBindUnbind() {}

    @Test
    public void testRename() {}

    @Test
    public void testListExternal() throws Exception {}

    @Test
    public void testListExternalBindings() throws Exception {}

    @Test
    public void testDeleteExternalBindings() throws Exception {}

    @Test
    public void testMultipleRebinds() throws Exception {}

    @Test
    public void testCustomInitialContextFactory() throws Exception {}

    @Test
    public void testRebindNewValue() throws Exception {}

    @Test
    public void testNamingManager() throws Exception {}
}
