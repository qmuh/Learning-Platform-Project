package frontend.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.GUIConstants;
import frontend.view.pages.components.customSwing.WButton;
import shared.objects.LoginInfo;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.1
 * @since April 6, 2018
 */
class LoginPanel extends JPanel
		implements ColourPalette, GUIConstants, WondrisInfo
{

	private static final long serialVersionUID = 1L;

	private static final String USERNAME_LABEL = "Username";

	private static final String PASSWORD_LABEL = "Password";

	private static final String BUTTON_SIGN_IN_TEXT = "Sign In";

	private static final Character PASSWORD_ECHO_CHAR = '\u2022';

	private static final int FIELD_SIZE = 8;

	private JTextField usernameField;

	private JPasswordField passwordField;

	private WButton loginButton;

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
	
	public WButton getLoginButton()
	{
		return loginButton;
	}

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

	public void setLoginListener(ActionListener listener)
	{
		loginButton.addActionListener(listener);
	}

	private JPanel createLoginArea()
	{
		JPanel thePanel = new JPanel(new GridLayout(1, 2, 0, 0));
		thePanel.setBackground(ACCENT_COLOR);
		thePanel.add(createTitleHalf(NAME));
		thePanel.add(createLoginHalf());
		return thePanel;
	}

	private JPanel createTitleHalf(String name)
	{
		JPanel titlePanel = new JPanel(new GridLayout(1, 1, 0, 0));
		JLabel title = new JLabel(name);

		title.setFont(TITLE_FONT);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);

		titlePanel.setBackground(BACKGROUND_COLOUR);
		titlePanel.add(title);
		return titlePanel;
	}

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

	private JPanel createButtonPanel()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(50, 50));
		buttonPanel.add(loginButton);
		buttonPanel.setOpaque(false);
		return buttonPanel;
	}

	private JPanel createEmptyPanel()
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel.setOpaque(false);
		return panel;
	}

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

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Login Panel Test");
		frame.add(new LoginPanel());
		frame.setSize(WINDOW_SIZE);
		frame.setVisible(true);
	}
}
