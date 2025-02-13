/*******************************************************************************
 * Copyright (c) 1997, 2002 IBM Corporation and others.
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

package com.ibm.websphere.pmi.server;

public interface SpdLoad extends SpdData {

    // Add a value
    public void add(double val);

    // increment the lastValue by incVal (default is 1)
    public void increment(double incVal);

    public void increment();

    // decrement the lastValue by incVal (default is 1)
    public void decrement(double incVal);

    public void decrement();

    // set lower and upper bound for the config data
    public void setConfig(long minSize, long maxSize);

    // combine the value of this data and other data
    public void combine(SpdLoad other);

    // clean up lastValue and integral but leave createTime unchanged
    public void cleanup();
}
