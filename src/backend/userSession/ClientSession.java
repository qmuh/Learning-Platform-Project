package backend.userSession;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import backend.interfaces.DatabaseCommands;
import backend.userSession.helpers.EmailHelper;
import backend.userSession.helpers.FileHelper;
import backend.database.Database;
import sharedobjects.SendMessage;

public abstract class ClientSession implements Runnable, DatabaseCommands
{
	/**
	 * Connects Client to a server
	 */
	protected Socket socket;

	/**
	 * Used for sending serialized objects
	 */
	protected ObjectOutputStream objectOutputStream;

	/**
	 * Used for receiving serialized objects
	 */
	protected ObjectInputStream objectInputStream;

	/**
	 * Database used by the server
	 */
	protected Database database;
	
	/**
	 * Used for sending emails
	 */
	protected EmailHelper emailHelper;

	/**
	 * Used to deal with files
	 */
	protected FileHelper fileHelper;

	public ClientSession(Socket socket)
	{
		this.socket = socket;
		try
		{
			objectOutputStream = new ObjectOutputStream(
					socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());

			fileHelper = new FileHelper();
			emailHelper = new EmailHelper();

		} catch (IOException e)
		{

			e.printStackTrace();
		}
	}

	/**
	 * Sets the database for the client session
	 */
	public void setDatabase(Database database)
	{
		this.database = database;
	}

	abstract void interpretMessage(SendMessage command);

	abstract public void write();

}
