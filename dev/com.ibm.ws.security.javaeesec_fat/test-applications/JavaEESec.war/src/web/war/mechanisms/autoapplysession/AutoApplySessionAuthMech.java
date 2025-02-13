/*******************************************************************************
 * Copyright (c) 2017, 2018 IBM Corporation and others.
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
package web.war.mechanisms.autoapplysession;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.AutoApplySession;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;

import web.war.mechanisms.BaseAuthMech;

@ApplicationScoped
@AutoApplySession
public class AutoApplySessionAuthMech extends BaseAuthMech {

    public AutoApplySessionAuthMech() {
        sourceClass = AutoApplySessionAuthMech.class.getName();
    }

}
