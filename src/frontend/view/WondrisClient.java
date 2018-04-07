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
public class WondrisClient extends JFrame
		implements WondrisInfo, ColourPalette, GUIConstants, ServerInfo, UserInfo
{

	private static final long serialVersionUID = 1L;

	private static final String BUTTON_SIGN_IN_TEXT = "Sign In";

	private static final Character PASSWORD_ECHO_CHAR = '\u2022';

	private JTextField userName;

	private JPasswordField password;

	private JButton enterCredentials;

	private JPanel currentPanel;

	public WondrisClient(String windowName)
	{
		super(windowName);
		this.setSize(WINDOW_SIZE);

		currentPanel = new LoginPanel();

				createLoginPanel();

		// createFields();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.add(currentPanel);
		setVisible(true);
	}

	private void createFields()
	{
		userName = new JTextField(20);
		password = new JPasswordField(20);
		password.setEchoChar(PASSWORD_ECHO_CHAR);
		enterCredentials = new JButton(BUTTON_SIGN_IN_TEXT);
	}

	private JPanel createLoginPanel()
	{
		JPanel loginPanel = new JPanel(new GridLayout(1, 1, 0, 0));
		loginPanel.setBackground(TERTIARY_COLOR);
		// TODO: Design interface with all border types.
		loginPanel.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(200, 300, 200, 300),
				new MatteBorder(10, 10, 10, 10, Color.DARK_GRAY)));

		//enterCredentials.addActionListener(new LoginListener());
		loginPanel.add(createLoginArea());
		return loginPanel;
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

	private JPanel createLoginArea()
	{
		JPanel thePanel = new JPanel(new GridLayout(1, 2, 0, 0));
		thePanel.setBackground(ACCENT_COLOR);
		thePanel.add(createTitle(NAME));
		thePanel.add(createTextPanel());
		return thePanel;
	}

	private JPanel createTextPanel()
	{
		JPanel thePanel = new JPanel(new GridLayout(3, 1, 0, 0));
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(3, 1));
		textPanel.add(createTextField("Username", userName));
		textPanel.add(createTextField("Password", password));
		textPanel.add(createButtonPanel());
		thePanel.add(createEmptyPanel(ACCENT_COLOR));
		thePanel.add(textPanel);
		thePanel.add(createEmptyPanel(ACCENT_COLOR));
		return thePanel;
	}

	private JPanel createTitle(String s)
	{
		JPanel titlePanel = new JPanel(new GridLayout(1, 1));
		titlePanel.setBackground(BACKGROUND_COLOUR);
		JLabel title = new JLabel(s);
		title.setFont(TITLE_FONT);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		titlePanel.add(title);
		return titlePanel;
	}

	private JPanel createTextField(String s, JTextField field)
	{
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setBackground(ACCENT_COLOR);
		JLabel text = new JLabel(s);
		text.setFont(TEXT_FONT);
		text.setForeground(Color.WHITE);
		textFieldPanel.add(text);
		textFieldPanel.add(field);
		return textFieldPanel;
	}

	private JPanel createButtonPanel()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(ACCENT_COLOR);
		buttonPanel.setPreferredSize(new Dimension(50, 50));
		enterCredentials.setFont(TEXT_FONT);
		enterCredentials.setFocusPainted(false);
		enterCredentials.setBackground(SECONDARY_COLOR);
		buttonPanel.add(enterCredentials);
		return buttonPanel;
	}

	private JPanel createEmptyPanel(Color c)
	{
		FlowLayout panelLayout = new FlowLayout();
		panelLayout.setVgap(0);
		panelLayout.setHgap(0);
		JPanel panel = new JPanel(panelLayout);
		panel.setBackground(c);
		return panel;
	}

	public static void main(String[] args)
	{
		new WondrisClient(TITLE);
	}
}
