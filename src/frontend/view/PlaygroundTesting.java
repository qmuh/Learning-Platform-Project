package frontend.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frontend.interfaces.ColourPalette;
import frontend.view.pages.components.customSwing.WButton;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class PlaygroundTesting extends JFrame
{

	public static void main(String[] args)
	{
		PlaygroundTesting p = new PlaygroundTesting();
		JPanel cardPanel = new JPanel();
		CardLayout cardLayout = new CardLayout();
		JPanel card1 = new JPanel();
		JPanel card2 = new JPanel();
		WButton first = new WButton("Press me");
		first.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				cardLayout.show(cardPanel, "Card 2");

			}
		});
		card1.add(first);
		WButton second = new WButton("Don't Press me");
		second.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				cardLayout.show(cardPanel, "Card 1");
			}
		});
		card2.add(second);
		card1.setBackground(ColourPalette.ACCENT_COLOR);
		card2.setBackground(ColourPalette.SECONDARY_COLOR);
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
