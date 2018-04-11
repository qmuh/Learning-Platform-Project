package frontend.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import frontend.view.pages.AssignmentPage;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class BrowseButtonListener implements ActionListener
{
	/**
	 * The page where the button is located
	 */
	private AssignmentPage assignPage;

	/** The listener for this, is it used to browse files in a computer
	 * @param assignPage The page where the button is located
	 */
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