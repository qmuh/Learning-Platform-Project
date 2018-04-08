package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.AssignItem;
import shared.objects.Assignment;
import shared.objects.Course;

public class AssignmentPage extends CoursePage<AssignItem, Assignment>
		implements WondrisInfo
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField uploadField, month, day, year;
	private JButton uploadButton, browseButton;
	private File selectedFile;

	public JTextField getUploadField()
	{
		return uploadField;
	}

	public void setUploadButtonListener(ActionListener listener)
	{
		uploadButton.addActionListener(listener);
	}

	public void setBrowseButtonListener(ActionListener listener)
	{
		browseButton.addActionListener(listener);
	}

	public File getFile()
	{
		return selectedFile;

	}

	public void setFile(File toSet)
	{
		selectedFile = toSet;
	}


	public String getDate()
	{

		return year.getText() + " " + month.getText() + " " +day.getText();

	}

	public AssignmentPage(Course course)
	{
		super(course);
		this.setName(ASSIGNMENT_PAGE + course.getId());
		this.setPageTitle("Assignments");
		bodyCenter.add(createAssignmentPanel(), BorderLayout.CENTER);
	}

	private JPanel createAssignmentPanel()
	{
		JPanel assignmentPanel = new JPanel(new GridLayout(1, 2));
		assignmentPanel.add(createUploadPanel(), 0);
		assignmentPanel.add(createTheAssignments(), 1);

		return assignmentPanel;
	}

	private JPanel createTheAssignments()
	{
		JPanel assignment = new JPanel(new BorderLayout());
		assignment.add(createLabel("Assignments", TEXT_FONT),
				BorderLayout.NORTH);
		assignment.add(createAssignmentList(), BorderLayout.CENTER);
		return assignment;
	}

	private JScrollPane createAssignmentList()
	{
		itemDisplay = new BoxList<AssignItem>();
		JScrollPane scrollPane = new JScrollPane(itemDisplay);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		return scrollPane;
	}

	private JPanel createUploadPanel()
	{
		JPanel uploadPanel = new JPanel(new GridLayout(2, 1));
		uploadPanel.add(createUpload(), 0);
		uploadPanel.add(createDatePanel(), 1);
		return uploadPanel;
	}

	private JPanel createDatePanel()
	{
		JPanel datePanel = new JPanel(new GridLayout(3, 1));
		datePanel.setBorder(BorderFactory.createEtchedBorder());
		month = new JTextField(2);
		day = new JTextField(2);
		year = new JTextField(4);
		uploadButton = new JButton();
		JPanel uploadContainer = new JPanel();
		uploadContainer.add(createButton(uploadButton, "Upload", TEXT_FONT));
		datePanel.add(createLabel("Due Date", TEXT_FONT), 0);
		datePanel.add(createTheDate(), 1);
		datePanel.add(uploadContainer, 2);
		return datePanel;
	}

	private JPanel createTheDate()
	{
		JPanel date = new JPanel();
		date.add(month);
		date.add(new JLabel("-"));
		date.add(day);
		date.add(new JLabel("-"));
		date.add(year);
		return date;
	}

	private JPanel createUpload()
	{
		JPanel upload = new JPanel(new GridLayout(2, 1));
		upload.setBorder(BorderFactory.createEtchedBorder());
		JPanel browse = new JPanel();
		uploadField = new JTextField(20);
		browseButton = new JButton();
		browse.add(uploadField);
		browse.add(createButton(browseButton, "Browse", TEXT_FONT));
		upload.add(createLabel("Upload", TEXT_FONT), 0);
		upload.add(browse, 1);
		return upload;
	}

	@Override
	public void displayPage()
	{
		itemDisplay.revalidate();
		itemDisplay.repaint();
	}


	public void setAssignmentVector(Vector<Assignment> myList)
	{
		itemDisplay.removeAll();
		for (Assignment assignment : myList)
		{
			this.addToBoxList(new AssignItem(assignment));
		}
		System.out.println("#: " + itemDisplay.getComponentCount());
		itemDisplay.revalidate();
		itemDisplay.repaint();
	}

}
