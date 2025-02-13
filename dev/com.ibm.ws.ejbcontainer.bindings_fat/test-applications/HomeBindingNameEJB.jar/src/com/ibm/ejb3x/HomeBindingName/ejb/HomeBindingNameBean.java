/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
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

package com.ibm.ejb3x.HomeBindingName.ejb;

import java.util.logging.Logger;

import com.ibm.ejb3x.ComponentIDBnd.ejb.ComponentIDBndBean;

@SuppressWarnings("serial")
public class HomeBindingNameBean implements javax.ejb.SessionBean {
    private final static String CLASS_NAME = HomeBindingNameBean.class.getName();
    private final static Logger svLogger = Logger.getLogger(CLASS_NAME);
    private static final String BeanName = "LocalHomeBindingName";

    public HomeBindingNameBean() {}

    /**
     * setSessionContext
     */
    @Override
    public void setSessionContext(javax.ejb.SessionContext ctx) {
        printMsg(BeanName, "(setSessionContext)");
    }

    /**
     * ejbCreate
     */
    public void ejbCreate() throws javax.ejb.CreateException {
        printMsg(BeanName, "(ejbCreate)");
    }

    /**
     * ejbActivate
     */
    @Override
    public void ejbActivate() {
        printMsg(BeanName, "(ejbActivate)");
        throw new IllegalStateException("Should never be called");
    }

    /**
     * ejbPassivate
     */
    @Override
    public void ejbPassivate() {
        printMsg(BeanName, "(ejbPassivate)");
    }

    /**
     * ejbRemove
     */
    @Override
    public void ejbRemove() {
        printMsg(BeanName, "(ejbRemove)");
    }

    public void printMsg(String beanName, String msg) {
        svLogger.info("       " + beanName + " : " + msg);
    }

    public String foo() {
        return "LocalHomeBindingNameBean.toString()";
    }
}
