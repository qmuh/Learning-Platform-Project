package backend.userSession;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import backend.database.Database;
import backend.userSession.helpers.EmailHelper;
import backend.userSession.helpers.FileHelper;
import shared.objects.EmailInfo;
import shared.objects.SendMessage;
import shared.objects.User;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */

public abstract class ClientSession implements Runnable
{
	/**
	 * Connects Client to a server
	 */
	protected Socket socket;

	/**
	 * Used for sending serialized objects
	 */
	protected ObjectOutputStream objectOut;

	/**
	 * Used for receiving serialized objects
	 */
	protected ObjectInputStream objectIn;

	/**
	 * Database used by the server
	 */
	protected Database database;

	/**
	 * Used for sending email
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
			objectOut = new ObjectOutputStream(socket.getOutputStream());
			objectIn = new ObjectInputStream(socket.getInputStream());
			fileHelper = new FileHelper();
			emailHelper = new EmailHelper();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		boolean isRunning = true;

		while (isRunning)
		{
			try
			{
				SendMessage<?> newMessage = (SendMessage<?>) objectIn
						.readObject();
				isRunning = interpretMessage(newMessage);

			} catch (IOException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sets the database for the client session
	 */
	public void setDatabase(Database database)
	{
		this.database = database;
	}

	/**
	 * Handles the email sending
	 * 
	 * @param emailLogin
	 *            The complete email information
	 */
	protected void handleEmail(EmailInfo emailLogin)
	{

		Boolean authenticate = emailHelper.sendEmail(emailLogin);
		try
		{
			objectOut.writeObject(authenticate);
			objectOut.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Used to send a file to the client
	 * 
	 * @param path
	 *            The client being sent to the client
	 */
	protected void sendBackFile(String path)
	{
		byte[] file = fileHelper.receiveFile(path);
		try
		{
			objectOut.writeObject(file);
			objectOut.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Used to send an Object to the client
	 * 
	 * @param message
	 *            The object being sent
	 */
	protected void sendObject(Object message)
	{
		try
		{
			objectOut.writeObject(message);
			objectOut.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public abstract void setUser(User user);

	/**
	 * Interprets the message sent by the client. Returns a boolean to denote
	 * whether the session is still active.
	 *
	 * @param command
	 *            the command to execute
	 * @return true until the client logs off
	 */
	protected abstract boolean interpretMessage(SendMessage<?> command);

	/**
	 * Used for writing data
	 */
	abstract public void write();
}
