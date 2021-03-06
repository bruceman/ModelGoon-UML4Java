package org.modelgoon.jdt.editor;

import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelgoon.ModelGoonActivator;
import org.modelgoon.ModelGoonIcons;
import org.modelgoon.classes.editparts.ClassCompartmentModel;
import org.modelgoon.classes.editparts.ListEditPart;
import org.modelgoon.core.Note;
import org.modelgoon.core.editparts.NoteEditPart;
import org.modelgoon.jdt.editparts.AssociationEditPart;
import org.modelgoon.jdt.editparts.ExtensionEditPart;
import org.modelgoon.jdt.editparts.FieldEditPart;
import org.modelgoon.jdt.editparts.JDTClassDiagramEditPart;
import org.modelgoon.jdt.editparts.MethodEditPart;
import org.modelgoon.jdt.editparts.UMLClassEditPart;
import org.modelgoon.jdt.model.AssociationRelationShip;
import org.modelgoon.jdt.model.ExtensionRelationShip;
import org.modelgoon.jdt.model.Field;
import org.modelgoon.jdt.model.Method;
import org.modelgoon.jdt.model.UMLClass;
import org.modelgoon.jdt.model.UMLModel;

public class JDTClassDiagramEditor extends JDTDiagramEditor {

	public JDTClassDiagramEditor() {
		super(new UMLModel());
		this.modelLoader.addMapping("org/modelgoon/jdt/xml/UMLModel.cas");
		this.modelLoader.addMapping("org/modelgoon/jdt/xml/ClassDiagram.cas");
		setModelElementFactory(new ClassModelElementFactory(this));
		registerEditPart(UMLModel.class, JDTClassDiagramEditPart.class);
		registerEditPart(UMLClass.class, UMLClassEditPart.class);
		registerEditPart(ClassCompartmentModel.class, ListEditPart.class);
		registerEditPart(Field.class, FieldEditPart.class);
		registerEditPart(Method.class, MethodEditPart.class);
		registerEditPart(ExtensionRelationShip.class, ExtensionEditPart.class);
		registerEditPart(AssociationRelationShip.class,
				AssociationEditPart.class);

		registerEditPart(Note.class, NoteEditPart.class);
	}

	@Override
	protected void fillPalette(final PaletteRoot paletteRoot,
			final PaletteGroup group) {

		ImageDescriptor inheritanceImage = ModelGoonActivator
				.getImageDescriptor(ModelGoonIcons.GENERALIZATION_ICON);
		ConnectionCreationToolEntry connectionToolEntry = new ConnectionCreationToolEntry(
				"Inheritance", "Creates inheritance", new SimpleFactory(
						ExtensionRelationShip.class), inheritanceImage,
				inheritanceImage);

		ImageDescriptor associationImage = ModelGoonActivator
				.getImageDescriptor(ModelGoonIcons.ASSOCIATION_ICON);
		ConnectionCreationToolEntry uniqueAssociation = new ConnectionCreationToolEntry(
				"Association", "Creates an Association",
				new AssociationCreationFactory(AssociationRelationShip.UNIQUE),
				associationImage, associationImage);

		// ImageDescriptor associationImage = ModelGoonActivator
		// .getImageDescriptor(ModelGoonIcons.REALIZATION_ICON);
		// ConnectionCreationToolEntry multipleAssociation = new
		// ConnectionCreationToolEntry(
		// "Multiple Association",
		// "Creates Multiple Association",
		// new AssociationCreationFactory(AssociationRelationShip.MULTIPLE),
		// SharedImages.DESC_SELECTION_TOOL_16,
		// SharedImages.DESC_SELECTION_TOOL_16);

		ImageDescriptor classImage = ModelGoonActivator
				.getImageDescriptor(ModelGoonIcons.CLASS_ICON);
		CreationToolEntry classCreationTool = new CreationToolEntry("Class",
				"Creates a new Class", new UMLClassCreationFactoryTool(false),
				classImage, classImage);

		ImageDescriptor interfaceImage = ModelGoonActivator
				.getImageDescriptor(ModelGoonIcons.INTERFACE_ICON);
		CreationToolEntry interfaceCreationTool = new CreationToolEntry(
				"Interface", "Creates a new Interface",
				new UMLClassCreationFactoryTool(true), interfaceImage,
				interfaceImage);

		group.add(interfaceCreationTool);
		group.add(classCreationTool);
		group.add(connectionToolEntry);
		group.add(uniqueAssociation);
		// group.add(multipleAssociation);
	}

}
