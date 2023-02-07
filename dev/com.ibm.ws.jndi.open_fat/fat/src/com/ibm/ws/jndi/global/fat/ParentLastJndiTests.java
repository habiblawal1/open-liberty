/*
 * WLP Copyright IBM Corp. 2023
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
import com.ibm.ws.jndi.fat.parentlast.ParentLastJndiServlet;

import componenttest.annotation.Server;
import componenttest.annotation.TestServlet;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.utils.FATServletClient;

/**
 * Regression tests for APAR PI24783 where parentLast classloading delegation caused the application classloader to have no hierarchy.
 */
@RunWith(FATRunner.class)
public class ParentLastJndiTests extends FATServletClient {

    @Server("parentlast_fat")
    @TestServlet(servlet = ParentLastJndiServlet.class, contextRoot = "parentLast")
    public static LibertyServer server;

    @BeforeClass
    public static void beforeClass() throws Exception {
        ShrinkHelper.exportDropinAppToServer(server, FATSuite.PARENT_LAST_WAR);
        server.startServer();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.stopServer();
    }

}
