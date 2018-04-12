package frontend.view.pages.components;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import frontend.controller.listeners.CoursePageNavigationButtonListener;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.interfaces.PageNames;
import shared.objects.Course;

public abstract class CourseNavigationBar extends JPanel
		implements WondrisInfo, PageNames
{
	private static final long serialVersionUID = 1L;
	private static final Dimension MAX_BUTTON_SIZE = new Dimension(Integer.MAX_VALUE, 100); 
	private static final Dimension MIN_BUTTON_SIZE = new Dimension(0, 50); 

	protected WButton assignmentButton, submissionButton, discussionButton,
			composeEmailButton;

	public CourseNavigationBar()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		assignmentButton = createNavBarButton("Assignments");
		submissionButton = createNavBarButton("Submissions");
		discussionButton = createNavBarButton("Discussion");
		composeEmailButton = createNavBarButton("Compose Email");
	}
	
	protected WButton createNavBarButton(String text)
	{
		WButton button = new WButton(text);
		button.setMaximumSize(MAX_BUTTON_SIZE);
		button.setMinimumSize(MIN_BUTTON_SIZE);
		button.setHorizontalAlignment(JButton.RIGHT);
		return button;
	}

	public void createListeners(Course course, PageNavigator pageNavigator)
	{
		assignmentButton.addActionListener(
				new CoursePageNavigationButtonListener(course, ASSIGNMENT_PAGE,
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
