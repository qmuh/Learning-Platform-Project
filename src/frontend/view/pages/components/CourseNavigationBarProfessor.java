package frontend.view.pages.components;

import frontend.controller.listeners.CoursePageNavigationButtonListener;
import frontend.view.pages.components.customSwing.WButton;
import shared.objects.Course;

public class CourseNavigationBarProfessor extends CourseNavigationBar
{

	private static final long serialVersionUID = 1L;
	private WButton enrollmentButton;

	public CourseNavigationBarProfessor()
	{
		super();
		enrollmentButton = new WButton("Enrollments");
		this.add(assignmentButton);
		this.add(submissionButton);
		this.add(enrollmentButton);
		this.add(discussionButton);
		this.add(composeEmailButton);
	}

	@Override
	public void createListeners(Course course, PageNavigator pageNavigator)
	{
		super.createListeners(course, pageNavigator);
		enrollmentButton.addActionListener(
				new CoursePageNavigationButtonListener(course, ENROLLMENT_PAGE,
						pageNavigator));
	}
}
