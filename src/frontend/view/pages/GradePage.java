package frontend.view.pages;

import frontend.components.BoxList;
import frontend.view.pages.items.GradeItem;
import sharedobjects.Course;
import sharedobjects.Grade;

public class GradePage extends Page<GradeItem, Grade>
{
	private Course course;
	
	public GradePage(BoxList<GradeItem> boxList)
	{
		super(boxList);
	}

	
}
