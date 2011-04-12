package org.modelgoon.jdt.editor;

import org.eclipse.gef.requests.CreationFactory;
import org.modelgoon.jdt.model.AssociationRelationShip;

public class UniqueAssociationCreationFactory implements CreationFactory {

	String type;

	public UniqueAssociationCreationFactory(final String type) {
		super();
		this.type = type;
	}

	public Object getNewObject() {
		AssociationRelationShip associationRelationShip = new AssociationRelationShip();
		associationRelationShip.setMultiplicity(this.type);
		return associationRelationShip;
	}

	public Object getObjectType() {
		return AssociationRelationShip.class;
	}

}
