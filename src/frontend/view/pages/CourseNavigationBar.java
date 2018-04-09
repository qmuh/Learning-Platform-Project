package frontend.view.pages;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.customSwing.WButton;

public class CourseNavigationBar extends JPanel implements WondrisInfo
{
	private static final long serialVersionUID = 1L;


	private WButton assignmentButton, submissionButton, enrollmentButton, myEmailButton, composeEmailButton;


	public CourseNavigationBar() {
		this.setLayout(new GridLayout(5, 1));
		assignmentButton = new WButton("Assignments");
		submissionButton = new WButton("Submissions");
		enrollmentButton = new WButton("Enrollments");
		myEmailButton = new WButton("My Emails");
		composeEmailButton = new WButton("Compose Email");
		this.add(createButton(assignmentButton));
		this.add(createButton(submissionButton));
		this.add(createButton(enrollmentButton));
		this.add(createButton(myEmailButton));
		this.add(createButton(composeEmailButton));
	}

	private WButton createButton(WButton b)
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

	public void setMyEmaillButtonListener(ActionListener listener)
	{
		myEmailButton.addActionListener(listener);
	}

	public void setComposeEmailButtonListener(ActionListener listener)
	{
		composeEmailButton.addActionListener(listener);
	}

}
