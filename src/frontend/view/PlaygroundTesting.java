package frontend.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import frontend.interfaces.Colours;

public class PlaygroundTesting extends JFrame
{

	public static void main(String[] args)
	{
		PlaygroundTesting p = new PlaygroundTesting();
		JPanel cardPanel = new JPanel();
		CardLayout cardLayout = new CardLayout();
		JPanel card1 = new JPanel();
		JPanel card2 = new JPanel();
		JButton first = new JButton("Press me");
		first.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				cardLayout.show(cardPanel, "Card 2");
				
			}
		});
		card1.add(first);
		JButton second = new JButton("Don't Press me");
		second.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				cardLayout.show(cardPanel, "Card 1");
			}
		});
		card2.add(second);
		card1.setBackground(Colours.ACCENT_COLOR);
		card2.setBackground(Colours.SECONDARY_COLOR);
		cardPanel.setLayout(cardLayout);
		cardPanel.add(card1, "Card 1");
		cardPanel.add(card2, "Card 2");
		cardLayout.show(cardPanel, "Card 1");
		
		p.add(cardPanel);
		p.setSize(500, 500);
		p.setVisible(true);
		p.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
}
