package frontend.view.pages.items.submission;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frontend.view.pages.components.customSwing.WLabel;
import frontend.view.pages.items.GeneralItem;
import shared.objects.Submission;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
abstract public class SubmitItem extends GeneralItem
{
	private static final long serialVersionUID = 1L;

	protected Submission submission;
	protected JLabel assignmentLink;
	protected JPanel submissionPanel;

	public SubmitItem(Submission submission)
	{
		super(BoxLayout.X_AXIS, Integer.toString(submission.getId()));
		this.submission = submission;
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
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

	private JPanel makeSubmissionPanel()
	{
		submissionPanel = new JPanel(new GridLayout(1, 2));
		submissionPanel.setBorder(new EmptyBorder(0, 40, 0, 40));
		assignmentLink = new WLabel(submission.getTitle());
		submissionPanel.add(assignmentLink, 0);
		submissionPanel.add(new JPanel(), 1);
		return submissionPanel;
	}

	@Override
	public int getId()
	{
		return submission.getId();
	}
}
