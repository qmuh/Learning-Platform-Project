package frontend.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import frontend.interfaces.WondrisInfo;
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
	private Socket mySocket;
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;

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

	public void onlySendMessage(SendMessage<?> toSend) throws IOException
	{
		objectOut.writeObject(toSend);
		objectOut.flush();
	}

	public Socket getMySocket()
	{
		return mySocket;
	}

	public ObjectInputStream getObjectIn()
	{
		return objectIn;
	}

	public ObjectOutputStream getObjectOut()
	{
		return objectOut;
	}
}
