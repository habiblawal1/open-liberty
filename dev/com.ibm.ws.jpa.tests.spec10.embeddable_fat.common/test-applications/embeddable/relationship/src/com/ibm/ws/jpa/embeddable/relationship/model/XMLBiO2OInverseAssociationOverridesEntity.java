/*******************************************************************************
 * Copyright (c) 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.jpa.embeddable.relationship.model;

public class XMLBiO2OInverseAssociationOverridesEntity {

    protected int id;

    private XMLJPAEmbeddableRelationshipEntity owner;

    public XMLBiO2OInverseAssociationOverridesEntity() {
    }

    public XMLBiO2OInverseAssociationOverridesEntity(int id,
                                                     XMLJPAEmbeddableRelationshipEntity owner) {
        this.id = id;
        this.owner = owner;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public XMLJPAEmbeddableRelationshipEntity getOwner() {
        return this.owner;
    }

    public void setOwner(XMLJPAEmbeddableRelationshipEntity owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        return ((37 * 17 + id) * 37 + ((owner != null) ? owner.getId() : 0));
    }

    @Override
    public boolean equals(Object otherObject) {

        if (otherObject == this)
            return true;
        if (!(otherObject instanceof XMLBiO2OInverseAssociationOverridesEntity))
            return false;
        return (otherObject.hashCode() == hashCode());
    }

    @Override
    public String toString() {
        if (owner != null)
            return "(id=" + id + " is inversed by " + owner.getId() + ")";
        return "(id=" + id + " is not owned)";
    }

}
