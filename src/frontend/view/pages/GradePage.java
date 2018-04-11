package frontend.view.pages;


import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.items.GradeItem;
import shared.objects.Course;
import shared.objects.Grade;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class GradePage extends CoursePage<GradeItem, Grade>
{
	private static final long serialVersionUID = 1L;

	public GradePage(Course course)
	{
		super(course);
		this.setName(GRADES_PAGE + course.getId());
		setPageTitle("Grades Page");
		bodyCenter.add(createDiscussionPage(), BorderLayout.CENTER);
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub

	}
	
	private WButton sendButton;
	private JTextArea sendArea;
	
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
		sendArea = new JTextArea(3, 200);
		sendArea.setFont(TEXT_FONT);
		sendArea.setBorder(new JTextField().getBorder());
		sendButton = new WButton();
		try {
			BufferedImage image = ImageIO.read(new File("send.png"));
			ImageIcon icon = new ImageIcon(image);
			sendButton.setIcon(icon);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
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
