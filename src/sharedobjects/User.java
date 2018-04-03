package sharedobjects;

import java.io.Serializable;

public abstract class User implements Serializable
{

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
}
