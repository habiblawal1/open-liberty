/*******************************************************************************
 * Copyright (c) 2019, 2021 IBM Corporation and others.
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

package com.ibm.ws.jpa.tests.spec10.callback;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ibm.ws.jpa.tests.spec10.callback.tests.AbstractFATSuite;
import com.ibm.ws.jpa.tests.spec10.callback.tests.Callback_EJB;
import com.ibm.ws.jpa.tests.spec10.callback.tests.Callback_Web;

import componenttest.rules.repeater.RepeatTests;

@RunWith(Suite.class)
@SuiteClasses({
                Callback_EJB.class,
                Callback_Web.class
})
public class JPA20Suite extends AbstractFATSuite {

    @ClassRule
    public static RepeatTests r = RepeatTests.with(new RepeatWithJPA20());

}
