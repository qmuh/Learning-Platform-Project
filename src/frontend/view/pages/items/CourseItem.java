package frontend.view.pages.items;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import frontend.interfaces.WondrisInfo;
import sharedobjects.Course;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class CourseItem extends GeneralItem implements WondrisInfo
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public void setActiveCheckBoxListener(ActionListener listener)
	{
		active.addActionListener(listener);
	}
	
	@Override
	public int getId()
	{
		return course.getId();
	}
}
