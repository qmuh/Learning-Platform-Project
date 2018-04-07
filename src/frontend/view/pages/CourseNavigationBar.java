package frontend.view.pages;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import frontend.interfaces.WondrisInfo;

public class CourseNavigationBar extends JPanel implements WondrisInfo
{
	private static final long serialVersionUID = 1L;

	private JButton enrollmentButton;
	private JButton assignmentButton;
	private JButton gradesButton;

	public CourseNavigationBar()
	{
		this.setLayout(new GridLayout(3, 1));
		enrollmentButton = new JButton("Enrollments");
		assignmentButton = new JButton("Assignments");
		gradesButton = new JButton("Grades");
		this.add(createButton(enrollmentButton));
		this.add(createButton(assignmentButton));
		this.add(createButton(gradesButton));
	}

	private JButton createButton(JButton b)
	{
		b.setFont(TITLE_FONT);
		// b.setPreferredSize(new Dimension(400, 200));
		return b;
	}

	public void setEnrollmentButtonListener(ActionListener listener)
	{
		enrollmentButton.addActionListener(listener);
	}

	public void setAssignmentButtonListener(ActionListener listener)
	{
		assignmentButton.addActionListener(listener);
	}

	public void setGradesButtonListener(ActionListener listener)
	{
		gradesButton.addActionListener(listener);
	}

}
