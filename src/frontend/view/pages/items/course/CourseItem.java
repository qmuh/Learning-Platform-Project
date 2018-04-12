package frontend.view.pages.items.course;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import frontend.view.pages.interfaces.GUIConstants;
import frontend.view.pages.items.GeneralItem;
import shared.objects.Course;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
abstract public class CourseItem extends GeneralItem
		implements WondrisInfo, ColourPalette, GUIConstants
{
	private static final long serialVersionUID = 1L;
	protected Course course;
	protected WButton viewButton;

	public CourseItem(Course course)
	{
		super(BoxLayout.X_AXIS, Integer.toString(course.getId()));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

		this.revalidate();
		this.repaint();

		this.course = course;
		this.setBorder(BorderFactory.createEtchedBorder());
	}

	protected JPanel createLabel(String name)
	{
		JPanel theLabel = new JPanel();
		WLabel label = new WLabel(name);
		theLabel.add(label);
		return theLabel;
	}

	protected JPanel createViewButton(String name)
	{
		JPanel theButton = new JPanel();
		viewButton = new WButton(name);
		viewButton.setFont(TEXT_FONT);
		theButton.add(viewButton);
		return theButton;
	}

	public WButton getViewButton()
	{
		return viewButton;
	}

	@Override
	public int getId()
	{
		return course.getId();
	}
}
