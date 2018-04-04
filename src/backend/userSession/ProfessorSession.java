package backend.userSession;

import java.io.IOException;
import java.net.Socket;

import sharedobjects.SendMessage;


public class ProfessorSession extends ClientSession
{

	public ProfessorSession(Socket socket)
	{
		super(socket);
	}

	@Override
	public void run()
	{
		boolean isRunning = true;

		while (isRunning)
		{
			try
			{
				SendMessage newMessage = (SendMessage)inputStream.readObject();
				interpretMessage(newMessage);
				

			} catch (IOException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}


		
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
