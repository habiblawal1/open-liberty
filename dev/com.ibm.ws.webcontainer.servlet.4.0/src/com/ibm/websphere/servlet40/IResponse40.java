/*******************************************************************************
 * Copyright (c) 2011, 2016 IBM Corporation and others.
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
package com.ibm.websphere.servlet40;

import java.util.Map;

import com.ibm.websphere.servlet31.response.IResponse31;

/**
 *
 */
public interface IResponse40 extends IResponse31 {

    public void setTrailers(Map<String, String> trailers);

}
