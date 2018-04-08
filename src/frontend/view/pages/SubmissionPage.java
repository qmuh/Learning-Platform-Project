package frontend.view.pages;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

	private Assignment assignment;
	private Submission submission;
	
	public SubmissionPage(Course course)
	{
		super(course);
		this.setName(SUBMISSION_PAGE + course.getId());
		setPageTitle("Submissions");
		bodyCenter.add(createSubmissionPage(), BorderLayout.CENTER);
	}
	
	private JPanel createSubmissionPage()
	{
		JPanel submissionPagePanel = new JPanel();
		
		return submissionPagePanel;
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.add(new SubmissionPage(new Course(1010101, "ENGG 201", true)));
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
