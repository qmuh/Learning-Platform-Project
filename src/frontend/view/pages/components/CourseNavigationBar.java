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

/**
 * The course navigation bar used when inside a course
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public abstract class CourseNavigationBar extends JPanel
		implements WondrisInfo, PageNames
{
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The maximum size for a button
	 */
	private static final Dimension MAX_BUTTON_SIZE = new Dimension(Integer.MAX_VALUE, 100); 
	
	/**
	 * The minimum size for a button
	 */
	private static final Dimension MIN_BUTTON_SIZE = new Dimension(0, 50); 

	/**
	 * The protected WButtons
	 */
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
