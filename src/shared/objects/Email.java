package shared.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Email implements Serializable
{

	/**
	 * The person sending the String
	 */
	private String from;

	/**
	 * How many people the email is going too
	 */
	private ArrayList<String> to;

	/**
	 * The subject for the email
	 */
	private String subject;

	/**
	 * The content???
	 */
	private String context;
}
