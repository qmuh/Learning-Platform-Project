package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionListener;

import frontend.interfaces.Colours;
import frontend.interfaces.WondrisInfo;
import javafx.scene.layout.Border;
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
	
	public void setEnrolledList(Vector<Student> enrollList)
	{
		enrolledStudentList.clearSelection();
		enrolledStudentList.setListData(enrollList);
		
	}
	
	private JPanel createEnrollmentPanel()
	{
		JPanel enrollmentPanel = new JPanel(new GridLayout(1, 2));
		enrollmentPanel.add(createEnrollmentList(), 0);
		enrollmentPanel.add(createSearchPanel(), 1);
		return enrollmentPanel;
	}

	private JPanel createSearchPanel()
	{
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BorderLayout());
		searchPanel.add(createSearchArea(), BorderLayout.NORTH);
		searchPanel.add(createSearchResults(), BorderLayout.CENTER);
		return searchPanel;
	}

	private JPanel createSearchResults()
	{
		JPanel searchResults = new JPanel(new BorderLayout());
		searchResults.setBorder(new EmptyBorder(20, 20, 20, 20));
		searchResults.add(new JLabel("Search Results"), BorderLayout.NORTH);
		studentSearchResults = new JList<Student>();
		searchResults.add(studentSearchResults, BorderLayout.CENTER);
		return searchResults;
	}

	private JPanel createSearchArea()
	{
		JPanel searchArea = new JPanel();
		searchArea.setLayout(new BoxLayout(searchArea, BoxLayout.Y_AXIS));
		searchArea.add(createButtonGroup());
		searchArea.add(createTextField());
		searchArea.add(createSearchButtonPanel());
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
		JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
//		buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
		// BORDER
		buttonPanel.setBorder(BorderFactory.createTitledBorder(
				new MatteBorder(1, 1, 1, 1, Colours.CONTRAST_COLOR), "Search"));

		id = new JRadioButton("ID");
		lastName = new JRadioButton("Last Name");
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(id);
		buttonGroup.add(lastName);
		id.setSelected(true);

		buttonPanel.add(id);
		buttonPanel.add(lastName);
		return buttonPanel;
	}

	private JPanel createTextField()
	{
		JPanel textFieldPanel = new JPanel(new GridLayout(1, 1));
		textFieldPanel.setBorder(new EmptyBorder(10, 40, 10, 40));
		searchField = new JTextField();
		textFieldPanel.add(searchField);
		return textFieldPanel;
	}

	private JPanel createEnrollmentList()
	{
		JPanel enrollList = new JPanel(new BorderLayout());
		// TODO: Fix label font addstatic enrolled student string
		enrollList.add(new JLabel("Enrolled Students"), BorderLayout.NORTH);
		enrollList.add(createEnrolledStudentList(), BorderLayout.CENTER);

		return enrollList;
	}

	private JPanel createEnrolledStudentList()
	{
		JPanel enrolledPanel = new JPanel(new GridLayout(1, 1));
		enrolledPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		enrolledStudentList = new JList<Student>();
		enrolledPanel.add(enrolledStudentList);
		return enrolledPanel;
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
