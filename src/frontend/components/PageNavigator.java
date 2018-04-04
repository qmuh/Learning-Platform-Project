package frontend.components;

import java.awt.CardLayout;

import javax.swing.JPanel;

import frontend.view.pages.*;

public class PageNavigator
{
	private JPanel pageLayout;
	private CardLayout cardLayout;
	private Page currentPage;
	
	public PageNavigator()
	{
		pageLayout = new JPanel(cardLayout);
		BoxList<?> boxList = new BoxList<>();
		currentPage = new HomePage(boxList);
		pageLayout.add(currentPage);
		
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
		return new HomePage(new BoxList<>());
	}
}
