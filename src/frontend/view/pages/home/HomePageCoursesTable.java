package frontend.view.pages.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.BoxList;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import frontend.view.pages.interfaces.GUIConstants;
import frontend.view.pages.items.course.CourseItem;

public class HomePageCoursesTable extends JPanel
		implements WondrisInfo, ColourPalette, GUIConstants
{
	private static final long serialVersionUID = 1L;

	private static final String TABLE_TITLE = "My Courses";

	private JPanel newCourseButtonPanel;

	private JPanel activeLabelPanel;

	private WButton newCourseButton;

	private BoxList<CourseItem> boxList;

	public HomePageCoursesTable(BoxList<CourseItem> boxList)
	{
		this.setLayout(new BorderLayout());
		this.boxList = boxList;
		this.add(createTitle(), BorderLayout.NORTH);
		this.add(createTable(), BorderLayout.CENTER);
	}

	private JPanel createTable()
	{
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		activeLabelPanel = new JPanel(new BorderLayout());
		JPanel tableHeader = new JPanel();
		
		
		tableHeader.setLayout(new GridLayout(1, 3));
		tableHeader.setBorder(
				BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		tableHeader.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
		tableHeader
				.add(new WLabel("Course Name", SUB_TITLE_FONT, WLabel.CENTER));
		tableHeader.add(activeLabelPanel);

		tableHeader.add(
				new WLabel("Course Home", SUB_TITLE_FONT, WLabel.CENTER), 2);

		tablePanel.add(tableHeader, BorderLayout.NORTH);
		tablePanel.add(createCoursesArea(), BorderLayout.CENTER);
		return tablePanel;
	}

	private JPanel createTitle()
	{
		JPanel titlePanel = new JPanel(new GridLayout(1, 2));
		JPanel theTitle = new JPanel();
		JLabel title = new JLabel(TABLE_TITLE);
		title.setFont(TITLE_FONT);
		theTitle.setBackground(BACKGROUND_COLOUR);
		theTitle.add(title);
		titlePanel.add(theTitle, 0);
		titlePanel.add(createButton("New Course"), 1);
		return titlePanel;
	}

	private JPanel createButton(String label)
	{
		newCourseButtonPanel = new JPanel();
		newCourseButtonPanel.setBackground(BACKGROUND_COLOUR);
		newCourseButtonPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
		newCourseButton = new WButton(label);
		return newCourseButtonPanel;
	}

	private JScrollPane createCoursesArea()
	{
		JScrollPane scrollPane = new JScrollPane(boxList);
		scrollPane.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		return scrollPane;
	}

	public void setNewCourseListener(ActionListener listener)
	{
		newCourseButton = new WButton("New Course");
		newCourseButton.addActionListener(listener);
		newCourseButtonPanel.add(newCourseButton);
	}

	public void enableActiveLabel()
	{
		WLabel active = new WLabel("Active", SUB_TITLE_FONT, JLabel.RIGHT);
		activeLabelPanel.add(active);
	}
}
