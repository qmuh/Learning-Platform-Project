package frontend.components;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.view.pages.*;

public class PageNavigator extends JPanel implements PageNames
{
	private JPanel pageLayout;
	private CardLayout cardLayout;
	private Page<?, ?> currentPage;

	public PageNavigator()
	{
		pageLayout = new JPanel();
		cardLayout = new CardLayout();
	}

	public void showPage(String pageName)
	{
		cardLayout.show(pageLayout, pageName);
	}

	public void addPage(JPanel page, String pageName)
	{
		pageLayout.add(page, pageName);
	}

	public void previousPage()
	{
		cardLayout.previous(pageLayout);
	}

}
