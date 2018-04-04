package frontend.components;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frontend.view.pages.*;

public class PageNavigator extends JFrame
{
	private JPanel pageLayout;
	private CardLayout cardLayout;
	private Page<?, ?> currentPage;
	
	public PageNavigator()
	{
		pageLayout = new JPanel(cardLayout);
		currentPage = new HomePage();
		pageLayout.add(currentPage);
		add(pageLayout);
		setVisible(true);
	}
	
	public void showPage(String page) {
		JPanel thePage = searchPage(page);
		addPage(thePage, page);
	}
	
	public void addPage(JPanel page, String name) {
		pageLayout.add(page);
	}
	
	public void removePage(String name) {
		pageLayout.remove(currentPage);
	}
	
	public JPanel searchPage(String name) {
		//TODO: Implement page search
		return new HomePage();
	}
}
