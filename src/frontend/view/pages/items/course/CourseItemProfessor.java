package frontend.view.pages.items.course;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import frontend.view.pages.components.customSwing.WButtonActivatable;
import frontend.view.pages.components.customSwing.WLabel;
import shared.objects.Course;

/**
 * Provides a class that stores course items on a page from a professor's
 * perspective.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 2.0
 * @since April 13, 2018
 */
final public class CourseItemProfessor extends CourseItem
{
	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * The button to activate or deactivate a course and set visibility to
	 * students.
	 */
	private WButtonActivatable activeButton;

	public CourseItemProfessor(Course course)
	{
		super(course);
		JPanel thePanel = new JPanel(new GridLayout(1, 3));
		thePanel.add(new WLabel(course.getName()));
		thePanel.add(createActiveButton());
		thePanel.add(createViewButton("View"));
		this.add(thePanel);
	}

	private JPanel createActiveButton()
	{
		JPanel activePanel = new JPanel(new BorderLayout());
		activeButton = new WButtonActivatable(course.getActive());
		activeButton.setPreferredSize(new Dimension(200, 30));
		activePanel.add(activeButton, BorderLayout.EAST);
		return activePanel;
	}

	public WButtonActivatable getActiveButton()
	{
		return activeButton;
	}
}
