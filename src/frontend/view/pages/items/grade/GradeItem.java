package frontend.view.pages.items.grade;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import frontend.view.pages.components.customSwing.WLabel;
import frontend.view.pages.items.GeneralItem;
import shared.objects.Grade;

/**
 * Provides a class that stores grade items on a page.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class GradeItem extends GeneralItem
{
	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The grade object stored inside of the grade display item.
	 */
	private Grade grade;

	public GradeItem(String assignmentName, Grade grade)
	{
		super(BoxLayout.X_AXIS, Integer.toString(grade.getId()));
		this.grade = grade;

		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		this.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(ACCENT_COLOR), new EmptyBorder(0, 60, 0, 0)));

		JPanel inner = new JPanel(new GridLayout(1, 2));
		inner.add(new WLabel(assignmentName), 0);

		inner.add(new WLabel(Integer.toString(grade.getGrade()) + "%"), 1);

		this.add(inner);
	}

	@Override
	public int getId()
	{
		return grade.getId();
	}
}
