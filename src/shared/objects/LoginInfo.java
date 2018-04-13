package shared.objects;

import java.io.Serializable;

/**
 * Provides a class to represent a login info object.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class LoginInfo implements Serializable
{

	/**
	 * The current version of this class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The integer id for the person logging in.
	 */
	private int username;

	/**
	 * The password for the user
	 */
	private String password;

	public LoginInfo(int user, String pass)
	{
		username = user;
		password = pass;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public int getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

}
