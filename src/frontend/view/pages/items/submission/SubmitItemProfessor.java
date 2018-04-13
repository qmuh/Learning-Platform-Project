package frontend.view.pages.items.submission;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import shared.objects.Submission;

/**
 * Provides a class represents is a student's submission item from a professor's
 * perspective.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
final public class SubmitItemProfessor extends SubmitItem
{

	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The grade given to the submission.
	 */
	private JTextField gradeField;
	
	/**
	 * The button to submit the grade to the server.
	 */
	private WButton gradeButton;

	public SubmitItemProfessor(Submission submission)
	{
		super(submission);
		submissionPanel.remove(1);
		submissionPanel.add(createGrade(), BorderLayout.EAST);
	}

	private JPanel createGrade()
	{
		JPanel gradePanel = new JPanel(new GridLayout(1, 3));

		gradePanel.add(createGradeFieldPanel(), 0);
		gradePanel.add(createPercentLabel(), 1);
		gradePanel.add(createGradeButtonPanel(), 2);
		return gradePanel;
	}

	private JPanel createGradeFieldPanel()
	{
		JPanel gradeFieldPanel = new JPanel(new GridBagLayout());
		gradeField = new JTextField(2);
		int grade = submission.getGrade();
		if (grade <= 0)
		{
			gradeField.setText(Integer.toString(grade));
		}
		gradeField.setHorizontalAlignment(JTextField.RIGHT);
		gradeField.setFont(TEXT_FONT);
		gradeField.setText(Integer.toString(submission.getGrade()));
		gradeFieldPanel.add(gradeField, new GridBagConstraints());
		return gradeFieldPanel;
	}

	private JPanel createPercentLabel()
	{
		JPanel percentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		WLabel percent = new WLabel("%");
		percentPanel.add(percent);
		return percentPanel;
	}

	private JPanel createGradeButtonPanel()
	{
		JPanel buttonPanel = new JPanel();
		gradeButton = new WButton("Grade");
		buttonPanel.add(gradeButton);
		return buttonPanel;
	}

	public JTextField getGradeField()
	{
		return gradeField;
	}

	public WButton getGradeButton()
	{
		return gradeButton;
	}

}
