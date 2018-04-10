package frontend.view.pages.components;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JPanel;

import frontend.interfaces.ColourPalette;
import frontend.view.pages.HomePage;
import frontend.view.pages.Page;
import frontend.view.pages.PageNames;
import frontend.view.pages.components.customSwing.WButton;
import shared.objects.Course;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class PageNavigator extends JPanel implements PageNames, ColourPalette
{
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private Page<?, ?> currentPage;
	private Stack<Page<?, ?>> pageStack;

	public PageNavigator()
	{
		this.cardLayout = new CardLayout();
		this.setLayout(cardLayout);

		this.pageStack = new Stack<Page<?, ?>>();

		this.currentPage = new HomePage();
		currentPage.setBackButtonEnabled(false);

		this.addPage(currentPage);
		cardLayout.show(this, HOME_PAGE);
	}

	public void showPage(String pageName)
	{
		if (currentPage.getName().equals(pageName))
		{
			return;
			
		} else
		{
			currentPage.setBackButtonEnabled(true);
			pageStack.push(currentPage);
			pageStack.peek();
			cardLayout.show(this, pageName);
			currentPage = searchPage(pageName);
		}
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

	protected void addPage(Page<?, ?> page)
	{
		page.getHomeButton().addActionListener(new HomeButtonListener());
		page.getBackButton().addActionListener(new BackButtonListener());
		this.add(page, page.getName());
	}

	public void previousPage()
	{
		if (!pageStack.isEmpty())
		{
			String pageName = pageStack.pop().getName();
			cardLayout.show(this, pageName);
			currentPage = searchPage(pageName);
			System.out.println(pageName + " was removed from the stack.");
			if (pageStack.isEmpty())
			{
				currentPage.setBackButtonEnabled(false);
			}
		} else
		{

		}
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

	public class ViewCoursePageListener implements ActionListener
	{
		private Course course;

		public ViewCoursePageListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(COURSE_PAGE + course.getId());
		}
	}
}
