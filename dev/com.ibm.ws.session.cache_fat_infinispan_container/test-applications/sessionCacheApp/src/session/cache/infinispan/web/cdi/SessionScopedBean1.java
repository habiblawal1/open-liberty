/*******************************************************************************
 * Copyright (c) 2018, 2019 IBM Corporation and others.
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
package session.cache.infinispan.web.cdi;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionScopedBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String stringValue;

    public SessionScopedBean1() {
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String str) {
        System.out.println("SessionScopedBean.setStringValue: [" + stringValue + "] -> [" + str + "]");
        stringValue = str;
    }
}