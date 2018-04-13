package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisDirectories;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.BoxList;
import frontend.view.pages.components.Footer;
import frontend.view.pages.components.Header;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import frontend.view.pages.interfaces.PageNames;
import shared.objects.User;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public abstract class Page<T extends Box, U> extends JPanel
		implements PageNames, WondrisInfo, ColourPalette, WondrisDirectories
{

	private static final long serialVersionUID = 1L;
	protected Header header;
	private Footer footer;
	protected JPanel body;
	protected ArrayList<U> itemList;
	protected BoxList<T> itemDisplay;
	// protected StudentGUI studentGUI;
	// protected ProfessorGUI professorGUI;

	protected Refresh function;
	
	public Page(User user)
	{
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new BorderLayout());
		itemDisplay = new BoxList<T>();
		body = new JPanel();
		header = new Header();
		footer = new Footer();
		header.setNamePanel(user);
		this.add(header, BorderLayout.NORTH);
		this.add(footer, BorderLayout.SOUTH);
		this.add(body, BorderLayout.CENTER);
	}

//	public abstract void displayPage();

	protected JPanel createLabel(String label, Font f)
	{
		JPanel labelPanel = new JPanel();
		JLabel theLabel = new JLabel(label);
		theLabel.setFont(f);
		labelPanel.add(theLabel);
		return labelPanel;
	}

	public void setBoxList(BoxList<T> boxList)
	{
		this.itemDisplay = boxList;
	}
	
	public WLabel getLogo()
	{
		return header.getLogo();
	}

	public WButton getHomeButton()
	{
		return header.getHomeButton();
	}

	public WButton getBackButton()
	{
		return header.getBackButton();
	}

	public WButton getGearButton()
	{
		return header.getGearButton();
	}

	public void setBackButtonEnabled(boolean b)
	{
		header.getBackButton().setEnabled(b);
	}
	
	public void setRefreshBehaviour(Refresh function)
	{
		this.function = function;
	}
	
	public void refresh()
	{
		this.itemDisplay.removeAll();
		this.itemDisplay.revalidate();
		this.itemDisplay.repaint();
		System.out.println("Refreshing: " + this.getName());
		this.function.refresh();
	}
	
	public void addToBoxList(T item)
	{
		itemDisplay.add(item);
		// TODO: Consider removal.
		itemDisplay.revalidate();
		itemDisplay.repaint();
	}
}
