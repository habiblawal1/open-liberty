/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package oidc.simple.client.withAndWithoutEL.servlets;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import oidc.client.base.servlets.BaseOpenIdConfig;

@Named
@Dependent
public class OpenIdConfig extends BaseOpenIdConfig {

//    @Override
//    public String getClientSecret() {
//        if (config.containsKey(Constants.CLIENT_SECRET)) {
//            return config.getProperty(Constants.CLIENT_SECRET);
//        }
//
//        return "mySharedKeyNowHasToBeLongerStrongerAndMoreSecureAndForHS512EvenLongerToBeStronger";
//    }

}
