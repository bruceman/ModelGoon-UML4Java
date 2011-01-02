package org.modelgoon.packages.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

public class NewPackageDiagramWizard extends BasicNewResourceWizard {

	private WizardNewFileCreationPage mainPage;

	/**
	 * Creates a wizard for creating a new file resource in the workspace.
	 */
	public NewPackageDiagramWizard() {
		super();
	}

	/*
	 * (non-Javadoc) Method declared on IWizard.
	 */
	@Override
	public void addPages() {
		super.addPages();
		this.mainPage = new WizardNewFileCreationPage("Default", getSelection());//$NON-NLS-1$
		this.mainPage.setTitle("New Package Dependencies Diagram");
		this.mainPage
				.setDescription("Creates a new Package Dependencies Diagram");
		this.mainPage.setFileExtension("mgp");
		addPage(this.mainPage);
	}

	/*
	 * (non-Javadoc) Method declared on IWorkbenchWizard.
	 */
	@Override
	public void init(final IWorkbench workbench,
			final IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		setWindowTitle("ModelGoon - New Package Dependency Diagram");
		setNeedsProgressMonitor(true);
	}

	/*
	 * (non-Javadoc) Method declared on IWizard.
	 */
	@Override
	public boolean performFinish() {
		IFile file = this.mainPage.createNewFile();
		if (file == null) {
			return false;
		}

		selectAndReveal(file);

		// Open editor on new file.
		IWorkbenchWindow dw = getWorkbench().getActiveWorkbenchWindow();
		try {
			if (dw != null) {
				IWorkbenchPage page = dw.getActivePage();
				if (page != null) {
					IDE.openEditor(page, file, true);
				}
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}

		return true;
	}
}
