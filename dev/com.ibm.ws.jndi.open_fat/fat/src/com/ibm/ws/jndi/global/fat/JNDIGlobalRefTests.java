/*
 * Copyright IBM Corp. 2023
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.ibm.ws.jndi.global.fat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.ibm.websphere.simplicity.ShrinkHelper;
import com.ibm.ws.jndi.global.fat.web.JNDIRefTestServlet;

import componenttest.annotation.Server;
import componenttest.annotation.TestServlet;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.utils.FATServletClient;

@RunWith(FATRunner.class)
public class JNDIGlobalRefTests extends FATServletClient {

    @Server("jndi_global_ref_fat")
    @TestServlet(servlet = JNDIRefTestServlet.class, contextRoot = "jndi-global")
    public static LibertyServer server;

    @BeforeClass
    public static void beforeClass() throws Exception {
        ShrinkHelper.exportDropinAppToServer(server, FATSuite.JNDI_GLOBAL_WAR);
        ShrinkHelper.exportToServer(server, "lib", FATSuite.FACTORY_JAR);
        server.startServer();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.stopServer("CWWKS1859E", "CWWKN0011E");
    }

}