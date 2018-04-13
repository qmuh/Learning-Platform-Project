package frontend.view.pages.items.submission;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.GeneralItem;
import shared.objects.Student;

public class StudentSubItem extends GeneralItem
{
	private static final long serialVersionUID = 1L;
	private Student student;
	private BoxList<SubmitItem> submissionItems;

	public StudentSubItem(Student student)
	{
		super(BoxLayout.Y_AXIS, Integer.toString(student.getId()));
		this.submissionItems = new BoxList<SubmitItem>();
		this.student = student;
		this.add(studentSubmissionsPanel());
	}

	private JPanel studentSubmissionsPanel()
	{
		JPanel studentSubmissionPanel = new JPanel();
		studentSubmissionPanel.setOpaque(false);
		studentSubmissionPanel.setLayout(new BorderLayout());

		studentSubmissionPanel.add(submissionItems, BorderLayout.NORTH);
		return studentSubmissionPanel;
	}

	public void setNameTitle()
	{
		MatteBorder matte = new MatteBorder(2, 2, 2, 2, Color.BLACK);
		TitledBorder titledBorder = new TitledBorder(matte);
		titledBorder
				.setTitle(student.getFirstName() + " " + student.getLastName());
		titledBorder.setTitleFont(TEXT_FONT);
		this.setBorder(titledBorder);
	}

	@Override
	public int getId()
	{
		return student.getId();
	}

	public void addSubmissionItem(SubmitItem submitItem)
	{
		this.submissionItems.add(submitItem);
	}
}
