package frontend.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.security.auth.login.LoginException;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.ServerInfo;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.GUIConstants;
import shared.UserInfo;
import shared.objects.LoginInfo;
import shared.objects.SendMessage;
import shared.objects.User;
import shared.objects.Professor;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class LoginGUI extends JFrame
		implements WondrisInfo, ColourPalette, GUIConstants, ServerInfo, UserInfo
{

	private static final long serialVersionUID = 1L;

	private JTextField userName;

	private JPasswordField password;

	private JButton enterCredentials;

	private JPanel currentPanel;

	public LoginGUI(String windowName)
	{
		super(windowName);
		this.setSize(WINDOW_SIZE);

		currentPanel = new LoginPanel();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.add(currentPanel);
		setVisible(true);
	}

	public class LoginListener implements ActionListener, ServerInfo
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				checkLogin();
				authenticateLogin();
			} catch (LoginException e1)
			{
				e1.printStackTrace();
			}
		}

		private void checkLogin() throws LoginException
		{
			if (getLoginInfo() == null)
			{
				throw new LoginException();
			}
		}

		private void authenticateLogin()
		{
			Socket socket = null;
			User user = null;
			try
			{
				socket = new Socket(HOST_NAME, PORT_NUMBER);
				ObjectOutputStream toServer = new ObjectOutputStream(
						socket.getOutputStream());
				ObjectInputStream fromServer = new ObjectInputStream(
						socket.getInputStream());
				toServer.writeObject(getLoginInfo());
				toServer.flush();

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
			if (user.getUserType().equals(USER_PROFESSOR))
			{
				currentPanel.setVisible(false);
				currentPanel = new ProfessorGUI(socket, (Professor) user);
				add(currentPanel);
				System.out.println("We have a professor");
				// cardPanel.add(new ProfessorGUI(mySocket), "PROF");
			}

			else if (user.getUserType().equals(USER_STUDENT))
			{
				System.out.println("We have a student");
			} else
			{
				return;
			}
			addWindowListener(new WindowListener()
			{

				@Override
				public void windowOpened(WindowEvent arg0)
				{
					// TODO Auto-generated method stub

				}

				@Override
				public void windowIconified(WindowEvent arg0)
				{
					// TODO Auto-generated method stub

				}

				@Override
				public void windowDeiconified(WindowEvent arg0)
				{
					// TODO Auto-generated method stub

				}

				@Override
				public void windowDeactivated(WindowEvent arg0)
				{
					// TODO Auto-generated method stub

				}

				@Override
				public void windowClosing(WindowEvent arg0)
				{
					int selection = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to close the program?",
							"Exit Application", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (selection == JOptionPane.YES_OPTION)
					{
						try
						{
							objectOutputStream.writeObject(new SendMessage(null, "LOGOUT"));
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.exit(0);
					}

				}

				@Override
				public void windowClosed(WindowEvent arg0)
				{
					// TODO Auto-generated method stub

				}

				@Override
				public void windowActivated(WindowEvent arg0)
				{
					// TODO Auto-generated method stub

				}
			});
		}
	}

	public class IncorrectLoginException extends Exception
	{

		private static final long serialVersionUID = 1L;

		public IncorrectLoginException()
		{
			super("Incorrect Login Provided");
		}
	}

	private LoginInfo getLoginInfo()
	{
		LoginInfo loginInfo = null;
		try
		{
			loginInfo = new LoginInfo(Integer.parseInt(userName.getText()),
					new String(password.getPassword()));

		} catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this, "Username must only be numbers",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return loginInfo;
	}

	public static void main(String[] args)
	{
		new LoginGUI(TITLE);
	}
}
