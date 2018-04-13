package shared.objects;

import java.io.Serializable;
import java.util.Vector;

/**
 * Provides a class that stores an email to be sent.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class EmailInfo implements Serializable
{

	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The sender's e-mail (g-mail).
	 */
	private String email;

	/**
	 * The sender's e-mail password (g-mail).
	 */
	private String password;

	/**
	 * The vector that contains recipient e-mails.
	 */
	private Vector<String> recipientList;

	/**
	 * The subject line of the email.
	 */
	private String subject;

	/**
	 * The content of the email.
	 */
	private String emailContent;

	/**
	 * Creates a new email object with the specified g-mail email and password.
	 * 
	 * @param mail the email of the g-mail account
	 * @param pass the password of the g-mail account
	 */
	public EmailInfo(String mail, String pass)
	{
		email = mail;
		password = pass;
		recipientList = new Vector<String>();
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

	public void addRecipient(String newRecipient)
	{
		recipientList.addElement(newRecipient);
	}

	public Vector<String> getRecipients()
	{
		return recipientList;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setContent(String content)
	{
		emailContent = content;
	}

	public String getContent()
	{
		return emailContent;
	}

	public void setFromEmail(String fromEmail)
	{
		email = fromEmail;
	}

	public void setFromEmailPassword(String pass)
	{
		password = pass;
	}
}
