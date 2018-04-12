package frontend.view.pages.assignment;

import java.awt.BorderLayout;
import java.awt.Component;

import frontend.interfaces.WondrisInfo;
import frontend.view.pages.course.CoursePage;
import frontend.view.pages.items.assignment.AssignItem;
import shared.objects.Assignment;
import shared.objects.Course;


/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version
 * @since April 4, 2018
 */
abstract public class AssignmentPage extends CoursePage<AssignItem, Assignment>
		implements WondrisInfo
{
	private static final long serialVersionUID = 1L;


	public AssignmentPage(Course course)
	{
		super(course);
		this.setName(ASSIGNMENT_PAGE + course.getId());
		this.setPageTitle("Assignments");
		this.bodyCenter.add(createAssignmentPanel(), BorderLayout.CENTER);
	}

	abstract protected Component createAssignmentPanel();

	@Override
	public void displayPage()
	{
		itemDisplay.revalidate();
		itemDisplay.repaint();
	}

//	public void setAssignmentVector(Vector<Assignment> myList)
//	{
//		itemDisplay.removeAll();
//		for (Assignment assignment : myList)
//		{
//			this.addToBoxList(new AssignItem(assignment));
//		}
//		System.out.println("# OF ASSIGNMENTS RECEIVED: " + itemDisplay.getComponentCount());
//		displayPage();
//	}
}
