package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import frontend.view.pages.components.customSwing.WButton;
import shared.objects.Course;
import shared.objects.Student;

public class MyEmailsPage extends CoursePage implements PageNames
{

	private static final long serialVersionUID = 1L;
	private JList<Student> theEmails;
	private JButton replyButton;
	private JTextField fromField, subjectField;
	private JTextArea emailArea;

	public MyEmailsPage(Course course)
	{
		super(course);
		// this.setName(MY_EMAIL_PAGE + course.getId());
		setPageTitle("My Emails");
		bodyCenter.add(createMyEmailsPage(), BorderLayout.CENTER);
	}

	private JPanel createMyEmailsPage()
	{
		JPanel myEmailsPanel = new JPanel(new BorderLayout());
		myEmailsPanel.add(createEmailList(), BorderLayout.WEST);
		myEmailsPanel.add(createTheEmail(), BorderLayout.CENTER);
		return myEmailsPanel;
	}

	private JPanel createEmailList()
	{
		JPanel emailList = new JPanel(new BorderLayout());
		emailList.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		theEmails = new JList<>();
		JScrollPane scrollPane = new JScrollPane(theEmails);
		emailList.add(scrollPane, BorderLayout.CENTER);
		return emailList;
	}

	private JPanel createTheEmail()
	{
		JPanel theEmail = new JPanel(new BorderLayout());
		theEmail.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		theEmail.add(createEmailFields(), BorderLayout.NORTH);
		theEmail.add(createEmailArea(), BorderLayout.CENTER);
		theEmail.add(createButtonPanel(), BorderLayout.SOUTH);
		return theEmail;
	}

	private JPanel createEmailFields()
	{
		JPanel emailFieldPanel = new JPanel(new BorderLayout());
		JPanel textFields = new JPanel(new GridLayout(2, 1));
		fromField = new JTextField(20);
		subjectField = new JTextField(20);
		fromField.setFont(TEXT_FONT);
		subjectField.setFont(TEXT_FONT);
		fromField.setEditable(false);
		subjectField.setEditable(false);
		textFields.add(createTextField("From: ", fromField), 0);
		textFields.add(createTextField("Subject: ", subjectField), 1);
		emailFieldPanel.add(textFields, BorderLayout.WEST);
		return emailFieldPanel;
	}

	private JPanel createTextField(String s, JTextField field)
	{
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		textFieldPanel
				.setLayout(new BoxLayout(textFieldPanel, BoxLayout.X_AXIS));
		textFieldPanel.add(createLabel(s, SUB_TITLE_FONT));
		textFieldPanel.add(field);
		return textFieldPanel;
	}

	private JPanel createEmailArea()
	{
		JPanel emailAreaPanel = new JPanel(new BorderLayout());
		emailAreaPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		emailArea = new JTextArea();
		emailArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		emailArea.setFont(TEXT_FONT);
		emailArea.setEditable(false);
		emailAreaPanel.add(emailArea, BorderLayout.CENTER);
		return emailAreaPanel;
	}

	private JPanel createButtonPanel()
	{
		JPanel buttonPanel = new JPanel(new BorderLayout());
		replyButton = new WButton("Reply");
		buttonPanel.add(replyButton, BorderLayout.EAST);
		return buttonPanel;
	}

}
