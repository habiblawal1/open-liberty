/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
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
package com.ibm.websphere.simplicity.config;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class JMSTopic extends AdminObject {

    @XmlElement(name = "properties.wasJms")
    private ConfigElementList<WasJmsProperties> wasJmsProperties;

    /**
     * @return the wasJmsProperties
     */
    public ConfigElementList<WasJmsProperties> getWasJmsProperties() {
        if (wasJmsProperties == null) {
            wasJmsProperties = new ConfigElementList<WasJmsProperties>();
        }
        return wasJmsProperties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.websphere.simplicity.config.ConfigElement#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        JMSTopic clone = (JMSTopic) super.clone();
        if (wasJmsProperties != null) {
            for (WasJmsProperties props : wasJmsProperties) {
                clone.getWasJmsProperties().add((WasJmsProperties) props.clone());
            }
        }
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(getClass().getSimpleName()).append('{');
        buf.append("id=\"" + (getId() == null ? "" : getId()) + "\" ");
        if (getJndiName() != null)
            buf.append("jndiName=\"" + getJndiName() + "\" ");

        List<?> nestedElementsList = Arrays.asList(
                                                   getProperties_FAT1(),
                                                   getWasJmsProperties()
                        );
        for (ConfigElementList<?> nestedElements : (List<ConfigElementList<?>>) nestedElementsList)
            if (nestedElements != null && nestedElements.size() > 0)
                for (Object o : nestedElements)
                    buf.append(", " + o);
        buf.append("}");
        return buf.toString();
    }

}
