package shared.objects;

import java.io.Serializable;

public class SendMessage<E> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Command

	/**
	 * The object which is to be sent
	 */
	private E messageObject;

	/**
	 * Command used by server to interpret the message
	 */
	private String command;

	public SendMessage(String commands)
	{
		this(null, commands);
	}

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
