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

final public class AssignmentPageStudent extends AssignmentPage
{
	private static final long serialVersionUID = 1L;

	private File selectedFile;

	public AssignmentPageStudent(Course course, Student student)
	{
		super(course, student);
	}

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
