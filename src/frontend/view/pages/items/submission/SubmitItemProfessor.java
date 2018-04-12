package frontend.view.pages.items.submission;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import shared.objects.Submission;

final public class SubmitItemProfessor extends SubmitItem
{

	private static final long serialVersionUID = 1L;
	private JTextField gradeField;
	private WButton gradeButton;
	
	public SubmitItemProfessor(Submission submission)
	{
		super(submission);
		submissionPanel.remove(1);
		submissionPanel.add(createGrade(), 1);
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
		if(grade <= 0)
		{
			gradeField.setText(Integer.toString(grade));
		}
		gradeField.setHorizontalAlignment(JTextField.RIGHT);
		gradeField.setFont(TEXT_FONT);
		gradeFieldPanel.add(gradeField, new GridBagConstraints());
		return gradeFieldPanel;
	}
	
	private JPanel createPercentLabel()
	{
		JPanel percentPanel = new JPanel();
		WLabel percent = new WLabel("%", JLabel.LEFT);
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
