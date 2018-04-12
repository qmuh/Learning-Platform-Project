package frontend.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisDirectories;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import frontend.view.pages.interfaces.GUIConstants;
import shared.objects.LoginInfo;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.1
 * @since April 6, 2018 The login panel holds the JPanel for the login
 */
public class LoginPanel extends JPanel
		implements ColourPalette, GUIConstants, WondrisInfo, WondrisDirectories
{

	/**
	 * Sets the serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The username label
	 */
	private static final String USERNAME_LABEL = "Username";

	/**
	 * The password label
	 */
	private static final String PASSWORD_LABEL = "Password";

	/**
	 * The text for the sign in
	 */
	private static final String BUTTON_SIGN_IN_TEXT = "Sign In";

	/**
	 * Sets the default field size
	 */
	private static final int FIELD_SIZE = 8;

	/**
	 * The JTextField for the username
	 */
	private JTextField usernameField;

	/**
	 * The JTextField for the password
	 */
	private JPasswordField passwordField;

	/**
	 * The login button
	 */
	private WButton loginButton;

	/**
	 * The login panel constructor
	 */
	public LoginPanel()
	{
		super(new GridLayout(1, 1, 0, 0));

		this.setBackground(TERTIARY_COLOR);
		// TODO: Design interface with all border types.
		this.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(200, 300, 200, 300),
				new MatteBorder(10, 10, 10, 10, Color.DARK_GRAY)));

		this.add(createLoginArea());
	}

	/**
	 * Returns the login button
	 * 
	 * @return The login button
	 */
	public WButton getLoginButton()
	{
		return loginButton;
	}

	/**
	 * Returns the login info
	 * 
	 * @return The login info entered
	 */
	public LoginInfo getLoginInfo()
	{

		LoginInfo loginInfo = null;

		try
		{
			loginInfo = new LoginInfo(Integer.parseInt(usernameField.getText()),
					new String(passwordField.getPassword()));
		} catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this, "Username must be a number.",
					"Username Error", JOptionPane.ERROR_MESSAGE);
		}
		return loginInfo;
	}

	/**
	 * Sets up the listener for logging in
	 * 
	 * @param listener
	 *            The listener associated with the WButton to be set
	 */
	public void setLoginListener(ActionListener listener)
	{
		loginButton.addActionListener(listener);
	}

	/**
	 * Creates the login area
	 * 
	 * @return The JPanel login area
	 */
	private JPanel createLoginArea()
	{
		JPanel thePanel = new JPanel(new GridLayout(1, 2, 0, 0));
		thePanel.setBackground(ACCENT_COLOR);
		thePanel.add(createTitleHalf(NAME));
		thePanel.add(createLoginHalf());
		return thePanel;
	}

	/**
	 * Creates the title half for the login panel
	 * 
	 * @param name
	 *            The name of the Title half
	 * @return The panel portion for the title half
	 */
	private JPanel createTitleHalf(String name)
	{
		JPanel titlePanel = new JPanel(new GridLayout(1, 1, 0, 0));
		
		try
		{
			WLabel title;
			title = new WLabel(ImageIO.read(new File(DIR_GRAPHICS + "wondrisLogoBW.png")));
			
			title.setFont(TITLE_FONT);
			title.setForeground(Color.WHITE);
			title.setHorizontalAlignment(SwingConstants.CENTER);

			titlePanel.setBackground(BACKGROUND_COLOUR);
			titlePanel.add(title);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return titlePanel;
	}

	/**
	 * Creates the login half
	 * 
	 * @return The panel with the login half
	 */
	private JPanel createLoginHalf()
	{
		JPanel thePanel = new JPanel(new GridLayout(3, 1, 0, 0));
		JPanel textPanel = new JPanel(new GridLayout(3, 1, 0, 0));

		usernameField = new JTextField(FIELD_SIZE);
		usernameField.requestFocusInWindow();
		usernameField.setFont(TEXT_FONT);
		passwordField = new JPasswordField(FIELD_SIZE);
		passwordField.setFont(TEXT_FONT);
		passwordField.setEchoChar(PASSWORD_ECHO_CHAR);
		loginButton = new WButton(BUTTON_SIGN_IN_TEXT);

		textPanel.add(createTextField(USERNAME_LABEL, usernameField));
		textPanel.add(createTextField(PASSWORD_LABEL, passwordField));
		textPanel.add(createButtonPanel());
		thePanel.add(createEmptyPanel());
		thePanel.add(textPanel);
		thePanel.add(createEmptyPanel());
		textPanel.setOpaque(false);
		thePanel.setBackground(ACCENT_COLOR);
		return thePanel;
	}

	/**
	 * Creates the button panel for the login panel
	 * 
	 * @return The panel for the buttons
	 */
	private JPanel createButtonPanel()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(50, 50));
		buttonPanel.add(loginButton);
		buttonPanel.setOpaque(false);
		return buttonPanel;
	}

	/**
	 * Creates the empty panel
	 * 
	 * @return The created empty panel
	 */
	private JPanel createEmptyPanel()
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel.setOpaque(false);
		return panel;
	}

	/**
	 * Creates the panel with the text field
	 * 
	 * @param s
	 *            The string for the label
	 * @param field
	 *            Adds the JTextField
	 * @return The panel for the text field
	 */
	private JPanel createTextField(String s, JTextField field)
	{
		JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel text = new JLabel(s);
		text.setFont(TEXT_FONT);
		text.setForeground(Color.WHITE);
		textFieldPanel.add(text);
		textFieldPanel.add(field);
		textFieldPanel.setOpaque(false);
		return textFieldPanel;
	}

	/**
	 * Used for testing
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Login Panel Test");
		frame.add(new LoginPanel());
		frame.setSize(WINDOW_SIZE);
		frame.setVisible(true);
	}
}
