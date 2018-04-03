package sharedobjects;

import java.io.Serializable;

public class LoginInfo implements Serializable
{
	
	/**
	 * The integer id for the person logging in
	 */
	private int username;
	
	/**
	 * The password for the user
	 */
	private String password;
}
