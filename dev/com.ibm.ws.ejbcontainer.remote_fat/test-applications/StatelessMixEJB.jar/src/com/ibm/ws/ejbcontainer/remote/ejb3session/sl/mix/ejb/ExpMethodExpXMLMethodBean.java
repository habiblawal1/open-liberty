/*******************************************************************************
 * Copyright (c) 2006, 2018 IBM Corporation and others.
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
package com.ibm.ws.ejbcontainer.remote.ejb3session.sl.mix.ejb;

import static javax.ejb.TransactionAttributeType.NEVER;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;

import com.ibm.websphere.ejbcontainer.test.tools.FATTransactionHelper;

/**
 * Bean implementation class for Enterprise Bean: ExpMethodExpXMLMethodBean
 **/
@Local(ExpMethodExpXMLMethodLocal.class)
@Remote(ExpMethodExpXMLMethodRemote.class)
public class ExpMethodExpXMLMethodBean {
    /**
     * 1)Not taking the XML into account this method will explicitly have Tx
     * Attr of NEVER. 2)XML sets this method to have the trans-attribute of
     * RequiresNew 3)The XML should take precedence
     *
     * To verify this, the caller must begin a global transaction prior to
     * calling this method.
     *
     * @param tid
     *            is the global transaction ID for the transaction that was
     *            started prior to calling this method.
     *
     * @return boolean true if a new global tran is created (tid's do not match)
     *         meaning the XML override worked. boolean false if the same tran
     *         is used (tid's match) meaning the XML override failed - this also
     *         means method level demarcation of NEVER failed as well.
     *
     * @throws java.lang.IllegalStateException
     *             is thrown if method is dispatched while not in any
     *             transaction context.
     */
    @TransactionAttribute(NEVER)
    public boolean expMethodExpXMLMethod(byte[] tid) {
        byte[] myTid = FATTransactionHelper.getTransactionId();
        if (myTid == null) {
            return false;
        }

        return (FATTransactionHelper.isSameTransactionId(tid) == false);
    }

    /**
     * 1)Not taking the XML into account the bean and its methods will
     * implicitly have Tx Attr of REQUIRED. 2)There is no XML used to override
     * this method 3)The implicit Tx Attr of REQUIRED should take precedence
     *
     * To verify this, the caller must begin a global transaction prior to
     * calling this method.
     *
     * @param tid
     *            is the global transaction ID for the transaction that was
     *            started prior to calling this method.
     *
     * @return boolean true if method is dispatched in the same transaction
     *         context with the same transaction ID as passed by tid parameter.
     *         Otherwise boolean false is returned.
     *
     * @throws java.lang.IllegalStateException
     *             is thrown if method is dispatched while not in any
     *             transaction context.
     */
    public boolean impClassNoXMLOverride(byte[] tid) {
        return FATTransactionHelper.isSameTransactionId(tid);
    }
}