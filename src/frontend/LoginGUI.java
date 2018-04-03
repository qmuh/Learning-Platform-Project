package frontend;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTree;

public class LoginGUI extends JFrame implements ServerInfo
{

	JTextField userName;
	JTextField password;
	JButton enterCredentials;
	private JTextField textField;
	private JTextField textField_1;

	public LoginGUI()
	{
		setSize(1600, 1000);
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(170, 72, 510, 334);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(243, 48, 146, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(113, 45, 95, 32);
		panel.add(lblUsername);
		
		JLabel label = new JLabel("Username");
		label.setBounds(113, 93, 95, 32);
		panel.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(243, 96, 146, 26);
		panel.add(textField_1);
	}

	public void login()
	{

	}

	public static void main(String[] args)
	{
		new LoginGUI();
	}
}
