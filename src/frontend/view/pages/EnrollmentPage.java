package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import sharedobjects.Course;
import sharedobjects.Student;

public class EnrollmentPage extends CoursePage
{
	private Course course;
	private JList<Student> enrolledStudentList;
	private JList<Student> studentSearchResults;

	private JRadioButton id;
	private JRadioButton lastName;
	private JButton search;
	private JButton enroll;
	private JButton unenroll;
	private JTextField searchField;

	public boolean isSearchById()
	{
		return id.isSelected();
	}

	public boolean isSearchByLastName()
	{
		return lastName.isSelected();
	}
	public Student getSelectedStudent()
	{
		return studentSearchResults.getSelectedValue();
	}

	public String getSearchFieldText()
	{
		return searchField.getText();
	}

	public void setStudentSearchResultListListener(
			ListSelectionListener listener)
	{
		studentSearchResults.addListSelectionListener(listener);
	}

	public void setSearchButtonListener(ActionListener listener)
	{
		search.addActionListener(listener);
	}

	public void setEnrollButtonListener(ActionListener listener)
	{
		enroll.addActionListener(listener);
	}

	public void setUnenrollButtonListener(ActionListener listener)
	{
		unenroll.addActionListener(listener);
	}

	public EnrollmentPage(Course course)
	{
		super(course);
		body.add(createEnrollmentPanel(), BorderLayout.CENTER);

	}

	public void setStudentList(Vector<Student> toSet)
	{
		studentSearchResults.clearSelection();
		studentSearchResults.setListData(toSet);
	}
	
	private JSplitPane createEnrollmentPanel()
	{
		JSplitPane enrollmentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				createEnrollmentList(), createSearchPanel());
		return enrollmentPanel;
	}

	private JPanel createSearchPanel()
	{
		JPanel searchPanel = new JPanel(new GridLayout(2, 1));
		searchPanel.add(createSearchArea(), 0);
		searchPanel.add(createSearchResults(), 1);
		return searchPanel;
	}

	private JPanel createSearchResults()
	{
		JPanel searchResults = new JPanel(new BorderLayout());
		searchResults.add(new JLabel("Search Results"), BorderLayout.NORTH);
		studentSearchResults = new JList<Student>();
		searchResults.add(studentSearchResults, BorderLayout.CENTER);
		return searchResults;
	}

	private JPanel createSearchArea()
	{
		JPanel searchArea = new JPanel(new GridLayout(4, 1));
		searchArea.add(new JLabel("Search"), 0);
		searchArea.add(createButtonGroup(), 1);
		searchArea.add(createTextField(), 2);
		searchArea.add(createSearchButtonPanel(), 3);
		return searchArea;
	}

	private JPanel createSearchButtonPanel()
	{
		JPanel buttonPanel = new JPanel();
		search = new JButton("Search");
		enroll = new JButton("Enroll");
		unenroll = new JButton("Unenroll");

		buttonPanel.add(search);
		buttonPanel.add(enroll);
		buttonPanel.add(unenroll);

		return buttonPanel;
	}

	private JPanel createButtonGroup()
	{
		JPanel buttons = new JPanel(new GridLayout(1, 2));
		buttons.setAlignmentX(LEFT_ALIGNMENT);
		id = new JRadioButton("ID");
		lastName = new JRadioButton("Last Name");
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(id);
		buttonGroup.add(lastName);
		id.setSelected(true);

		buttons.add(id);
		buttons.add(lastName);
		return buttons;
	}

	private JTextField createTextField()
	{
		searchField = new JTextField(20);
		return searchField;
	}

	private JPanel createEnrollmentList()
	{
		JPanel enrollList = new JPanel(new BorderLayout());
		// TODO: Fix label font addstatic enrolled student string
		enrollList.add(new JLabel("Enrolled Students"), BorderLayout.NORTH);
		enrollList.add(createEnrolledStudentList(), BorderLayout.CENTER);

		return enrollList;
	}

	private JList<Student> createEnrolledStudentList()
	{
		return enrolledStudentList = new JList<Student>();
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub

	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Testing");
		frame.add(new EnrollmentPage(
				new Course(1010101, "PlaceHolder Text", true)));
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
