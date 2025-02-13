/*******************************************************************************
 * Copyright (c) 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.jpa.embeddable.nested.model;

public interface IEntity03 {

    //--------------------------------------------
    // Entity03 fields
    //--------------------------------------------
    public int getId();

    public void setId(int id);

    public String getEnt03_str01();

    public void setEnt03_str01(String str);

    public String getEnt03_str02();

    public void setEnt03_str02(String str);

    public String getEnt03_str03();

    public void setEnt03_str03(String str);

    //--------------------------------------------
    // Embeddable03a fields
    //--------------------------------------------
    public int getEmb03a_int01();

    public void setEmb03a_int01(int ii);

    public int getEmb03a_int02();

    public void setEmb03a_int02(int ii);

    public int getEmb03a_int03();

    public void setEmb03a_int03(int ii);

    //--------------------------------------------
    // Embeddable03b fields
    //--------------------------------------------
    public int getEmb03b_int04();

    public void setEmb03b_int04(int ii);

    public int getEmb03b_int05();

    public void setEmb03b_int05(int ii);

    public int getEmb03b_int06();

    public void setEmb03b_int06(int ii);
}
