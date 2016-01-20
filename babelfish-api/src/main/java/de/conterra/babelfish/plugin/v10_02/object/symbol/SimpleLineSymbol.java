package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.Color;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SLSStyle;

/**
 * defines a Simple Line Symbol
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class SimpleLineSymbol
extends SimpleSymbol
{
	/**
	 * the style
	 * 
	 * @since 0.1
	 */
	private final SLSStyle style;
	/**
	 * the color
	 * 
	 * @since 0.1
	 */
	private ColorSymbol color;
	/**
	 * the width
	 * 
	 * @since 0.1
	 */
	private int width;
	
	/**
	 * constructor, with given {@link ColorSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @param style the style
	 * @param color the {@link ColorSymbol}
	 * @param width the width
	 */
	public SimpleLineSymbol(SLSStyle style, ColorSymbol color, int width)
	{
		this.style = style;
		this.color = color;
		this.width = width;
	}
	
	/**
	 * constructor, width given {@link Color}
	 * 
	 * @since 0.1
	 * 
	 * @param style the style
	 * @param color the color
	 * @param width the width
	 */
	public SimpleLineSymbol(SLSStyle style, Color color, int width)
	{
		this(style, new ColorSymbol(color), width);
	}
	
	@Override
	public String getType()
	{
		return super.getType() + "SLS";
	}
	
	/**
	 * gives the color
	 * 
	 * @since 0.1
	 * 
	 * @return the color
	 */
	@Override
	public ColorSymbol getColor()
	{
		return this.color;
	}
	
	/**
	 * sets the color
	 * 
	 * @since 0.1
	 * 
	 * @param color the color to set
	 */
	@Override
	public void setColor(ColorSymbol color)
	{
		this.color = color;
	}
	
	/**
	 * gives the width
	 * 
	 * @since 0.1
	 * 
	 * @return the width
	 */
	public int getWidth()
	{
		return this.width;
	}
	
	/**
	 * sets the width
	 * 
	 * @since 0.1
	 * 
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	/**
	 * gives the style
	 * 
	 * @since 0.1
	 * 
	 * @return the style
	 */
	@Override
	public SLSStyle getStyle()
	{
		return this.style;
	}
}