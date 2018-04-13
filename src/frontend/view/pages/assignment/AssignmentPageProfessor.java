package frontend.view.pages.assignment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import frontend.view.pages.components.customSwing.WButton;
import shared.objects.Course;
import shared.objects.Professor;

/** The assignment page for the professor
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 11, 2018
 */
/**
 * @author qasimmuhammad
 *
 */
public class AssignmentPageProfessor extends AssignmentPage
{
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The upload and browse buttons
	 */
	private WButton uploadButton, browseButton;

	/**
	 * The text field for the upload, and month/day/year
	 */
	private JTextField uploadField, month, day, year;

	/**
	 * The selected file that is most likely going to be submitted
	 */
	private File selectedFile;

	/** Constructs the page
	 * @param course The specific course the page is or
	 * @param professor The associated professor for the course
	 */
	public AssignmentPageProfessor(Course course, Professor professor)
	{
		super(course, professor);
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
		return year.getText() + "/" + month.getText() + "/" + day.getText();
	}

	public JTextField getUploadField()
	{
		return uploadField;
	}

	/** Sets up the upload button
	 * @param listener The listener for the upload button
	 */
	public void setUploadButtonListener(ActionListener listener)
	{
		uploadButton.addActionListener(listener);
	}

	/** Sets the listener for the browse button
	 * @param listener The listener for the browse button
	 */
	public void setBrowseButtonListener(ActionListener listener)
	{
		browseButton.addActionListener(listener);
	}

	/** Creates the upload portion of the panel
	 * @return The created J Panel
	 */
	private JPanel createUpload()
	{
		JPanel upload = new JPanel();
		upload.setBorder(new EmptyBorder(0, 20, 0, 0));
		upload.setLayout(new BorderLayout());
		JPanel browse = new JPanel();
		browse.setLayout(new BoxLayout(browse, BoxLayout.X_AXIS));
		uploadField = new JTextField(20);
		uploadField.setFont(TEXT_FONT);
		uploadField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		uploadField.setEditable(false);
		browseButton = new WButton("Browse");
		browse.add(uploadField);
		browse.add(browseButton);
		upload.add(createLabel("Upload a New Assignment", SUB_TITLE_FONT),
				BorderLayout.NORTH);
		upload.add(browse, BorderLayout.CENTER);
		return upload;
	}

	/** Creates the date panel
	 * @return The created date panel
	 */
	private JPanel createDatePanel()
	{
		JPanel datePanel = new JPanel(new GridLayout(3, 1));
		month = new JTextField(2);
		day = new JTextField(2);
		year = new JTextField(4);
		uploadButton = new WButton("Upload");
		month.setFont(TEXT_FONT);
		day.setFont(TEXT_FONT);
		year.setFont(TEXT_FONT);
		JPanel uploadContainer = new JPanel();
		uploadContainer.add(uploadButton);
		datePanel.add(createLabel("Set a Due Date", SUB_TITLE_FONT), 0);
		datePanel.add(createTheDate(), 1);
		datePanel.add(uploadContainer, 2);
		return datePanel;
	}

	/** Creates the date panel
	 * @return The date panel
	 */
	private JPanel createTheDate()
	{
		JPanel date = new JPanel();
		date.add(month);
		date.add(createLabel("/", TEXT_FONT));
		date.add(day);
		date.add(createLabel("/", TEXT_FONT));
		date.add(year);
		return date;
	}

	@Override
	protected JPanel createAssignmentPanel()
	{
		JPanel assignmentPanel = new JPanel(new BorderLayout());
		assignmentPanel.add(createAssignmentList(), BorderLayout.CENTER);
		assignmentPanel.add(createUploadPanel(), BorderLayout.SOUTH);
		return assignmentPanel;
	}

	/** Creates the panel containing the assignment list
	 * @return The assignment list
	 */
	private JPanel createAssignmentList()
	{
		JPanel theList = new JPanel(new BorderLayout());
		theList.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		JScrollPane scrollPane = new JScrollPane(itemDisplay);
		scrollPane.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		theList.add(createListHeader(), BorderLayout.NORTH);
		theList.add(scrollPane, BorderLayout.CENTER);
		return theList;
	}

	/** Creates the list header
	 * @return The created panel with the list header 
	 */
	private JPanel createListHeader()
	{
		JPanel theHeader = new JPanel(new GridLayout(1, 3));
		theHeader.setBorder(new JTextField().getBorder());
		theHeader.add(createLabel("Name", SUB_TITLE_FONT));
		theHeader.add(createLabel("Active", SUB_TITLE_FONT));
		theHeader.add(createLabel("Due Date", SUB_TITLE_FONT));
		return theHeader;
	}

	/** creates upload panel
	 * @return The created upload panel
	 */
	private JPanel createUploadPanel()
	{
		JPanel uploadPanel = new JPanel(new GridLayout(1, 2));
		uploadPanel.setBorder(BorderFactory.createEtchedBorder());
		uploadPanel.add(createUpload(), 0);
		uploadPanel.add(createDatePanel(), 1);
		return uploadPanel;
	}
}
