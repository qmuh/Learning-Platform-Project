package frontend.view.pages.items;

import java.awt.GridLayout;

import javax.swing.JPanel;

import shared.objects.Course;

public class CourseItemProfessor extends CourseItem
{

	private static final long serialVersionUID = 1L;

	public CourseItemProfessor(Course course)
	{
		super(course);
		JPanel thePanel = new JPanel(new GridLayout(1, 3));
		thePanel.add(createLabel(course.getName()));
		thePanel.add(createActiveButton());
		thePanel.add(createViewButton("View"));
		this.add(thePanel);
	}

}
