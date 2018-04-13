package frontend.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontend.view.pages.components.PageNavigator;
import shared.objects.Course;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class CoursePageNavigationButtonListener implements ActionListener
{
	/**
	 * The class used too navigate the functionalities of a course page
	 */
	protected PageNavigator pageNavigator;

	/**
	 * The course name
	 */
	protected Course course;

	/**
	 * The page name
	 */
	protected String pageName;

	/**
	 * Listener for the button that had to do with a course
	 * 
	 * @param course
	 *            The course associated with the listener
	 * @param pageName
	 *            The name of the page name
	 * @param pageNavigator
	 *            The page navigator
	 */
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
		pageNavigator.searchPage(pageName + course.getId()).refresh();
		pageNavigator.showPage(pageName + course.getId());
	}
}