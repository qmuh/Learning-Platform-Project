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
import frontend.view.pages.items.student.StudentItem;
import shared.objects.Course;
import shared.objects.EmailInfo;
import shared.objects.Student;

abstract public class ComposeEmailPage extends CoursePage<StudentItem, Student>
{
	private static final long serialVersionUID = 1L;
	private JTextField toField, subjectField;
	private JTextArea emailArea;
	protected WButton sendButton;
	protected JPanel sendButtonPanel;

	public ComposeEmailPage(Course course)
	{
		super(course);
		this.setName(COMPOSE_EMAIL_PAGE + course.getId());
		this.setPageTitle("Compose Email");
		bodyCenter.add(createComposeEmailCenter(), BorderLayout.CENTER);
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

	public WButton getSendButton()
	{
		return sendButton;
	}

	public void setSendButtonListener(ActionListener listener)
	{
		sendButton.addActionListener(listener);
	}

	private JPanel createComposeEmailCenter()
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
		emailComponents.add(createComposeEmailPanel(), BorderLayout.CENTER);
		return emailComponents;
	}
	
	private JPanel createComposeEmailPanel()
	{

		JPanel emailComponents = new JPanel(new BorderLayout());
		emailComponents.add(createEmailFields(), BorderLayout.NORTH);
		emailComponents.add(createEmailTextArea(), BorderLayout.CENTER);
		return emailComponents;
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

	protected JPanel createButtonPanel()
	{
		sendButtonPanel = new JPanel(new BorderLayout());
		sendButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		try
		{
			sendButton = new WButton(ImageIO.read(new File(DIR_GRAPHICS + "send.png")));
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		sendButtonPanel.add(sendButton, BorderLayout.EAST);
		return sendButtonPanel;
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
