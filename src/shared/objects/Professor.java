package shared.objects;

public class Professor extends User
{

	private static final long serialVersionUID = 1L;

	public Professor(int userId, String fName, String lName, String mail,
			String pass)
	{
		super(userId, fName, lName, mail, USER_PROFESSOR, pass);
		// TODO Auto-generated constructor stub
	}

}
