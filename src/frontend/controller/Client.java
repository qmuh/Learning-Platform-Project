package frontend.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.objects.SendMessage;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */

public class Client
{
	/**
	 * Used to connect to the server
	 */
	private Socket mySocket;

	/**
	 * Used to input objects
	 */
	private ObjectInputStream objectIn;

	/**
	 * Used to output objects
	 */
	private ObjectOutputStream objectOut;

	/**
	 * Connects the server with client
	 * 
	 * @param toConnect
	 *            The connecting socket
	 */
	public void connectToServer(Socket toConnect)
	{
		try
		{
			mySocket = toConnect;
			this.objectIn = new ObjectInputStream(mySocket.getInputStream());
			this.objectOut = new ObjectOutputStream(mySocket.getOutputStream());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Sends a message to the server and returns an object
	 * 
	 * @param toSend
	 *            The SendMessage object that is sent to the server
	 * @return And object is returned, the type depends on command sent
	 * @throws IOException
	 */
	public Object sendMessage(SendMessage<?> toSend) throws IOException
	{

		objectOut.writeObject(toSend);
		objectOut.flush();

		try
		{
			return objectIn.readObject();

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Sends only a message, returns nothing as this command expects no return
	 * 
	 * @param toSend
	 *            The SendMessage being sent to the server
	 * @throws IOException
	 */
	public void onlySendMessage(SendMessage<?> toSend) throws IOException
	{
		objectOut.writeObject(toSend);
		objectOut.flush();
	}

	/**
	 * Gets the socket
	 * 
	 * @return The Socket
	 */
	public Socket getMySocket()
	{
		return mySocket;
	}

	/**
	 * Gets the object input stream
	 * 
	 * @return The object input stream
	 */
	public ObjectInputStream getObjectIn()
	{
		return objectIn;
	}

	/**
	 * Gets the object the output stream
	 * 
	 * @return The object output stream
	 */
	public ObjectOutputStream getObjectOut()
	{
		return objectOut;
	}
}
