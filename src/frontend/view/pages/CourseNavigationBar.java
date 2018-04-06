package frontend.view.pages;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import frontend.interfaces.WondrisInfo;

public class CourseNavigationBar extends JPanel implements WondrisInfo
{
	private static final long serialVersionUID = 1L;
	private JButton enrollment;
	private JButton assignment;
	private JButton grades;
	public CourseNavigationBar() {
		this.setLayout(new GridLayout(3, 1));
		enrollment = new JButton("Enrollments");
		assignment = new JButton("Assignments");
		grades = new JButton("Grades");
		this.add(createButton(enrollment));
		this.add(createButton(assignment));
		this.add(createButton(grades));
	}
	
	private JButton createButton(JButton b) {
		b.setFont(TITLE_FONT);
//		b.setPreferredSize(new Dimension(400, 200));
		return b;
	}
	
}
