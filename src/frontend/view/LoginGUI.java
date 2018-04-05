package frontend.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.security.auth.login.LoginException;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import frontend.components.PageNavigator;
import frontend.interfaces.Colours;
import frontend.interfaces.ServerInfo;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.GUIConstants;
import sharedobjects.LoginInfo;
import sharedobjects.Professor;
import sharedobjects.User;

public class LoginGUI extends JFrame implements WondrisInfo, Colours, GUIConstants
{

	private static final long serialVersionUID = 1L;
	private JTextField userName;
	private JPasswordField password;
	private JButton enterCredentials;
	
	private JPanel currentPanel;
	
	public LoginGUI(String s)
	{
		super(s);
		setSize(WINDOW_SIZE);
		createFields();
		JPanel loginPanel = new JPanel();
		
		GridLayout loginPanelLayout = new GridLayout(1, 1);
		loginPanelLayout.setVgap(0);
		loginPanelLayout.setHgap(0);
		loginPanel.setLayout(loginPanelLayout);
		
		loginPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(200, 300, 200, 300),
				new MatteBorder(10, 10, 10, 10, Color.DARK_GRAY)));
		loginPanel.add(createLoginPanel(NAME));
		loginPanel.setBackground(TERTIARY_COLOR);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		currentPanel = loginPanel;
		this.add(currentPanel);
		setupLogin();
	}
	
	private void setupLogin()
	{
		enterCredentials.addActionListener(new LoginListener());
	}
	
	public class LoginListener implements ActionListener, ServerInfo{

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
				ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
				toServer.writeObject(getLoginInfo());
				toServer.flush();
				
				user = (User) fromServer.readObject();
				
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
			checkUser(user, socket);
		}
		
		private void checkUser(User user, Socket socket)
		{
			if (user.getUserType().equals("P"))
			{
				
				currentPanel.setVisible(false);
				currentPanel = new ProfessorGUI(socket, (Professor)user);
				add(currentPanel);				
				System.out.println("We have a professor");
//				cardPanel.add(new ProfessorGUI(mySocket), "PROF");

			}

			else if (user.getUserType().equals("S"))
			{
				System.out.println("We have a student");
			}
		}
	}
	

	
	public class IncorrectLoginException extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public IncorrectLoginException() {
			super("Incorrect Login Provided");
		}
	}
	
	private LoginInfo getLoginInfo() {
		LoginInfo loginInfo = null;
		try {
			loginInfo = new LoginInfo(Integer.parseInt(userName.getText()),
				new String(password.getPassword()));
			
		} catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this, "Username must only be numbers", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return loginInfo;
	}

	private void createFields()
	{
		userName = new JTextField(20);
		password = new JPasswordField(20);
		password.setEchoChar('\u2022');
		enterCredentials = new JButton("Sign In");
	}

	private JPanel createLoginPanel(String s)
	{
		GridLayout panelLayout = new GridLayout(1, 2);
		panelLayout.setVgap(0);
		panelLayout.setHgap(0);
		JPanel thePanel = new JPanel();
		thePanel.setLayout(panelLayout);
		thePanel.setBackground(ACCENT_COLOR);
		thePanel.add(createTitle(s));
		thePanel.add(createTextPanel());
		return thePanel;
	}

	private JPanel createTextPanel()
	{
		GridLayout panelLayout = new GridLayout(3, 1);
		panelLayout.setVgap(0);
		panelLayout.setHgap(0);
		JPanel thePanel = new JPanel(panelLayout);
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
		text.setFont(TEXTFONT);
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
		enterCredentials.setFont(TEXTFONT);
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
		new LoginGUI(TITLE);
	}
}
