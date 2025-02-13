/*******************************************************************************
 * Copyright (c) 1997, 2005 IBM Corporation and others.
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
package com.ibm.ws.cache.config;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class NotValue {
   public String   notValue;
   public ArrayList ranges;
   
   public String toString() {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      pw.println("not-value:  "+notValue);
      if (ranges != null){
      	Iterator it = ranges.iterator();
      	while (it.hasNext())
      		pw.println(it.next());
      }
      return sw.toString();
   }

   public String fancyFormat(int level) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      for (int i = level;i>0;i--) pw.print("\t");
      pw.println("not-value:  "+notValue);
      if (ranges != null){
      	Iterator it = ranges.iterator();
      	while (it.hasNext())
      		pw.println(it.next());
      }
      return sw.toString();
   }



   public Object clone() {
      NotValue c =  new NotValue();
      c.notValue = notValue;
      if (ranges != null){
      	c.ranges = new ArrayList();
      	for (Iterator i = ranges.iterator(); i.hasNext();) {
      		c.ranges.add(((Range)i.next()).clone());
      	}
      }
      return c;
   }
}
