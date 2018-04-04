package backend.userSession;

import java.net.Socket;

public class StudentSession extends ClientSession
{

	public StudentSession(Socket socket)
	{
		super(socket);
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
	void interpretMessage(String command)
	{
		// TODO Auto-generated method stub
		
	}

}
