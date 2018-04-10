package frontend.view.pages.components;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import frontend.controller.listeners.CoursePageNavigationButtonListener;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.PageNames;
import frontend.view.pages.components.customSwing.WButton;
import shared.objects.Course;

public class CourseNavigationBar extends JPanel
		implements WondrisInfo, PageNames
{
	private static final long serialVersionUID = 1L;

	private WButton assignmentButton, submissionButton, enrollmentButton,
			discussionButton, composeEmailButton;

	public CourseNavigationBar()
	{
		this.setLayout(new GridLayout(5, 1));
		assignmentButton = new WButton("Assignments");
		submissionButton = new WButton("Submissions");
		enrollmentButton = new WButton("Enrollments");
		discussionButton = new WButton("Discussion");
		composeEmailButton = new WButton("Compose Email");
		this.add(createButton(assignmentButton));
		this.add(createButton(submissionButton));
		this.add(createButton(enrollmentButton));
		this.add(createButton(discussionButton));
		this.add(createButton(composeEmailButton));
	}

	private WButton createButton(WButton b)
	{
		b.setFont(TITLE_FONT);
		return b;
	}

	public void createListeners(Course course, PageNavigator pageNavigator)
	{
		assignmentButton.addActionListener(
				new CoursePageNavigationButtonListener(course, ASSIGNMENT_PAGE,
						pageNavigator));

		enrollmentButton.addActionListener(
				new CoursePageNavigationButtonListener(course, ENROLLMENT_PAGE,
						pageNavigator));

		submissionButton.addActionListener(
				new CoursePageNavigationButtonListener(course, SUBMISSION_PAGE,
						pageNavigator));

		discussionButton.addActionListener(
				new CoursePageNavigationButtonListener(course, DISCUSSION_PAGE,
						pageNavigator));

		composeEmailButton.addActionListener(
				new CoursePageNavigationButtonListener(course,
						COMPOSE_EMAIL_PAGE, pageNavigator));
	}

}
