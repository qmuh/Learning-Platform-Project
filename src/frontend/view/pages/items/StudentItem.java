package frontend.view.pages.items;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;

import sharedobjects.Student;

public class StudentItem extends Box
{
	private static final long serialVersionUID = 1L;
	private Student student;
	
	public StudentItem(Student student) {
		super(BoxLayout.X_AXIS);
		this.student = student;
	}
	
}
