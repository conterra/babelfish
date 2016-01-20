package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.Color;
import java.awt.Font;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.HorizontalAlignment;
import de.conterra.babelfish.plugin.v10_02.object.symbol.style.VerticalAlignment;

/**
 * defines a Text Symbol
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class TextSymbol
extends MovableSymbol
{
	/**
	 * the text color
	 * 
	 * @since 0.1
	 */
	private ColorSymbol textColor = new ColorSymbol(Color.BLACK);
	/**
	 * the background color
	 * 
	 * @since 0.1
	 */
	private ColorSymbol bgColor = null;
	/**
	 * the color of the border line
	 * 
	 * @since 0.1
	 */
	private ColorSymbol borderLineColor = null;
	/**
	 * the {@link VerticalAlignment}
	 * 
	 * @since 0.1
	 */
	private VerticalAlignment vAlign = VerticalAlignment.BASELINE;
	/**
	 * the {@link HorizontalAlignment}
	 * 
	 * @since 0.1
	 */
	private HorizontalAlignment hAlign = HorizontalAlignment.LEFT;
	/**
	 * should the text written from right left?
	 * 
	 * @since 0.1
	 */
	private boolean rightToLeft = false;
	/**
	 * should a kerning applied to the text?
	 * 
	 * @since 0.1
	 */
	private boolean kerning = true;
	/**
	 * the {@link Font}
	 * 
	 * @since 0.1
	 */
	private Font font;
	
	/**
	 * constructor, with given {@link Font}
	 * 
	 * @since 0.1
	 * 
	 * @param font the {@link Font}
	 */
	public TextSymbol(Font font)
	{
		this.font = font;
	}
	
	@Override
	public String getType()
	{
		return super.getType() + "TS";
	}
	
	/**
	 * gives the text color
	 * 
	 * @since 0.1
	 * 
	 * @return the text color
	 */
	public ColorSymbol getTextColor()
	{
		return this.textColor;
	}
	
	/**
	 * sets the text color
	 * 
	 * @since 0.1
	 * 
	 * @param textColor the text color to set
	 */
	public void setTextColor(ColorSymbol textColor)
	{
		this.textColor = textColor;
	}
	
	/**
	 * gives the background color
	 * 
	 * @since 0.1
	 * 
	 * @return the background color
	 */
	public ColorSymbol getBgColor()
	{
		return this.bgColor;
	}
	
	/**
	 * sets the background color
	 * 
	 * @since 0.1
	 * 
	 * @param bgColor the background color to set
	 */
	public void setBgColor(ColorSymbol bgColor)
	{
		this.bgColor = bgColor;
	}
	
	/**
	 * gives the color of the border line
	 * 
	 * @since 0.1
	 * 
	 * @return the color of the border line
	 */
	public ColorSymbol getBorderLineColor()
	{
		return this.borderLineColor;
	}
	
	/**
	 * sets the color of the border line
	 * 
	 * @since 0.1
	 * 
	 * @param borderLineColor the color to set
	 */
	public void setBorderLineColor(ColorSymbol borderLineColor)
	{
		this.borderLineColor = borderLineColor;
	}
	
	/**
	 * gives the {@link VerticalAlignment}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link VerticalAlignment}
	 */
	public VerticalAlignment getvAlign()
	{
		return this.vAlign;
	}
	
	/**
	 * sets the {@link VerticalAlignment}
	 * 
	 * @since 0.1
	 * 
	 * @param vAlign the {@link VerticalAlignment} to set
	 */
	public void setvAlign(VerticalAlignment vAlign)
	{
		this.vAlign = vAlign;
	}
	
	/**
	 * gives the {@link HorizontalAlignment}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link HorizontalAlignment}
	 */
	public HorizontalAlignment gethAlign()
	{
		return this.hAlign;
	}
	
	/**
	 * sets the {@link HorizontalAlignment}
	 * 
	 * @since 0.1
	 * 
	 * @param hAlign the {@link HorizontalAlignment} to set
	 */
	public void sethAlign(HorizontalAlignment hAlign)
	{
		this.hAlign = hAlign;
	}
	
	/**
	 * should the text written from right left?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if the text should be written from right left
	 */
	public boolean isRightToLeft()
	{
		return this.rightToLeft;
	}
	
	/**
	 * sets if the text should be written from right left
	 * 
	 * @since 0.1
	 * 
	 * @param rightToLeft <code>true</code>, if the text should be written from
	 *        right to left
	 */
	public void setRightToLeft(boolean rightToLeft)
	{
		this.rightToLeft = rightToLeft;
	}
	
	/**
	 * should a kerning applied to the text?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if a kerning should applied to the text
	 */
	public boolean isKerning()
	{
		return this.kerning;
	}
	
	/**
	 * sets if a kerning should applied to the text
	 * 
	 * @since 0.1
	 * 
	 * @param kerning <code>true</code>, if a kerning should applied to the text
	 */
	public void setKerning(boolean kerning)
	{
		this.kerning = kerning;
	}
	
	/**
	 * gives the font
	 * 
	 * @since 0.1
	 * 
	 * @return the font
	 */
	public Font getFont()
	{
		return this.font;
	}
	
	/**
	 * sets the {@link Font}
	 * 
	 * @since 0.1
	 * 
	 * @param font the {@link Font} to set
	 */
	public void setFont(Font font)
	{
		this.font = font;
	}
}