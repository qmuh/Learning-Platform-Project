package frontend;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class LoginGUI extends JFrame implements ServerInfo, WondrisInfo, Colours
{

	private static final long serialVersionUID = 1L;
	private JTextField userName;
	private JTextField password;
	private JButton enterCredentials;

	public LoginGUI(String s)
	{
		super(s);
		setSize(1600, 1000);
		createFields();

		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(200, 400, 200, 400),
				new MatteBorder(5, 5, 5, 5, Color.DARK_GRAY)));
		loginPanel.add(createLoginPanel(NAME));
		loginPanel.setBackground(BACKGROUND_COLOUR);
		add(loginPanel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login();
	}

	private void login()
	{
		
	}
	
	private void createFields() {
		userName = new JTextField(20);
		password = new JTextField(20);
		enterCredentials = new JButton("Sign in");
	}
	
	private JPanel createLoginPanel(String s) 
	{
		JPanel thePanel = new JPanel();
		thePanel.setLayout(new BorderLayout());
		thePanel.setBackground(TERTIARY_COLOR);
		thePanel.add(createTitle(s), BorderLayout.NORTH);
		thePanel.add(createTextPanel(), BorderLayout.CENTER);
		return thePanel;
	}

	private JPanel createTextPanel()
	{
		JPanel wrapper = new JPanel();
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(3, 1));
		textPanel.add(createTextField("Username", userName));
		textPanel.add(createTextField("Password", password));
		textPanel.add(createButtonPanel());
		wrapper.add(textPanel);
		return wrapper;
	}

	private JPanel createTitle(String s) {
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(SECONDARY_COLOR);
		titlePanel.setPreferredSize(new Dimension(760, 50));
		JLabel title = new JLabel(s);
		title.setFont(TITLE_FONT);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(title);
		return titlePanel;
	}
	
	private JPanel createTextField(String s, JTextField field) {
		JPanel textFieldPanel = new JPanel();
		JLabel text = new JLabel(s);
		text.setFont(TEXTFONT);
		textFieldPanel.add(text);
		textFieldPanel.add(field);
		return textFieldPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(50, 25));
		buttonPanel.add(enterCredentials);
		return buttonPanel;
	}
	
	public static void main(String[] args)
	{
		new LoginGUI(TITLE);
	}
}
