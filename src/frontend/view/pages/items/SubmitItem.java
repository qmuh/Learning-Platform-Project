package frontend.view.pages.items;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import frontend.view.pages.components.customSwing.WButton;
import shared.objects.Submission;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class SubmitItem extends GeneralItem
{
	private static final long serialVersionUID = 1L;
	
	private WButton gradeButton;
	private JTextField gradeField;
	private Submission submission;
	private JLabel assignmentLink;

	public SubmitItem(Submission submission)
	{
		super(BoxLayout.X_AXIS, Integer.toString(submission.getId()));
		this.submission = submission;
		
		this.setBackground(SECONDARY_COLOR);
		
		this.add(makeSubmissionPanel());
	}
	
	public JLabel getAssignmentLink()
	{
		return assignmentLink;
	}
	
	public Submission getSubmission()
	{
		return submission;
	}
	
	public JTextField getGradeTextField()
	{
		return gradeField;
	}
	
	public WButton getGradeButton()
	{
		return gradeButton;
	}
	
	private JPanel makeSubmissionPanel()
	{
		JPanel submissionPanel = new JPanel(new GridLayout(1, 2));
		JPanel submissionPanelRight = new JPanel(new GridLayout(1, 3));
		
		submissionPanel.setBorder(new EmptyBorder(0, 40, 0, 40));
		JLabel submissionTitleLeft = new JLabel(submission.getTitle());
		submissionTitleLeft.setFont(TEXT_FONT);
		JLabel gradePercent = new JLabel("%");
		gradePercent.setFont(TEXT_FONT);
		
		gradePercent.setHorizontalAlignment(JLabel.LEFT);
		
		gradeField = new JTextField(2);
		int grade = submission.getGrade();
		if (grade >=  0)
		{
			gradeField.setText(String.valueOf(grade));
		}
		
		gradeField.setHorizontalAlignment(JTextField.RIGHT);
		gradeField.setFont(TEXT_FONT);
		gradeButton = new WButton("Grade");
		JPanel gradeFieldPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gradeFieldPanel.add(gradeField, gridBagConstraints);
		submissionTitleLeft.setHorizontalAlignment(JLabel.LEFT);
		
		submissionPanelRight.add(gradeFieldPanel, 0);
		submissionPanelRight.add(gradePercent, 1);
		submissionPanelRight.add(gradeButton, 2);
		
		submissionPanel.add(submissionTitleLeft, 0);
		submissionPanel.add(submissionPanelRight, 1);
		
		return submissionPanel;
	}

	@Override
	public int getId()
	{
		return submission.getId();
	}
	
}
