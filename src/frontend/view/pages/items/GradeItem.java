package frontend.view.pages.items;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import frontend.view.pages.components.customSwing.WLabel;
import javafx.scene.layout.Border;
import shared.objects.Grade;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class GradeItem extends GeneralItem
{

	private static final long serialVersionUID = 1L;

	private Grade grade;

	public GradeItem(String assignmentName, Grade grade)
	{
		super(BoxLayout.X_AXIS, Integer.toString(grade.getId()));
		this.grade = grade;

		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		this.setBorder(BorderFactory.createCompoundBorder(new LineBorder(ACCENT_COLOR),
				new EmptyBorder(0, 60, 0, 0)));

		JPanel inner = new JPanel(new GridLayout(1, 2));
		inner.add(new WLabel(assignmentName), 0);

		inner.add(new WLabel(Integer.toString(grade.getGrade()) + "%", WLabel.CENTER), 1);

		this.add(inner);
	}

	@Override
	public int getId()
	{
		return grade.getId();
	}
}
