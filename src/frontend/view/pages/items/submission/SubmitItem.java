package frontend.view.pages.items.submission;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
		this.setBorder(new LineBorder(CONTRAST_COLOR));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
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
		submissionPanel = new JPanel(new BorderLayout());
		submissionPanel.setBorder(new EmptyBorder(0, 40, 0, 40));
		String title = submission.getTitle();
//		if (title.length() > 20)
//		{
//			title = title.substring(0, 16) + "...";
//		}
		assignmentLink = new WLabel(title);
		submissionPanel.add(assignmentLink, BorderLayout.WEST);
		submissionPanel.add(new JPanel(), BorderLayout.EAST);
		return submissionPanel;
	}

	@Override
	public int getId()
	{
		return submission.getId();
	}
}
