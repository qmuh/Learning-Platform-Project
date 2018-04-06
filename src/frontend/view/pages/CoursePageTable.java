package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.CourseItem;
import shared.objects.Course;

public class CoursePageTable extends JPanel implements WondrisInfo, ColourPalette
{
	private static final long serialVersionUID = 1L;
	
	private static final String TABLE_TITLE = "My Courses";
	
	private static final int NEW_COURSE = 1;
	
	private static final String TABLE_CONTENTS = "Table Contents";
	
	//TODO: Add Edit Course
	
	private JPanel tablePanel;
	
	private JPanel titlePanel;
	
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
		tableHeader.add(createLabel("Active", TEXT_FONT));
		tableHeader.add(createLabel("Course Name", TEXT_FONT));
		tableHeader.add(createLabel("Course Home", TEXT_FONT));
		
		tablePanel.add(tableHeader);
		return tablePanel;
	}

	private JPanel createTitle() {
		titlePanel = new JPanel(new GridLayout(1, 3));
		titlePanel.add(createLabel(TABLE_TITLE, TITLE_FONT), 0);
		titlePanel.add(createButton("New Course", TEXT_FONT), NEW_COURSE);
		
		return titlePanel;
	}
	
	private JButton createButton(String label, Font font)
	{
		JButton button = new JButton(label);
		button.setFont(font);
		return button;
		
	}
	private JLabel createLabel(String title, Font font) {
		JLabel label = new JLabel(title);
		label.setFont(font);
		return label;
	}
	
	public void setBoxList(BoxList<CourseItem> itemDisplay)
	{
		tablePanel.add(itemDisplay, TABLE_CONTENTS);
//		}
	}
	
	public void setNewCourseListener(ActionListener listener)
	{
		((JButton)titlePanel.getComponent(NEW_COURSE)).addActionListener(listener);
	}

	
}
