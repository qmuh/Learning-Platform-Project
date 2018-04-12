package frontend.view.pages.compose;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import shared.objects.Course;
import shared.objects.Professor;
import shared.objects.Student;
import shared.objects.User;

public class ComposeEmailPageProfessor extends ComposeEmailPage
{

	private static final long serialVersionUID = 1L;
	private WButton sendToAllButton, addToEmailButton;
	private JList<Student> studentList;

	public ComposeEmailPageProfessor(Course course, Professor professor)
	{
		super(course, professor);
		sendToAllButton = new WButton("Add All Enrolled Students");

		createButtonPanel();
		bodyCenter.add(createEnrolledStudentPanel(), BorderLayout.WEST);

	}

	private JPanel createEnrolledStudentPanel()
	{
		JPanel enrolledStudentPanel = new JPanel(new BorderLayout());
		enrolledStudentPanel.add(createStudentListHeader(), BorderLayout.NORTH);
		enrolledStudentPanel.add(createStudentList(), BorderLayout.CENTER);
		return enrolledStudentPanel;
	}

	private JPanel createStudentListHeader()
	{
		JPanel studentHeader = new JPanel();
		studentHeader.add(new WLabel("Enrolled Students:", SUB_TITLE_FONT));
		return studentHeader;
	}

	private JPanel createStudentList()
	{
		JPanel studentListPanel = new JPanel(new BorderLayout());
		studentListPanel
				.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		studentList = new JList<Student>();
		studentList.setFont(TEXT_FONT);
//		studentList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane scrollPane = new JScrollPane(studentList);
		scrollPane.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		studentListPanel.add(scrollPane, BorderLayout.CENTER);
		JPanel theButton = new JPanel(new BorderLayout());
		theButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		addToEmailButton = new WButton("Add to Email");
		theButton.add(addToEmailButton, BorderLayout.WEST);
		studentListPanel.add(theButton, BorderLayout.SOUTH);
		return studentListPanel;
	}

	@Override
	protected JPanel createButtonPanel()
	{
		sendButtonPanel = super.createButtonPanel();
		sendToAllButton = new WButton("Send to Enrolled Students");
		sendButtonPanel.add(sendToAllButton, BorderLayout.WEST);
		return sendButtonPanel;
	}

	public WButton getSendToAllButton()
	{
		return sendToAllButton;
	}

	public WButton getAddToEmailButton()
	{
		return addToEmailButton;
	}

	public JList<Student> getStudentList()
	{
		return studentList;
	}

	public String getSelected()
	{
		Student selected = getStudentList().getSelectedValue();
		return selected.getEmail();
	}

	public void setStudentList(Vector<Student> enrollList)
	{
		studentList.clearSelection();
		studentList.setListData(enrollList);
	}
}
