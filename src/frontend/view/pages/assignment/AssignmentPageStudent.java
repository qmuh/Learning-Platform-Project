package frontend.view.pages.assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import frontend.view.pages.components.customSwing.WLabel;
import shared.objects.Course;
import shared.objects.Student;

/**
 * The assignment page for the student
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
final public class AssignmentPageStudent extends AssignmentPage
{
	/**
	 * The serialversionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The selected file which is to be sent to the server
	 */
	private File selectedFile;

	/** Constructor for the AssignmentPageStudent
	 * @param course The specific course 
	 * @param student The student with the page
	 */
	public AssignmentPageStudent(Course course, Student student)
	{
		super(course, student);
	}

	/** Creates the assignment header
	 * @return The created assignment header
	 */
	private JPanel createAssignmentHeader()
	{
		JPanel assignmentHeader = new JPanel(new GridLayout(1, 3));
		assignmentHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));;
		assignmentHeader.add(new WLabel("Assignment", SUB_TITLE_FONT), 0);
		assignmentHeader.add(new WLabel("Due Date", SUB_TITLE_FONT), 1);
		assignmentHeader.add(new WLabel("Submit", SUB_TITLE_FONT), 2);
		return assignmentHeader;
	}

	@Override
	protected JPanel createAssignmentPanel()
	{
		JPanel assignmentPanel = new JPanel(new BorderLayout());
		assignmentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		assignmentPanel.add(createAssignmentHeader(), BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(itemDisplay);
		assignmentPanel.add(scrollPane, BorderLayout.CENTER);
		return assignmentPanel;
	}

	public void setFile(File selectedFile)
	{
		this.selectedFile = selectedFile;

	}

	public File getFile()
	{
		return selectedFile;
	}

	public String getDate()
	{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HH").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

}
