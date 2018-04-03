package frontend;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController
{

	Socket mySocket;
	ObjectInputStream objectIn;
	ObjectOutputStream objectOut;
	BufferedReader readIn;
	PrintWriter output;
	
	public void connectToServer(Socket toConnect) 
	{
		
	}
}
