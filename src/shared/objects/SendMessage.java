package shared.objects;

import java.io.Serializable;

/**
 * Provides a class to communicate with the server.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 * 
 * @param <E>
 *            the object sent to the server to be used for context handling.
 */
public class SendMessage<E> implements Serializable
{
	/**
	 * The version of the class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The object which is to be sent
	 */
	private E messageObject;

	/**
	 * Command used by server to interpret the message
	 */
	private String command;

	/**
	 * Creates a message that does not expect to receive an object in return
	 * 
	 * @param commands
	 *            the commands to send
	 */
	public SendMessage(String commands)
	{
		this(null, commands);
	}

	/**
	 * Creates a message to send commands and an item for additional information
	 * 
	 * @param toAdd
	 *            the item with information to send
	 * @param commands
	 *            the commands to execute
	 */
	public SendMessage(E toAdd, String commands)
	{
		messageObject = toAdd;
		command = commands;
	}

	public E getContents()
	{
		return messageObject;
	}

	public String getCommand()
	{
		return command;
	}
}
