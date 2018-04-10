package frontend.controller;

import java.net.Socket;

import frontend.view.pages.components.PageNavigator;
import shared.objects.Student;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class StudentGUI extends PageNavigator
{
	private Student thisStudent;
	// Page??
	private MessageHandler comHandler;

	public StudentGUI(Socket socket, Student user)
	{
		super();
	}

}
