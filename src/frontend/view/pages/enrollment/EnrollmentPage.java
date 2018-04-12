package frontend.view.pages.enrollment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.course.CoursePage;
import shared.objects.Course;
import shared.objects.Student;
import shared.objects.User;

public class EnrollmentPage extends CoursePage implements WondrisInfo
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JList<Student> enrolledStudentList;
	private JList<Student> studentSearchResults;

	private JRadioButton id;
	private JRadioButton lastName;
	private WButton search;
	private WButton enrollment;
	private JTextField searchField;

	public EnrollmentPage(Course course, User user)
	{
		super(course, user);
		this.setName(ENROLLMENT_PAGE + course.getId());
		setPageTitle("Enrollments");
		bodyCenter.add(createEnrollmentPanel(), BorderLayout.CENTER);
	}

	public boolean isSearchById()
	{
		return id.isSelected();
	}

	public boolean isSearchByLastName()
	{
		return lastName.isSelected();
	}

	public WButton getEnrollmentButton()
	{
		return enrollment;
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

	public void setEnrollmentPageButtonListener(ActionListener listener)
	{
		enrollment.addActionListener(listener);
	}

	public void setEnrollmentListListener(ListSelectionListener listener)
	{
		studentSearchResults.addListSelectionListener(listener);
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
		searchResults.add(createLabel("Search Results", TEXT_FONT),
				BorderLayout.NORTH);
		studentSearchResults = new JList<Student>();
		studentSearchResults.setFont(TEXT_FONT);
		studentSearchResults
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(studentSearchResults);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		searchResults.add(scrollPane, BorderLayout.CENTER);
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
		search = new WButton("Search");
		enrollment = new WButton("Enroll");
		enrollment.setEnabled(false);

		buttonPanel.add(search);
		buttonPanel.add(enrollment);

		return buttonPanel;
	}

	private JPanel createButtonGroup()
	{
		JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
		// buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
		// BORDER
		MatteBorder matteBorder = new MatteBorder(1, 1, 1, 1,
				ColourPalette.CONTRAST_COLOR);
		TitledBorder titledBorder = BorderFactory
				.createTitledBorder(matteBorder);
		titledBorder.setTitle("Search");
		titledBorder.setTitleFont(TEXT_FONT);
		buttonPanel.setBorder(titledBorder);

		id = new JRadioButton("ID");
		lastName = new JRadioButton("Last Name");
		id.setFont(TEXT_FONT);
		lastName.setFont(TEXT_FONT);
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
		searchField = new JTextField(20);
		searchField.setFont(TEXT_FONT);
		textFieldPanel.add(searchField);
		return textFieldPanel;
	}

	private JPanel createEnrollmentList()
	{
		JPanel enrollList = new JPanel(new BorderLayout());
		// TODO: Fix label font addstatic enrolled student string
		enrollList.add(createLabel("Enrolled Students", TEXT_FONT),
				BorderLayout.NORTH);
		enrollList.add(createEnrolledStudentList(), BorderLayout.CENTER);

		return enrollList;
	}

	private JScrollPane createEnrolledStudentList()
	{
		enrolledStudentList = new JList<Student>();
		enrolledStudentList.setEnabled(false);
		enrolledStudentList.setFont(TEXT_FONT);
		JScrollPane scrollPane = new JScrollPane(enrolledStudentList);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		return scrollPane;
	}

	
}
