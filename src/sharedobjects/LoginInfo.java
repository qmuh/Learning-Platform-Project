package sharedobjects;

import java.io.Serializable;

public class LoginInfo implements Serializable
{
	
	/**
	 * The current version of this class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The integer id for the person logging in
	 */
	private int username;
	
	/**
	 * The password for the user
	 */
	private String password;
}
