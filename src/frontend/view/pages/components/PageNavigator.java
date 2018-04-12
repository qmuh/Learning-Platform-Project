package frontend.view.pages.components;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JPanel;

import frontend.controller.Client;
import frontend.controller.professor.listeners.CourseActiveButtonListener;
import frontend.interfaces.ColourPalette;
import frontend.view.pages.Page;
import frontend.view.pages.home.HomePage;
import frontend.view.pages.interfaces.PageNames;
import frontend.view.pages.items.course.CourseItem;
import frontend.view.pages.items.course.CourseItemProfessor;
import shared.interfaces.UserCommands;
import shared.objects.Course;
import shared.objects.SendMessage;
import shared.objects.User;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public abstract class PageNavigator extends JPanel
		implements PageNames, ColourPalette, UserCommands
{
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private Page<?, ?> currentPage;
	private Stack<Page<?, ?>> pageStack;
	protected Client client;

	public PageNavigator(Socket socket, User user)
	{
		this.cardLayout = new CardLayout();

		this.client = new Client();
		this.client.connectToServer(socket);

		this.pageStack = new Stack<Page<?, ?>>();

		this.currentPage = new HomePage(user);
		this.currentPage.setBackButtonEnabled(false);

		this.setLayout(cardLayout);
		this.addPage(currentPage);
		// Show the current home page without adding it to the stack
		// to preserve the state of the back button.
		this.cardLayout.show(this, HOME_PAGE);
	}

	public void showPage(String pageName)
	{
		// If the page to show is not the current page
		if (!currentPage.getName().equals(pageName))
		{
			// Go to the page to show
			Page<?,?> nextPage = searchPage(pageName);
			nextPage.refresh();
			currentPage.setBackButtonEnabled(true);
			pageStack.push(currentPage);
			cardLayout.show(this, pageName);
			currentPage = searchPage(pageName);
		}
	}

	protected Page<?, ?> searchPage(String name)
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

	private void previousPage()
	{
		// If the stack is not empty
		if (!pageStack.isEmpty())
		{
			String pageName = pageStack.pop().getName();
			cardLayout.show(this, pageName);
			currentPage = searchPage(pageName);

			// If the stack is empty after hitting the back button
			if (pageStack.isEmpty())
			{
				currentPage.setBackButtonEnabled(false);
			}
		}
	}

	protected void createNewCourse(Course course, HomePage homePage)
	{
		createCoursePage(course);
		createAssignmentPage(course);
		createSubmissionPage(course);
		createComposeEmailPage(course);
		createDiscussionPage(course);
	}

	abstract protected void createCoursePage(Course course);

	abstract protected void createAssignmentPage(Course course);

	abstract protected void createSubmissionPage(Course course);

	abstract protected void createComposeEmailPage(Course course);

	abstract protected void createDiscussionPage(Course course);

	abstract protected void createHomePage();

	private class BackButtonListener implements ActionListener
	{
		// TODO: Fix or Remove
		@Override
		public void actionPerformed(ActionEvent e)
		{
			previousPage();
		}
	}

	private class HomeButtonListener implements ActionListener
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
