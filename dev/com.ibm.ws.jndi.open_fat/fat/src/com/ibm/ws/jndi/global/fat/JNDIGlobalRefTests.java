/*
 * Copyright IBM Corp. 2023
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.ibm.ws.jndi.global.fat;

import componenttest.annotation.ExpectedFFDC;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import componenttest.topology.impl.LibertyServer;
import componenttest.topology.impl.LibertyServerFactory;

public class JNDIGlobalRefTests {

    private static LibertyServer server = LibertyServerFactory.getLibertyServer("jndi_global_ref_fat");

    // @ClassRule
    // public static TestRule startAndStopServerRule = startAndStopAutomatically(server);

    // @Rule
    // public TestRule runAll = runAllUsingTestNames(server).usingApp("jndi-global").andServlet("JNDIRefTestServlet");

    @Test
    public void testRefEntry() throws Exception {}

    @Test
    public void testRefEntryInjection() throws Exception {}

    @Test
    public void testRefEntryProps() throws Exception {}

    @Test
    public void testRefEntryPropsDecode() throws Exception {}

    @Test
    @ExpectedFFDC("com.ibm.websphere.crypto.InvalidPasswordDecodingException")
    public void testRefEntryPropsDecodeError() throws Exception {}

    @Test
    public void testRefEntryPropsInjection() throws Exception {}

    @Test
    public void testRefEntryClassName() throws Exception {}
}
