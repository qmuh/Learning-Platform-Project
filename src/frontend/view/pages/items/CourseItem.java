package frontend.view.pages.items;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pageNavigation.pages.GUIConstants;
import frontend.view.pages.components.customSwing.WButton;
import shared.objects.Course;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class CourseItem extends GeneralItem
		implements WondrisInfo, ColourPalette, GUIConstants
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Course course;
	private WButton view, active;

	public CourseItem(Course course)
	{
		super(BoxLayout.X_AXIS, Integer.toString(course.getId()));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

		this.revalidate();
		this.repaint();

		JPanel wrapper = new JPanel(new GridLayout(1, 3));
		this.course = course;
		this.setBorder(BorderFactory.createEtchedBorder());
		wrapper.add(createLabel(course.getName()));
		wrapper.add(createActiveButton());
		wrapper.add(createViewButton("View"));

		this.add(wrapper);
	}

	private JPanel createActiveButton()
	{
		JPanel activePanel = new JPanel(new BorderLayout());
		active = new WButton();
		active.setPreferredSize(new Dimension(200, 30));
		active.setFont(TEXT_FONT);
		if (course.getActive())
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
		view = new WButton(name);
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
