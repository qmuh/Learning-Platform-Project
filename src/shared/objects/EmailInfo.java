package shared.objects;

import java.io.Serializable;
import java.util.Vector;

/**
 * Used to autenthicate emails with a gmail server
 *
 */
public class EmailInfo implements Serializable
{

	private String email;

	private String password;

	private Vector<String> recipientList;

	private String subject;

	private String emailContent;

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

}
