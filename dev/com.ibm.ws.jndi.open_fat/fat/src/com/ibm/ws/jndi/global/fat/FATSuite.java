/*
 * Copyright IBM Corp. 2012, 2023
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.ibm.ws.jndi.global.fat;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ibm.websphere.simplicity.ShrinkHelper;

import junit.framework.AssertionFailedError;

/**
 * All of the JNDI tests
 */
@RunWith(Suite.class)
@SuiteClasses({
                JNDIGlobalTests.class,
                JNDIGlobalRefTests.class,
                JNDIEntryTests.class,
                JNDIURLEntryTests.class,
                JNDIFeatureTests.class,
                ParentLastJndiTests.class
})
public class FATSuite {
    static final JavaArchive FACTORY_JAR;
    static final WebArchive JNDI_GLOBAL_WAR;
    static final WebArchive PARENT_LAST_WAR;
    static final WebArchive READ_JNDI_ENTRY_WAR;
    static final WebArchive READ_JNDI_URL_ENTRY_WAR;

    static {
        try {
            FACTORY_JAR = ShrinkHelper.buildJavaArchive("factory.jar", "com.ibm.ws.jndi.fat.factory");
            JNDI_GLOBAL_WAR = ShrinkHelper.buildDefaultApp("jndi-global.war", "com.ibm.ws.jndi.global.fat.web");
            PARENT_LAST_WAR = ShrinkHelper.buildDefaultApp("parentLast.war", "com.ibm.ws.jndi.fat.parentlast");
            READ_JNDI_ENTRY_WAR = ShrinkHelper.buildDefaultApp("ReadJndiEntry.war", "com.ibm.ws.jndi.fat.web");
            READ_JNDI_URL_ENTRY_WAR = ShrinkHelper.buildDefaultApp("ReadJndiURLEntry.war", "com.ibm.ws.jndi.fat.web");
        } catch (Exception e) {
            throw (AssertionFailedError) new AssertionFailedError("Could not assemble test application").initCause(e);
        }
    }

}
