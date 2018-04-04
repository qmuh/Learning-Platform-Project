package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import backend.table.*;

public abstract class ClientSession implements Runnable, Commands
{

	/**
	 * Connects Client to a server
	 */
	protected Socket mySocket;
	
	/**
	 * Used for sending serialized objects
	 */
	protected ObjectOutputStream outputStream;
	
	/**
	 * Used for receiving serialized objects
	 */
	protected ObjectInputStream inputStream;
	
	/**
	 * Database used by the server
	 */
	protected Database myDatabase;
	
	/**
	 * Used for sending emails
	 */
	protected EmailHelper myEmailHelper;
	
	/**
	 * Used to deal with files
	 */
	protected FileHelper myFileHelper;
		
	
	public ClientSession(Socket socket)
	{
		mySocket = socket;
		try
		{
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException e)
		{
			
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Sets the database for the client session
	 */
	public void setDatabase(Database toAdd) 
	{
		myDatabase = toAdd;
	}

	abstract void interpretMessage(String command);
	
	abstract public void write();

}
