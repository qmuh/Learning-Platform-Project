package frontend.view.pages.items;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import frontend.interfaces.WondrisInfo;
import sharedobjects.Course;

public class CourseItem extends GeneralItem implements WondrisInfo
{

	private Course course;
	private JCheckBox active;
	private JButton view;

	public CourseItem(Course course)
	{
		super(BoxLayout.X_AXIS, Integer.toString(course.getId()));
		this.course = course;
		this.add(createCheckBox());
		this.add(createLabel(course.getName()));
		this.add(createButton("View"));
	}

	private JCheckBox createCheckBox()
	{
		active = new JCheckBox();
		active.setSelected(course.getActive());
		return active;
	}

	private JLabel createLabel(String name)
	{
		JLabel label = new JLabel(name);
		label.setFont(TEXT_FONT);
		return label;
	}

	private JButton createButton(String name)
	{
		view = new JButton(name);
		view.setFont(TEXT_FONT);
		return view;
	}

	public void setViewButtonListener(ActionListener listener)
	{
		view.addActionListener(listener);
	}

	@Override
	public int getId()
	{
		return course.getId();
	}
}
