package backend.userSession;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import backend.interfaces.Commands;
import backend.Database;
import backend.userSession.helper.EmailHelper;
import backend.userSession.helper.FileHelper;

public abstract class ClientSession implements Runnable, Commands
{

	/**
	 * Connects Client to a server
	 */
	protected Socket mySocket;
	
	/**
	 * Reads from socket
	 */
	protected BufferedReader reader;
	
	/**
	 * Writes to the socket
	 */
	protected PrintWriter writer;
	
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
		
	
	/**
	 * Sets the database for the client session
	 */
	public void setDatabase() 
	{
		
	}

	abstract void interpretMessage(String command);
	
	abstract public void write();

}
