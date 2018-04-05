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
	protected Hashtable<Integer, Page> pages;
	
	public PageNavigator()
	{
		pages = new Hashtable<Integer, Page>();
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
