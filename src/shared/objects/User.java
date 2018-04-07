package shared.objects;

import java.io.Serializable;

import shared.interfaces.UserInfo;

public abstract class User implements Serializable, UserInfo
{
	private static final long serialVersionUID = 1L;

	/**
	 * The id for the user
	 */
	private int id;

	/**
	 * The first name of the user
	 */
	private String firstName;

	/**
	 * The last name of the user
	 */
	private String lastName;

	/**
	 * The email of the User
	 */
	private String email;

	/**
	 * The type of the User
	 */
	private Character userType;

	/**
	 * The password of the user
	 */
	private String password;

	public User(int userId, String fName, String lName, String mail,
			Character type, String pass)
	{
		id = userId;
		firstName = fName;
		lastName = lName;
		email = mail;
		userType = type;
		password = pass;
	}

	public int getId()
	{
		return id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public Character getUserType()
	{
		return userType;
	}

	public String getPassword()
	{
		return password;
	}

	@Override
	public String toString()
	{
		return "ID: " + id + " " + firstName + " " + lastName;
	}

}
