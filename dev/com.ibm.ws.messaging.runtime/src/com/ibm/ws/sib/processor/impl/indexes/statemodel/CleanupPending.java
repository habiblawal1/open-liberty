/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
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
package com.ibm.ws.sib.processor.impl.indexes.statemodel;

import com.ibm.websphere.ras.TraceComponent;
import com.ibm.ws.sib.processor.SIMPConstants;
import com.ibm.ws.sib.utils.ras.SibTr;

/**
 * Class to represent the various lookups for different destination types
 */ 
public class CleanupPending extends Visible
{
  /** 
   * Trace for the component
   */
  private static final TraceComponent tc =
    SibTr.register(
      CleanupPending.class,
      SIMPConstants.MP_TRACE_GROUP,
      SIMPConstants.RESOURCE_BUNDLE);
 
  
  public boolean isCleanupPending()
  {
    return true;
  }

  public State cleanupComplete()
  {
    return State.ACTIVE; 
  }

  public State create()
  {
    return this; 
  }
  
  public State defer()
  {
    return State.CLEANUP_DEFERED; 
  }

  public State delete()
  {
    return State.DELETE_PENDING; 
  }
  
  public State cleanup()
  {
    return this; 
  }

  public String toString()
  {
    return "CLEANUP_PENDING";
  }
}
