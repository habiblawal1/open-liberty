/*
 * Copyright (c) 2015, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 */
package com.ibm.ws.jsf22.fat.resources.beans.faces40;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * This bean tests the userAgentNeedsUpdate method of the Resource class
 */
@Named
@RequestScoped
public class ResourceUserAgentBean {

    private final FacesContext context = FacesContext.getCurrentInstance();

    public ResourceUserAgentBean() {
    }

    /**
     * Get the request headers
     *
     * @return a String containing the request headers
     */
    public String getRequestHeaders() {
        return context.getExternalContext().getRequestHeaderMap().toString();
    }

    /**
     * Tests the userAgentNeedsUpdate method by checking what this method returns.
     *
     * @return true or false depending on whether or not the user-agent needs an update of this resource.
     */
    public boolean getUserAgentNeedsUpdateResult() {
        ResourceHandler rh = context.getApplication().getResourceHandler();
        Resource r = rh.createResource("basicTemplate.xhtml", "templates");
        return r.userAgentNeedsUpdate(context);
    }

    /**
     * Tests the libraryExists API for the ResourceHandler.
     *
     * @return true or false depending on whether or not the library exists.
     */
    public boolean doLibraryExist() {
        ResourceHandler resourceHandler = context.getApplication().getResourceHandler();
        Resource r = resourceHandler.createResource("basicTemplate.xhtml", "templates");
        boolean libraryFound = resourceHandler.libraryExists("templates");

        return libraryFound;
    }
}
