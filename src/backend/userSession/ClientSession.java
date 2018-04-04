package backend.userSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import backend.interfaces.Commands;
import backend.database.Database;
import backend.userSession.helper.EmailHelper;
import backend.userSession.helper.FileHelper;
import sharedobjects.SendMessage;

public abstract class ClientSession implements Runnable, Commands
{

	/**
	 * Connects Client to a server
	 */
	Socket mySocket;

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

	abstract void interpretMessage(SendMessage command);

	abstract public void write();


}
