package frontend.view.pages.items;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.xml.internal.ws.api.Component;

import frontend.interfaces.ColorPalette;
import frontend.interfaces.WondrisInfo;
import sharedobjects.Course;

public class CourseItem extends GeneralItem implements WondrisInfo, ColorPalette
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Course course;
	private JButton view, active;

	public CourseItem(Course course)
	{
		super(BoxLayout.X_AXIS, Integer.toString(course.getId()));
		this.course = course;
		this.setBorder(BorderFactory.createEtchedBorder());
		this.add(createLabel(course.getName()));
		this.add(createActiveButton());
		this.add(createViewButton("View"));
	}

	private JPanel createActiveButton()
	{
		JPanel activePanel = new JPanel();
		active = new JButton();
		active.setHorizontalAlignment(SwingConstants.RIGHT);
		active.setFont(TEXT_FONT);
		if(course.getActive())
		{
			active.setText("DEACTIVATE");
			active.setBackground(CONTRAST_COLOR);
		} else
		{
			active.setText("ACTIVATE");
			active.setBackground(BACKGROUND_COLOUR);
		}
		activePanel.add(active, BorderLayout.EAST);
		return activePanel;
	}

	private JPanel createLabel(String name)
	{
		JPanel theLabel = new JPanel();
		JLabel label = new JLabel(name);
		label.setFont(TEXT_FONT);
		theLabel.add(label);
		return theLabel;
	}

	private JPanel createViewButton(String name)
	{
		JPanel theButton = new JPanel();
		view = new JButton(name);
		view.setFont(TEXT_FONT);
		theButton.add(view);
		return theButton;
	}

	public void setViewButtonListener(ActionListener listener)
	{
		view.addActionListener(listener);
	}
	
	public void setActiveButtonListener(ActionListener listener)
	{
		active.addActionListener(listener);
	}
	
	@Override
	public int getId()
	{
		return course.getId();
	}
}
