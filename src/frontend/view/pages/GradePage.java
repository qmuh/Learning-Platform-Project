package frontend.view.pages;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import frontend.view.pages.items.grade.GradeItem;
import shared.objects.Course;
import shared.objects.Grade;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class GradePage extends CoursePage<GradeItem, Grade>
{
	private static final long serialVersionUID = 1L;

	public GradePage(Course course)
	{
		super(course);
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
	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Testing 123");
		GradePage gradePage = new GradePage(new Course(10101, "ENGG 501", true));
		gradePage.addToBoxList(new GradeItem("Short Quiz", new Grade(1, 87, 221, 1123)));
		gradePage.addToBoxList(new GradeItem("Long Descriptive Assignment 2", new Grade(1, 73, 221, 1123)));
		gradePage.addToBoxList(new GradeItem("Reasonable Assigmment", new Grade(1, 100, 221, 1123)));
		frame.add(gradePage);
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
