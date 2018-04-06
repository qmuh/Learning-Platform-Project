package shared.objects;

public class Student extends User
{

	private static final long serialVersionUID = 1L;

	public Student(int userId, String fName, String lName, String mail, String pass)
	{
		super(userId, fName, lName, mail, USER_STUDENT, pass);
	}

	@Override
	public String toString()
	{
		return super.toString();
	}
	
	
	
	
}
