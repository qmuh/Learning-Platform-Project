package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.SubmitItem;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Submission;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class SubmissionPage extends CoursePage<SubmitItem, Submission>
{

	private Vector<Submission> theSubmissions;
	private Vector<Assignment> theAssignments;
	private Vector<JPanel> thePanels;

	public SubmissionPage(Course course)
	{
		super(course);
		this.setName(SUBMISSION_PAGE + course.getId());
		setPageTitle("Submissions");
		
		setAssignments(); // remove this later
		createAssignmentPanels();
		bodyCenter.add(createSubmissionPage(), BorderLayout.CENTER);
	}
	
	private JScrollPane createSubmissionPage()
	{
		JPanel view = new JPanel();
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		
		for(JPanel panel : thePanels)
		{
			panel.setPreferredSize(new Dimension(0, 200));
			view.add(panel);
		}
		
		
		
		JScrollPane submissionPagePanel = new JScrollPane(view);
		return submissionPagePanel;
	}
	
	private void setAssignments()
	{
		theAssignments = new Vector<Assignment>();
		theAssignments.add(new Assignment(100, "Assignment 1", "C:\\Users\\trevo\\repos\\ENSF409\\", true, "04/10/2018"));
		theAssignments.add(new Assignment(100, "Assignment 2", "C:\\Users\\trevo\\repos\\ENSF409\\", true, "04/11/2018"));
		theAssignments.add(new Assignment(100, "Assignment 3", "C:\\Users\\trevo\\repos\\ENSF409\\", true, "04/12/2018"));
		theAssignments.add(new Assignment(100, "Assignment 4", "C:\\Users\\trevo\\repos\\ENSF409\\", true, "04/12/2018"));

	}
	
	private void createAssignmentPanels()
	{
		thePanels = new Vector<JPanel>();
		MatteBorder matte = new MatteBorder(2, 2, 2, 2, ACCENT_COLOR);
		TitledBorder titledBorder = null;
		for(Assignment assignment: theAssignments)
		{
			titledBorder = new TitledBorder(assignment.getTitle());
			titledBorder.setBorder(matte);
			titledBorder.setTitleFont(SUB_TITLE_FONT);
			JPanel assignmentPanel = new JPanel();
			assignmentPanel.setBorder(titledBorder);
			assignmentPanel.add(createSubmission());
			thePanels.add(assignmentPanel);
		}
	}
	
	private JPanel createSubmission()
	{
		JPanel submissionPanel = new JPanel();
		submissionPanel.add(createLabel("Default", TEXT_FONT));
		return submissionPanel;
	}
	
	public void addAssignment(Assignment assignment)
	{
		
	}
	
	@Override
	public void addToBoxList(SubmitItem item)
	{
		super.addToBoxList(item);
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub

	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		SubmissionPage submissionPage = new SubmissionPage(new Course(1010101, "ENGG 201", true));
		frame.add(submissionPage);
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
