package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.CourseItem;
import shared.objects.Course;

public class CoursePageTable extends JPanel implements WondrisInfo, ColourPalette, GUIConstants
{
	private static final long serialVersionUID = 1L;

	private static final String TABLE_TITLE = "My Courses";


	private static final String TABLE_CONTENTS = "Table Contents";

	//TODO: Add Edit Course

	private JPanel tablePanel;

	private JPanel titlePanel;

	private JButton newCourseButton;

	public CoursePageTable()
	{
		setLayout(new BorderLayout());
		add(createTitle(), BorderLayout.NORTH);
		add(createTable(), BorderLayout.CENTER);
	}

	private JPanel createTable()
	{
		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		JPanel tableHeader = new JPanel();
		tableHeader.setLayout(new GridLayout(1, 3));
		tableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		tableHeader.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
		tableHeader.add(createLabel("Course Name", SUB_TITLE_FONT, JLabel.CENTER), 0);
		tableHeader.add(createLabel("Active", SUB_TITLE_FONT, JLabel.RIGHT), 1);
		tableHeader.add(createLabel("Course Home", SUB_TITLE_FONT, JLabel.CENTER), 2);
		tablePanel.add(tableHeader, BorderLayout.NORTH);
		return tablePanel;
	}

	private JPanel createTitle() {
		titlePanel = new JPanel(new GridLayout(1, 3));
		JPanel theTitle = new JPanel();
		JLabel title = new JLabel(TABLE_TITLE);
		title.setFont(TITLE_FONT);
		theTitle.setBackground(BACKGROUND_COLOUR);
		theTitle.add(title);
		titlePanel.add(theTitle, 0);
		titlePanel.add(createButton("Add a New Course", TEXT_FONT), 1);
		return titlePanel;
	}

	private JPanel createButton(String label, Font font)
	{
		JPanel theButton = new JPanel();
		theButton.setBackground(BACKGROUND_COLOUR);
		newCourseButton = new JButton(label);
		newCourseButton.setFont(font);
		theButton.add(newCourseButton);
		return theButton;

	}
	private JPanel createLabel(String title, Font font, int alignment) {
		JPanel theLabel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(title);
		label.setPreferredSize(new Dimension(200, 30));
		label.setHorizontalAlignment(alignment);
		label.setFont(font);
		theLabel.add(label, BorderLayout.NORTH);
		return theLabel;
	}

	public void setBoxList(BoxList<CourseItem> itemDisplay)
	{
		JScrollPane scrollPane = new JScrollPane(itemDisplay);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		tablePanel.add(scrollPane, BorderLayout.CENTER);
	}

	public void setNewCourseListener(ActionListener listener)
	{
		newCourseButton.addActionListener(listener);
	}


}
