package frontend.view.pages;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import frontend.interfaces.WondrisInfo;
import shared.objects.Course;

public class CourseNavigationBar extends JPanel implements WondrisInfo
{
	private static final long serialVersionUID = 1L;


	private JButton assignmentButton, submissionButton, enrollmentButton, myMessagesButton, composeEmailButton;


	public CourseNavigationBar() {
		this.setLayout(new GridLayout(5, 1));
		assignmentButton = new JButton("Assignments");
		submissionButton = new JButton("Submissions");
		enrollmentButton = new JButton("Enrollments");
		myMessagesButton = new JButton("My Messages");
		composeEmailButton = new JButton("Compose Email");
		this.add(createButton(assignmentButton));
		this.add(createButton(submissionButton));
		this.add(createButton(enrollmentButton));
		this.add(createButton(myMessagesButton));
		this.add(createButton(composeEmailButton));
	}

	private JButton createButton(JButton b)
	{
		b.setFont(TITLE_FONT);
		return b;
	}

	public void setEnrollmentButtonListener(ActionListener listener)
	{
		enrollmentButton.addActionListener(listener);
	}

	public void setAssignmentButtonListener(ActionListener listener)
	{
		assignmentButton.addActionListener(listener);
	}

	public void setSubmissionButtonListener(ActionListener listener)
	{
		submissionButton.addActionListener(listener);
	}

	public void setMyMessagesButtonListener(ActionListener listener)
	{
		myMessagesButton.addActionListener(listener);
	}

	public void setComposeEmailButtonListener(ActionListener listener)
	{
		composeEmailButton.addActionListener(listener);
	}

}
