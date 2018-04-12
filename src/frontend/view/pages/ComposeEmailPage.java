package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import frontend.view.pages.items.StudentItem;
import shared.objects.Course;
import shared.objects.EmailInfo;
import shared.objects.Student;

public class ComposeEmailPage extends CoursePage<StudentItem, Student>
{
	private static final long serialVersionUID = 1L;
	private JTextField toField, subjectField;
	private JTextArea emailArea;
	private WButton sendToAllButton, sendButton, addToEmailButton;
	private JList<Student> studentList;

	public ComposeEmailPage(Course course)
	{
		super(course);
		this.setName(COMPOSE_EMAIL_PAGE + course.getId());
		this.setPageTitle("Compose Email");
		bodyCenter.add(createComposeEmailPanel(), BorderLayout.CENTER);
		toField.setText("");
		toField.setEditable(false);
	}

	public JTextField getToField()
	{
		return toField;
	}

	public JTextField getSubjectField()
	{
		return subjectField;
	}

	public JTextArea getEmailArea()
	{
		return emailArea;
	}

	public WButton getSendToAllButton()
	{
		return sendToAllButton;
	}

	public WButton getSendButton()
	{
		return sendButton;
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

	public void setSendToAllButtonListener(ActionListener listener)
	{
		sendToAllButton.addActionListener(listener);
	}

	public void setSendButtonListener(ActionListener listener)
	{
		sendButton.addActionListener(listener);
	}

	public void setAddToEmailButtonListener(ActionListener listener)
	{
		addToEmailButton.addActionListener(listener);
	}

	public void setStudentList(Vector<Student> enrollList)
	{
		studentList.clearSelection();
		studentList.setListData(enrollList);
	}

	private JPanel createComposeEmailPanel()
	{
		JPanel composeEmailPanel = new JPanel(new BorderLayout());
		composeEmailPanel
				.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		composeEmailPanel.add(createEmailComponents());
		return composeEmailPanel;
	}

	private JPanel createEmailComponents()
	{
		JPanel emailComponents = new JPanel(new BorderLayout());

		JPanel emailComponentsLeft = new JPanel(new BorderLayout());

		emailComponentsLeft.add(createEmailFields(), BorderLayout.NORTH);
		emailComponentsLeft.add(createEmailTextArea(), BorderLayout.CENTER);

		JPanel emailComponentsRight = new JPanel(new BorderLayout());
		emailComponentsRight.add(createStudentListHeader(), BorderLayout.NORTH);
		emailComponentsRight.add(createStudentList(), BorderLayout.CENTER);

		emailComponents.add(emailComponentsLeft, BorderLayout.CENTER);
		emailComponents.add(emailComponentsRight, BorderLayout.EAST);
		return emailComponents;
	}

	private JPanel createStudentListHeader()
	{
		JPanel studentHeader = new JPanel();
		studentHeader.add(new WLabel("Enrolled Students:", SUB_TITLE_FONT));
		return studentHeader;
	}

	private JPanel createEmailFields()
	{
		JPanel emailFieldPanel = new JPanel();

		JPanel sendReceivePanel = new JPanel(new BorderLayout());

		JPanel textFieldPanel = new JPanel(new GridLayout(2, 1));
		toField = new JTextField(20);
		subjectField = new JTextField(20);
		toField.setFont(TEXT_FONT);
		subjectField.setFont(TEXT_FONT);

		JPanel textFieldLabelPanel = new JPanel(new GridLayout(2, 1));
		textFieldLabelPanel
				.add(new WLabel("To:", SUB_TITLE_FONT, WLabel.RIGHT), 0);
		textFieldLabelPanel
				.add(new WLabel("Subject:", SUB_TITLE_FONT, WLabel.RIGHT), 1);

		textFieldPanel.add(toField, 0);
		textFieldPanel.add(subjectField, 1);

		sendReceivePanel.add(textFieldLabelPanel, BorderLayout.WEST);
		sendReceivePanel.add(textFieldPanel, BorderLayout.CENTER);

		emailFieldPanel.add(sendReceivePanel);
		return emailFieldPanel;
	}

	private JPanel createEmailTextArea()
	{
		JPanel emailTextPanel = new JPanel(new BorderLayout());
		emailArea = new JTextArea();
		emailArea.setBorder(new JTextField().getBorder());
		emailArea.setFont(TEXT_FONT);
		emailTextPanel.add(emailArea, BorderLayout.CENTER);
		emailTextPanel.add(createButtonPanel(), BorderLayout.SOUTH);
		return emailTextPanel;
	}

	private JPanel createButtonPanel()
	{
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		sendToAllButton = new WButton("Add All Enrolled Students");
		sendButton = new WButton();
		try
		{
			BufferedImage image = ImageIO.read(new File("send.png"));
			ImageIcon icon = new ImageIcon(image);
			sendButton.setIcon(icon);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		buttonPanel.add(sendToAllButton, BorderLayout.WEST);
		buttonPanel.add(sendButton, BorderLayout.EAST);
		return buttonPanel;
	}

	private JPanel createStudentList()
	{
		JPanel studentListPanel = new JPanel(new BorderLayout());
		studentListPanel
				.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		studentList = new JList<Student>();
		studentList.setFont(TEXT_FONT);
		studentList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane scrollPane = new JScrollPane(studentList);
		scrollPane.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		studentListPanel.add(scrollPane, BorderLayout.CENTER);
		JPanel theButton = new JPanel(new BorderLayout());
		theButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		addToEmailButton = new WButton("Add to Email");
		theButton.add(addToEmailButton, BorderLayout.EAST);
		studentListPanel.add(theButton, BorderLayout.SOUTH);
		return studentListPanel;
	}

	public void appendEmail(String add)
	{
		String head = toField.getText();

		if (head.equals(""))
		{
			toField.setText(add);

		} else
		{
			head = head + "," + add;
			toField.setText(head);
		}
	}

	public void clearEmail()
	{
		toField.setText("");

	}

	public EmailInfo getEmailInfo()
	{
		String emails[] = toField.getText().split(",");
		EmailInfo composedEmail = new EmailInfo(null, null);

		for (int i = 0; i < emails.length; i++)
		{
			composedEmail.addRecipient(emails[i]);
		}

		composedEmail.setContent(emailArea.getText());
		composedEmail.setSubject(subjectField.getText());

		return composedEmail;

	}

}
