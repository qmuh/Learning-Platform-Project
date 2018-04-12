package frontend.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import frontend.controller.professor.ProfessorGUI;
import frontend.controller.student.StudentGUI;
import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.LoginPanel;
import frontend.view.pages.interfaces.GUIConstants;
import shared.interfaces.ServerInfo;
import shared.interfaces.UserCommands;
import shared.interfaces.UserInfo;
import shared.objects.LoginInfo;
import shared.objects.Professor;
import shared.objects.SendMessage;
import shared.objects.Student;
import shared.objects.User;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class LoginGUI extends JFrame implements WondrisInfo, ColourPalette,
		GUIConstants, ServerInfo, UserInfo, UserCommands
{

	/**
	 * Used for the version of the class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The panel where everything happens
	 */
	private JPanel currentPanel;

	/** Constructs the LoginGUI
	 * @param windowName Name of the Window
	 */
	public LoginGUI(String windowName)
	{
		super(windowName);
		this.setSize(WINDOW_SIZE);

		LoginPanel loginPanel = new LoginPanel();
		loginPanel.setLoginListener(new LoginListener(loginPanel));

		this.setLookAndFeel();

		this.getRootPane().setDefaultButton(loginPanel.getLoginButton());
		this.currentPanel = loginPanel;
		this.add(currentPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(
			        UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Controls the complete logic for handling a user logging in with the
	 * server and making decisions based on server response
	 */
	private class LoginListener implements ActionListener
	{
		private LoginPanel loginPanel;

		public LoginListener(LoginPanel loginPanel)
		{
			this.loginPanel = loginPanel;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			LoginInfo loginInfo = loginPanel.getLoginInfo();
			if (loginInfo != null)
			{
				authenticateLogin(loginInfo);
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
		}

		private void authenticateLogin(LoginInfo loginInfo)
		{
			Socket socket = null;
			User user = null;
			try
			{
				socket = new Socket(HOST_NAME, PORT_NUMBER);
				ObjectOutputStream toServer = new ObjectOutputStream(
						socket.getOutputStream());
				toServer.writeObject(loginInfo);
				toServer.flush();
				ObjectInputStream fromServer = new ObjectInputStream(
						socket.getInputStream());

				user = (User) fromServer.readObject();
				checkUser(user, socket, toServer);
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		private void checkUser(User user, Socket socket,
				ObjectOutputStream objectOutputStream)
		{
			if (user == null)
			{
				JOptionPane.showMessageDialog(loginPanel,
						"Error, incorrect username or password.",
						"Authentication Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (user.getUserType().equals(USER_PROFESSOR))
			{
				currentPanel.setVisible(false);
				currentPanel = new ProfessorGUI(socket, (Professor) user);

				// cardPanel.add(new ProfessorGUI(mySocket), "PROF");

			} else if (user.getUserType().equals(USER_STUDENT))
			{
				currentPanel.setVisible(false);
				currentPanel = new StudentGUI(socket, (Student) user);

			} else
			{ // user is not logged in
				return;

			}

			add(currentPanel);
			getRootPane().setDefaultButton(null);

			// Logout functionality;
			addWindowListener(new WindowListener()
			{
				@Override
				public void windowActivated(WindowEvent arg0)
				{
				}

				@Override
				public void windowClosed(WindowEvent arg0)
				{
				}

				@Override
				public void windowClosing(WindowEvent arg0)
				{
					int selection = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to log out and "
									+ "close the program?",
							"Exit Application", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (selection == JOptionPane.YES_OPTION)
					{
						try
						{
							objectOutputStream
									.writeObject(new SendMessage<>(CMD_LOGOUT));
						} catch (IOException e)
						{
							e.printStackTrace();
						}
						System.exit(0);
					}
				}

				@Override
				public void windowDeactivated(WindowEvent arg0)
				{
				}

				@Override
				public void windowDeiconified(WindowEvent arg0)
				{
				}

				@Override
				public void windowIconified(WindowEvent arg0)
				{
				}

				@Override
				public void windowOpened(WindowEvent arg0)
				{
				}
			});
		}
	}

	public static void main(String[] args)
	{
		new LoginGUI(TITLE);
	}
}
