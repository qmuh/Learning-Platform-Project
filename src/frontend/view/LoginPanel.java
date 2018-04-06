package frontend.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.GUIConstants;

public class LoginPanel extends JPanel
		implements ColourPalette, GUIConstants, WondrisInfo
{

	private static final long serialVersionUID = 1L;

	private static final String USERNAME = "Username";
	
	private JTextField userName;

	private JPasswordField password;

	private JButton enterCredentials;

	public LoginPanel()
	{
		super(new GridLayout(1, 1, 0, 0));

		this.setBackground(TERTIARY_COLOR);
		// TODO: Design interface with all border types.
		this.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(200, 300, 200, 300),
				new MatteBorder(10, 10, 10, 10, Color.DARK_GRAY)));

		// enterCredentials.addActionListener(new LoginListener());
		this.add(createLoginArea());
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

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Login Panel Test");
		frame.add(new LoginPanel());
		frame.setSize(WINDOW_SIZE);
		frame.setVisible(true);
	}
}
