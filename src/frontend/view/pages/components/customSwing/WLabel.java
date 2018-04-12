package frontend.view.pages.components.customSwing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import frontend.interfaces.WondrisInfo;

public class WLabel extends JLabel implements WondrisInfo
{

	private static final long serialVersionUID = 1L;

	public WLabel(String text, int alignment)
	{
		this.setFont(TEXT_FONT);
		this.setForeground(Color.BLACK);
	}

	public WLabel(String text, Font font, Color color, int alignment)
	{
		super(text, alignment);
		this.setFont(font);
		this.setForeground(color);
	}

	public WLabel(String text)
	{
		this(text, TEXT_FONT);
	}

	public WLabel()
	{
		this("");
	}

	public WLabel(String text, Font font)
	{
		this(text, font, Color.BLACK, CENTER);
	}

	public WLabel(String text, Color color)
	{
		this(text, TEXT_FONT, color, CENTER);
	}

	public WLabel(String text, Font font, int right)
	{
		this(text, font, Color.BLACK, right);
	}
}
