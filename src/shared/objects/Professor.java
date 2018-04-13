package shared.objects;

/**
 * Provides a class to represent a professor.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class Professor extends User
{
	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;

	public Professor(int userId, String fName, String lName, String mail,
			String pass)
	{
		super(userId, fName, lName, mail, USER_PROFESSOR, pass);
	}

}
