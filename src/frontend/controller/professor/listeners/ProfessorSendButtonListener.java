package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import frontend.controller.Client;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.compose.ComposeEmailPage;
import frontend.view.pages.compose.ComposeEmailPageProfessor;
import shared.interfaces.ProfessorCommands;
import shared.objects.Course;
import shared.objects.EmailInfo;
import shared.objects.SendMessage;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018 Sends an email
 */
public class ProfessorSendButtonListener
		implements ActionListener, ProfessorCommands, WondrisInfo
{
	/**
	 * The course this is associated
	 */
	private Course course;

	/**
	 * The client which is used for communication
	 */
	private Client client;

	/**
	 * The composed email page which holds this button
	 */
	private ComposeEmailPageProfessor emailPage;

	/**
	 * The constructor for this
	 * 
	 * @param client
	 *            Used for communication
	 * @param course
	 *            The course this is used for
	 * @param email
	 *            The email page which holds the email information
	 */
	public ProfessorSendButtonListener(Client client, Course course,
			ComposeEmailPageProfessor email)
	{
		System.out.println("SEND BUTTON EMAIL PROF CONSTRUCTED");
		this.client = client;
		this.course = course;
		this.emailPage = email;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("SEND BUTTON EMAIL PROF CALLED");
		EmailInfo mailInfo = emailPage.getEmailInfo();
		JTextField gEmail = new JTextField(50);
		JPasswordField password = new JPasswordField(50);
		password.setEchoChar(PASSWORD_ECHO_CHAR);
		Object toDisplay[] =
			{ "GMail Email: ", gEmail, "GMail Password: ", password };
		int response = JOptionPane.showConfirmDialog(null, toDisplay,
				"Insert real email information", JOptionPane.OK_CANCEL_OPTION);

		if (response == JOptionPane.OK_OPTION)
		{
			if (gEmail.getText().length() == 0
					|| password.getPassword().length == 0)
			{
				JOptionPane.showMessageDialog(null,
						"Either Password or Email was incorrect");

			} else
			{
				mailInfo.setFromEmail(gEmail.getText());
				mailInfo.setFromEmailPassword(new String(password.getPassword()));
				try
				{
					Boolean h = (Boolean) client.sendMessage(
							new SendMessage<EmailInfo>(mailInfo, CMD_EMAIL));
					if (h == true)
					{
						JOptionPane.showMessageDialog(null, "Email Sent");
					} else
					{
						JOptionPane.showMessageDialog(null,
								"Email not sent, error in login info or email addresses");
					}
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}
}