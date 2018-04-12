package frontend.controller.student.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import frontend.controller.Client;
import frontend.view.pages.compose.ComposeEmailPageStudent;
import shared.interfaces.StudentCommands;
import shared.objects.Course;
import shared.objects.EmailInfo;
import shared.objects.SendMessage;

import frontend.controller.Client;
import frontend.view.pages.compose.ComposeEmailPageStudent;
import shared.objects.Course;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018 Sends an email
 */
public class StudentSendButtonListener implements ActionListener, StudentCommands
{

	private ComposeEmailPageStudent emailPage;

	private Client client;

	private Course myCourse;

	public StudentSendButtonListener(Course course, ComposeEmailPageStudent myPage, Client client)
	{
		myCourse = course;
		this.client = client;
		emailPage = myPage;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		EmailInfo mailInfo = emailPage.getEmailInfo();
		JTextField gEmail = new JTextField(50);
		JTextField password = new JTextField(50);
		Object toDisplay[] = {"GMail Email: ", gEmail, "GMail Password: ", password};
		int response =JOptionPane.showConfirmDialog(null, toDisplay, "Insert real email information", JOptionPane.OK_CANCEL_OPTION);

		if( response == JOptionPane.OK_OPTION)
		{
			if(gEmail.getText().length() == 0 || password.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(null,"Either Password or Email was incoorect");
			}

			else {
			mailInfo.setFromEmail(gEmail.getText());
			mailInfo.setFromEmailPassword(password.getText());
			try
			{
				Boolean h = (Boolean) client.sendMessage(new SendMessage<EmailInfo>(mailInfo, CMD_EMAIL));
				if(h == true)
				{
					JOptionPane.showMessageDialog(null,"Email Sent");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Email not sent, error in login info or email addresses");
				}
			} catch (IOException e1)
			{
				e1.printStackTrace();

			}
		}

	}
}
}
