package backend.userSession;

import java.net.Socket;

import shared.objects.SendMessage;
import shared.objects.Student;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */

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
	boolean interpretMessage(SendMessage command)
	{
		String interpreter[] = command.getCommand().split(" ");

		if (interpreter[0].equals("INSERT"))
		{
			handleInsert(interpreter, command.getContents());
		}

		else if (interpreter[0].equals("REMOVE"))
		{

		}

		else if (interpreter[0].equals("RECEIVE"))
		{
			handleRecieve(interpreter, command.getContents());
		}

		else if (interpreter[0].equals("MODIFY"))
		{
			handleModify(interpreter, command.getContents());
		} else if (interpreter[0].equals("LOGOUT"))
		{
			return false;
		}
		return true;

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
