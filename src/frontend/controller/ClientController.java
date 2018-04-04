package frontend.controller;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController
{

	private Socket mySocket;
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private BufferedReader readIn;
	private PrintWriter output;
	
	public void connectToServer(Socket toConnect) 
	{
		
	}
}
