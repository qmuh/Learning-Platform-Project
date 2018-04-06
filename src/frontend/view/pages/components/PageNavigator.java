package frontend.view.pages.components;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import frontend.view.pages.*;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class PageNavigator extends JPanel implements PageNames
{
	private CardLayout cardLayout;
	private Page<?, ?> currentPage;

	public PageNavigator()
	{
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		addPage(new HomePage(), HOME_PAGE);
		this.showPage(HOME_PAGE);
	}

	public void showPage(String pageName)
	{
		cardLayout.show(this, pageName);
		currentPage = (Page<?, ?>) searchPage(pageName);

	}

	public Page<?, ?> searchPage(String name)
	{
		for (Component component : this.getComponents())
		{
			if (component.getName().equals(name))
			{
				return (Page<?, ?>) component;
			}
		}
		return null;
	}

	protected void addPage(Page<?, ?> page, String pageName)
	{
		page.setHomeButtonListener(new HomeButtonListener());
		page.setBackButtonListener(new BackButtonListener());
		this.add(page, pageName);
	}

	public void previousPage()
	{
		cardLayout.previous(this);
	}

	public class BackButtonListener implements ActionListener
	{
		// TODO: Fix or Remove
		@Override
		public void actionPerformed(ActionEvent e)
		{
			previousPage();
		}

	}

	public class HomeButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(HOME_PAGE);
		}
	}

	public class ViewCourseListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(COURSE_PAGE);
		}

	}

}
