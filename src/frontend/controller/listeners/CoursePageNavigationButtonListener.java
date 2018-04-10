package frontend.controller.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import frontend.view.pages.PageNames;
import frontend.view.pages.components.PageNavigator;
import shared.objects.Course;

public class CoursePageNavigationButtonListener implements ActionListener
{
	protected PageNavigator pageNavigator;
	protected Course course;
	protected String pageName;

	public CoursePageNavigationButtonListener(Course course, String pageName,
			PageNavigator pageNavigator)
	{
		this.course = course;
		this.pageName = pageName;
		this.pageNavigator = pageNavigator;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		pageNavigator.showPage(pageName + course.getId());
	}
}