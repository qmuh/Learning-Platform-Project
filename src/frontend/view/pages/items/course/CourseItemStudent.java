package frontend.view.pages.items.course;

import java.awt.GridLayout;

import javax.swing.JPanel;

import frontend.view.pages.components.customSwing.WLabel;
import shared.objects.Course;

/**
 * Provides a class that stores course items on a page from a student's
 * perspective.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class CourseItemStudent extends CourseItem
{
	private static final long serialVersionUID = 1L;

	public CourseItemStudent(Course course)
	{
		super(course);
		JPanel thePanel = new JPanel(new GridLayout(1, 3));
		thePanel.add(new WLabel(course.getName()));
		thePanel.add(new JPanel());
		thePanel.add(createViewButton("View"));
		this.add(thePanel);
	}
}
