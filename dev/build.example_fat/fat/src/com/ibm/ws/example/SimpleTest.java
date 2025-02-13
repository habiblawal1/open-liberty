/*******************************************************************************
 * Copyright (c) 2017, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.example;

import static componenttest.annotation.SkipForRepeat.EE10_FEATURES;
import static componenttest.annotation.SkipForRepeat.EE8_FEATURES;
import static componenttest.annotation.SkipForRepeat.EE9_FEATURES;
import static componenttest.annotation.SkipForRepeat.NO_MODIFICATION;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ibm.websphere.simplicity.ShrinkHelper;

import app1.web.TestServletA;
import componenttest.annotation.Server;
import componenttest.annotation.SkipForRepeat;
import componenttest.annotation.TestServlet;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.utils.FATServletClient;

/**
 * Example Shrinkwrap FAT project:
 * <li> Application packaging is done in the @BeforeClass, instead of ant scripting.
 * <li> Injects servers via @Server annotation. Annotation value corresponds to the
 * server directory name in 'publish/servers/%annotation_value%' where ports get
 * assigned to the LibertyServer instance when the 'testports.properties' does not
 * get used.
 * <li> Specifies an @RunWith(FATRunner.class) annotation. Traditionally this has been
 * added to bytecode automatically by ant.
 * <li> Uses the @TestServlet annotation to define test servlets. Notice that not all @Test
 * methods are defined in this class. All of the @Test methods are defined on the test
 * servlet referenced by the annotation, and will be run whenever this test class runs.
 */
@RunWith(FATRunner.class)
public class SimpleTest extends FATServletClient {

    public static final String APP_NAME = "app1";

    @Server("FATServer")
    @TestServlet(servlet = TestServletA.class, contextRoot = APP_NAME)
    public static LibertyServer server;

    @BeforeClass
    public static void setUp() throws Exception {
        // Create a WebArchive that will have the file name 'app1.war' once it's written to a file
        // Include the 'app1.web' package and all of it's java classes and sub-packages
        // Automatically includes resources under 'test-applications/APP_NAME/resources/' folder
        // Exports the resulting application to the ${server.config.dir}/apps/ directory
        ShrinkHelper.defaultApp(server, APP_NAME, "app1.web");

        server.startServer();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.stopServer();
    }

    @Test
    public void verifyArtifactoryDependency() throws Exception {
        // Confirm that the example Artifactory dependency was download and is available on the classpath
        org.apache.commons.logging.Log.class.getName();
    }

    @Test
    @SkipForRepeat({ EE8_FEATURES, EE9_FEATURES, EE10_FEATURES })
    public void testEE7Only() throws Exception {
        // This test will only run for the EE7 feature iteration (i.e. NO_MODIFICATION)

        // Verify only EE7 features are enabled
        Set<String> features = server.getServerConfiguration().getFeatureManager().getFeatures();
        assertTrue("Expected the Java EE 7 feature 'servlet-3.1' to be enabled but was not: " + features,
                   features.contains("servlet-3.1"));
        assertTrue("No EE8 features should be enabled when this test runs: " + features,
                   !features.contains("servlet-4.0"));
    }

    @Test
    @SkipForRepeat({ NO_MODIFICATION, EE9_FEATURES, EE10_FEATURES })
    public void testEE8Only() throws Exception {
        // This test will only run for the EE 8 iteration

        // Verify only EE8 features are enabled
        Set<String> features = server.getServerConfiguration().getFeatureManager().getFeatures();
        assertTrue("Expected the Java EE 8 feature 'servlet-4.0' to be enabled but was not: " + features,
                   features.contains("servlet-4.0"));
        assertTrue("No EE7 features should be enabled when this test runs: " + features,
                   !features.contains("servlet-3.1"));
    }

    @Test
    @SkipForRepeat({ NO_MODIFICATION, EE8_FEATURES, EE10_FEATURES })
    public void testEE9Only() throws Exception {
        // This test will only run for the EE9 iteration

        // Verify only EE9 features are enabled
        Set<String> features = server.getServerConfiguration().getFeatureManager().getFeatures();
        assertTrue("Expected the Java EE 9 feature 'servlet-5.0' to be enabled but was not: " + features,
                   features.contains("servlet-5.0"));
        assertTrue("No EE7 features should be enabled when this test runs: " + features,
                   !features.contains("servlet-3.1"));
    }

    @Test
    @SkipForRepeat({ NO_MODIFICATION, EE8_FEATURES, EE9_FEATURES })
    public void testEE10Only() throws Exception {
        // This test will only run for the EE10 iteration

        // Verify only EE10 features are enabled
        Set<String> features = server.getServerConfiguration().getFeatureManager().getFeatures();
        assertTrue("Expected the Java EE 10 feature 'servlet-6.0' to be enabled but was not: " + features,
                   features.contains("servlet-6.0"));
        assertTrue("No EE7 features should be enabled when this test runs: " + features,
                   !features.contains("servlet-3.1"));
    }
}
