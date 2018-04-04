package backend.userSession;

import java.net.Socket;

import sharedobjects.SendMessage;
import sharedobjects.Student;

public class StudentSession extends ClientSession
{

	private Student user;
	public StudentSession(Socket socket)
	{
		super(socket);
	}

	public void setStudent(Student toSet)
	{
		user = toSet;
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	void interpretMessage(SendMessage command)
	{
		// TODO Auto-generated method stub
		
	}

	

}
