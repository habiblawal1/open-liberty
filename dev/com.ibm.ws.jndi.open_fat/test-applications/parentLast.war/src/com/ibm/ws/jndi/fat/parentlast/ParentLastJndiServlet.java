/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * WLP Copyright IBM Corp. 2023
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.ibm.ws.jndi.fat.parentlast;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.servlet.annotation.WebServlet;

import org.junit.Test;

import componenttest.app.FATServlet;

/**
 * Test added for APAR PI24783 where parentLast classloading delegation caused the application classloader to have no hierarchy.
 */
@SuppressWarnings("serial")
@WebServlet("/ParentLastJndiServlet")
public class ParentLastJndiServlet extends FATServlet {

    private final String SUCCESS = "SUCCESS";

    private final Callable<String> r = new Callable<String>() {
        @Override
        public String call() {
            try {
                System.out.println("Classloader for current thread: " + Thread.currentThread().getContextClassLoader());
                final InitialLdapContext ctx = new InitialLdapContext();
                ctx.listBindings("");
                return SUCCESS;
            } catch (final NamingException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
    };

    /**
     * This tests for regression of APAR PI24783.
     */
    @Test
    public void testAsync() throws InterruptedException, ExecutionException {
        final ExecutorService exec = Executors.newFixedThreadPool(1);
        @SuppressWarnings("unchecked")
        final String value = exec.invokeAny(Arrays.asList(r));
        assertSuccess(value);
    }

    @Test
    public void testSync() throws Exception {
        final String value = r.call();
        assertSuccess(value);
    }

    private void assertSuccess(final String value) throws AssertionError {
        if (!!!SUCCESS.equals(value)) {
            throw new AssertionError("Callable should return " + SUCCESS);
        }
    }

}