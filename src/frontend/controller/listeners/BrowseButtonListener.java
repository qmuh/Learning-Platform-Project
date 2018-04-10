package frontend.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import frontend.view.pages.AssignmentPage;

/**
 * Listener for the browse button on the assignment page
 *
 */
public class BrowseButtonListener implements ActionListener
{
	private AssignmentPage assignPage;

	public BrowseButtonListener(AssignmentPage assignPage)
	{
		this.assignPage = assignPage;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		File selectedFile;
		JFileChooser fileBrowser = new JFileChooser();
		if (fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			selectedFile = fileBrowser.getSelectedFile();
			assignPage.setFile(selectedFile);
			assignPage.getUploadField().setText(selectedFile.getPath());

		} else
		{
			assignPage.setFile(null);
		}

	}

}