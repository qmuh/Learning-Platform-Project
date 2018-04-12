package frontend.view.pages.components;

import frontend.controller.listeners.CoursePageNavigationButtonListener;
import frontend.view.pages.components.customSwing.WButton;
import shared.objects.Course;

public class CourseNavigationBarStudent extends CourseNavigationBar
{

	private static final long serialVersionUID = 1L;
	private WButton gradeButton;

	public CourseNavigationBarStudent()
	{
		super();
		gradeButton = createNavBarButton("Grades");

		this.add(assignmentButton);
		this.add(submissionButton);
		this.add(gradeButton);
		this.add(discussionButton);
		this.add(composeEmailButton);
	}

	@Override
	public void createListeners(Course course, PageNavigator pageNavigator)
	{
		super.createListeners(course, pageNavigator);
		gradeButton.addActionListener(new CoursePageNavigationButtonListener(
				course, GRADES_PAGE, pageNavigator));
	}
}
