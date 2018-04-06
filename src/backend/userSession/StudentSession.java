package backend.userSession;

import java.io.IOException;
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
	public void write()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	void interpretMessage(SendMessage command)
	{
		String interpreter[] = command.getCommand().split(" ");
		
		if(interpreter[0].equals("INSERT"))
		{
			handleInsert(interpreter,command.getmessageObject());
		}
		
		else if(interpreter[0].equals("REMOVE"))
		{
			
		}
		
		else if(interpreter[0].equals("RECEIVE"))
		{
			handleRecieve(interpreter, command.getmessageObject());
		}
		
		else if(interpreter[0].equals("MODIFY"))
		{
			handleModify(interpreter, command.getmessageObject());
		}
		
		
	}

	private void handleModify(String[] interpreter, Object getmessageObject)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleRecieve(String[] interpreter, Object getmessageObject)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleInsert(String[] interpreter, Object getmessageObject)
	{
		// TODO Auto-generated method stub
		
	}
}
