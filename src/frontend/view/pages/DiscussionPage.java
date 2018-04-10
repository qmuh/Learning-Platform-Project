package frontend.view.pages;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.items.CourseItem;
import shared.objects.Course;

public class DiscussionPage extends CoursePage<CourseItem, Course>
{

	private static final long serialVersionUID = 1L;
	private WButton sendButton;
	private JTextArea sendArea;

	public DiscussionPage(Course course)
	{
		super(course);
		this.setName(DISCUSSION_PAGE + course.getId());
		setPageTitle("Discussion");
		bodyCenter.add(createDiscussionPage(), BorderLayout.CENTER);
	}

	private JPanel createDiscussionPage()
	{
		JPanel myEmailsPanel = new JPanel(new BorderLayout());
		myEmailsPanel.add(createDiscussionArea(), BorderLayout.CENTER);
		myEmailsPanel.add(createSendField(), BorderLayout.SOUTH);
		return myEmailsPanel;
	}

	private JPanel createSendField()
	{
		JPanel sendFieldPanel = new JPanel(new BorderLayout());
		sendArea = new JTextArea(5, 200);
		sendArea.setFont(TEXT_FONT);
		sendArea.setBorder(new JTextField().getBorder());
		sendButton = new WButton("Send");
		sendFieldPanel.add(sendArea, BorderLayout.CENTER);
		sendFieldPanel.add(sendButton, BorderLayout.EAST);
		return sendFieldPanel;
	}

	private JPanel createDiscussionArea()
	{
		// Note: The final product will not be a JTextArea
		// TODO: Change from JTextArea to itemDisplay (BoxList)
		JPanel theChat = new JPanel(new BorderLayout());
		theChat.add(itemDisplay);
		theChat.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		JTextArea sampleTextArea = new JTextArea();
		sampleTextArea.setBorder(new JTextField().getBorder());
		theChat.add(sampleTextArea, BorderLayout.CENTER);
		return theChat;
	}

}
