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
	Socket mySocket;
	
	/**
	 * Reads from socket
	 */
	BufferedReader reader;
	
	/**
	 * Writes to the socket
	 */
	PrintWriter writer;
	
	/**
	 * Used for sending serialized objects
	 */
	ObjectOutputStream outputStream;
	
	/**
	 * Used for receiving serialized objects
	 */
	ObjectInputStream inputStream;
	
	/**
	 * Database used by the server
	 */
	Database myDatabase;
	
	/**
	 * Used for sending emails
	 */
	EmailHelper myEmailHelper;
	
	/**
	 * Used to deal with files
	 */
	FileHelper myFileHelper;
		
	
	/**
	 * Sets the database for the client session
	 */
	public void setDatabase() 
	{
		
	}

	abstract void interpretMessage(String command);
	
	abstract public void write();

}
