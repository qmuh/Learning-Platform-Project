package frontend.view.pages.grade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import frontend.view.pages.course.CoursePage;
import frontend.view.pages.items.grade.GradeItem;
import shared.objects.Course;
import shared.objects.Grade;
import shared.objects.User;

/**
 * The grade page that students view. 
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class GradePage extends CoursePage<GradeItem, Grade>
{
	private static final long serialVersionUID = 1L;

	public GradePage(Course course, User user)
	{
		super(course, user);
		this.setName(GRADES_PAGE + course.getId());
		this.setPageTitle("Grades");
		bodyCenter.add(createGradesPage(), BorderLayout.CENTER);
	}

	private JPanel createGradesPage()
	{
		JPanel gradesPagePanel = new JPanel(new BorderLayout());
		gradesPagePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		JPanel gradesHeader = new JPanel(new GridLayout(1, 2));
		gradesHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		gradesHeader.add(createLabel("Assignment", SUB_TITLE_FONT));
		gradesHeader.add(createLabel("Grade", SUB_TITLE_FONT));
		gradesPagePanel.add(gradesHeader, BorderLayout.NORTH);
		gradesPagePanel.add(createTheGrades(), BorderLayout.CENTER);
		return gradesPagePanel;
	}

	private JScrollPane createTheGrades()
	{
		JScrollPane scrollPane = new JScrollPane(itemDisplay);
		return scrollPane;
	}

	public void addGradeItem(GradeItem item)
	{
		itemDisplay.add(item);
	}

	

}
