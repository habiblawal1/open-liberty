/*******************************************************************************
 * Copyright (c) 1997, 2006 IBM Corporation and others.
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
package com.ibm.wsspi.webcontainer.servlet;

import javax.servlet.ServletResponse;
/**
 * 
 * Simple interface to allowing retrieving of a wrapped response object
 * without all the extra methods specified in the Servlet Specification
 * @ibm-private-in-use
 */
public interface IServletResponseWrapper {
	public ServletResponse getWrappedResponse();
}
