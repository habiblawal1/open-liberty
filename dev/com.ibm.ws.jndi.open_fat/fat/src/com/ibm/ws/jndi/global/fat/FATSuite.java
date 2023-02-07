/*
 * Copyright IBM Corp. 2012, 2023
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.ibm.ws.jndi.global.fat;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All of the JNDI tests
 */
@RunWith(Suite.class)
@SuiteClasses({
            //    JNDIGlobalTests.class,
            //    JNDIGlobalRefTests.class,
            //    JNDIEntryTests.class,
            //    JNDIURLEntryTests.class,
               JNDIFeatureTests.class,
            //    ParentLastJndiTests.class
})
public class FATSuite {}
