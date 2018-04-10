package backend.userSession.helpers;

import java.util.Properties;
import java.util.Vector;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import shared.objects.EmailInfo;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */

public class EmailHelper
{

	public Boolean sendEmail(EmailInfo emailLogin)
	{
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true"); // Using TLS
		properties.put("mail.smtp.auth", "true"); // Authenticate
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Using Gmail Account
		properties.put("mail.smtp.port", "587"); // TLS uses port 587
		
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator(){
				 protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication(emailLogin.getEmail() , emailLogin.getPassword());
				 }
				});
		try
		{
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailLogin.getEmail()));
			
			for (int i = 0; i < emailLogin.getRecipients().size(); i++)
			{
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress( emailLogin.getRecipients().get(i)));
			}
			
			message.setSubject((emailLogin.getSubject()));
			message.setText(emailLogin.getContent());
			Transport.send(message); // Send the Email Message
			
		} catch (MessagingException e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

}
