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

import frontend.interfaces.ColorPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.CourseItem;
import sharedobjects.Course;

public class CoursePageTable extends JPanel implements WondrisInfo, ColorPalette, GUIConstants
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
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		JPanel tableHeader = new JPanel();
		tableHeader.setLayout(new GridLayout(1, 3));
		tableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		tableHeader.add(createLabel("Course Name", SUB_TITLE_FONT), 0);
		tableHeader.add(createLabel("Active", SUB_TITLE_FONT), 1);
		tableHeader.add(createLabel("Course Home", SUB_TITLE_FONT), 2);
		tablePanel.add(tableHeader);
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
	private JPanel createLabel(String title, Font font) {
		JPanel theLabel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(title);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(font);
		theLabel.add(label, BorderLayout.NORTH);
		return theLabel;
	}
	
	public void setBoxList(BoxList<CourseItem> itemDisplay)
	{
		tablePanel.add(itemDisplay, TABLE_CONTENTS);
	}
	
	public void setNewCourseListener(ActionListener listener)
	{
		newCourseButton.addActionListener(listener);
	}

	
}
