package frontend.view.pages;

import javax.swing.JLabel;

import frontend.components.BoxList;
import frontend.view.pages.items.CourseItem;
import sharedobjects.Course;

public class CoursePage extends Page<CourseItem, Course>
{
	private Course course;
	
	public CoursePage(BoxList<CourseItem> boxList)
	{
		super(boxList);
		this.add(new JLabel("Jimmy finally started to code"));
	}

}
