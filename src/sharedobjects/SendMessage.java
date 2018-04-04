package sharedobjects;

import java.io.Serializable;

public class SendMessage<E> implements Serializable
{

	// Command
	
	/**
	 * The object which is to be sent
	 */
	private E messageObject;
	
	/**
	 * Command used by server to interpret the message
	 */
	private String command;

	public SendMessage(E toAdd, String commands)
	{
		messageObject = toAdd;
		command = commands;
	}
	
	public E getmessageObject()
	{
		return messageObject;
	}

	public String getCommand()
	{
		return command;
	}
	
	
}
