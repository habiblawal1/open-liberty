/*******************************************************************************
 * Copyright (c) 2020, 2021 IBM Corporation and others.
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

package com.ibm.ws.jpa.fvt.inheritance.entities.singletable.ano;

import javax.persistence.MappedSuperclass;

import com.ibm.ws.jpa.fvt.inheritance.entities.ITreeMSC;

@MappedSuperclass
public class AnoSTCDTreeMSC extends AnoSTCDTreeRootEntity implements ITreeMSC {
    public AnoSTCDTreeMSC() {
        super();
    }

    private String stringVal1;

    @Override
    public String getStringVal1() {
        return stringVal1;
    }

    @Override
    public void setStringVal1(String stringVal1) {
        this.stringVal1 = stringVal1;
    }

    @Override
    public String toString() {
        return "AnoSTCDTreeMSC [stringVal1=" + stringVal1 + ", getId()=" + getId() + ", getName()=" + getName()
               + "]";
    }
}
