package frontend.view.pages.items;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WButtonActivatable;
import shared.objects.Course;

final public class CourseItemProfessor extends CourseItem
{

	private static final long serialVersionUID = 1L;
	private WButtonActivatable activeButton;

	public CourseItemProfessor(Course course)
	{
		super(course);
		JPanel thePanel = new JPanel(new GridLayout(1, 3));
		thePanel.add(createLabel(course.getName()));
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
