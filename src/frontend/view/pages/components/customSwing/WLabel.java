package frontend.view.pages.components.customSwing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import frontend.interfaces.WondrisInfo;

public class WLabel extends JLabel implements WondrisInfo
{
	
	private static final long serialVersionUID = 1L;

	public WLabel (String text, int alignment)
	{
		super(text, alignment);
		this.setFont(TEXT_FONT);
		this.setForeground(Color.BLACK);
	}
	
	public WLabel(String text, Font font, Color color)
	{
		super(text);
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
		this(text, font, Color.BLACK);
	}
	
	public WLabel(String text, Color color)
	{
		this(text, TEXT_FONT, color);
	}
}
