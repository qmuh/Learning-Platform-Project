package frontend.view.pages.items.submission;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.GeneralItem;
import shared.objects.Student;

/**
 * Provides a class that is a container to store a student's submissions.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class StudentSubItem extends GeneralItem
{
	/**
	 * The version of the class.
	 */
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

	/**
	 * Puts a titled border around the container of submissions with the
	 * student's name if called.
	 */
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
