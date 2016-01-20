package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.Color;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SMSStyle;

/**
 * defines a Simple Marker Symbol
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class SimpleMarkerSymbol
extends SimpleSymbol
{
	/**
	 * the style
	 * 
	 * @since 0.1
	 */
	private final SMSStyle style;
	/**
	 * the color
	 * 
	 * @since 0.1
	 */
	private ColorSymbol color;
	/**
	 * the size
	 * 
	 * @since 0.1
	 */
	private int size;
	/**
	 * the angle
	 * 
	 * @since 0.1
	 */
	private double angle = 0;
	/**
	 * the offset in x-direction
	 * 
	 * @since 0.1
	 */
	private int xOffset = 0;
	/**
	 * the offset in y-direction
	 * 
	 * @since 0.1
	 */
	private int yOffset = 0;
	/**
	 * the outline
	 * 
	 * @since 0.1
	 */
	private Outline outline = null;
	
	/**
	 * constructor, with given style, size and {@link ColorSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @param style the style
	 * @param size the size
	 * @param color the {@link ColorSymbol}
	 * @see SimpleMarkerSymbol#SimpleMarkerSymbol(SMSStyle, int, Color)
	 */
	public SimpleMarkerSymbol(SMSStyle style, int size, ColorSymbol color)
	{
		this.style = style;
		this.size = size;
		this.color = color;
	}
	
	/**
	 * constructor, with given style, size and {@link Color}
	 * 
	 * @since 0.1
	 * 
	 * @param style the style
	 * @param size the size
	 * @param color the {@link Color}
	 * @see SimpleMarkerSymbol#SimpleMarkerSymbol(SMSStyle, int, ColorSymbol)
	 */
	public SimpleMarkerSymbol(SMSStyle style, int size, Color color)
	{
		this(style, size, new ColorSymbol(color));
	}
	
	/**
	 * constructor, with given style and {@link ColorSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @param style the style
	 * @param color the {@link ColorSymbol}
	 * @see SimpleMarkerSymbol#SimpleMarkerSymbol(SMSStyle, Color)
	 */
	public SimpleMarkerSymbol(SMSStyle style, ColorSymbol color)
	{
		this(style, 8, color);
	}
	
	/**
	 * constructor, with given style and {@link Color}
	 * 
	 * @since 0.1
	 * 
	 * @param style the style
	 * @param color the {@link Color}
	 * @see SimpleMarkerSymbol#SimpleMarkerSymbol(SMSStyle, ColorSymbol)
	 */
	public SimpleMarkerSymbol(SMSStyle style, Color color)
	{
		this(style, new ColorSymbol(color));
	}
	
	@Override
	public String getType()
	{
		return super.getType() + "SMS";
	}
	
	/**
	 * gives the style
	 * 
	 * @since 0.1
	 * 
	 * @return the style
	 */
	@Override
	public SMSStyle getStyle()
	{
		return this.style;
	}
	
	/**
	 * gives the {@link ColorSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link ColorSymbol}
	 */
	@Override
	public ColorSymbol getColor()
	{
		return this.color;
	}
	
	/**
	 * sets the {@link ColorSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @param color the {@link ColorSymbol} to set
	 */
	@Override
	public void setColor(ColorSymbol color)
	{
		this.color = color;
	}
	
	/**
	 * gives the size
	 * 
	 * @since 0.1
	 * 
	 * @return the size
	 */
	public int getSize()
	{
		return this.size;
	}
	
	/**
	 * sets the size
	 * 
	 * @since 0.1
	 * 
	 * @param size the size to set
	 */
	public void setSize(int size)
	{
		this.size = size;
	}
	
	/**
	 * gives the angle
	 * 
	 * @since 0.1
	 * 
	 * @return the angle
	 */
	public double getAngle()
	{
		return this.angle;
	}
	
	/**
	 * sets the angle
	 * 
	 * @since 0.1
	 * 
	 * @param angle the angle to set
	 */
	public void setAngle(double angle)
	{
		this.angle = angle;
	}
	
	/**
	 * gives the offset in x-direction
	 * 
	 * @since 0.1
	 * 
	 * @return the offset in x-direction
	 */
	public int getxOffset()
	{
		return this.xOffset;
	}
	
	/**
	 * sets the offset in x-direction
	 * 
	 * @since 0.1
	 * 
	 * @param xOffset the offset to set
	 */
	public void setxOffset(int xOffset)
	{
		this.xOffset = xOffset;
	}
	
	/**
	 * gives the offset in y-direction
	 * 
	 * @since 0.1
	 * 
	 * @return the offset in y-direction
	 */
	public int getyOffset()
	{
		return this.yOffset;
	}
	
	/**
	 * sets the offset in y-direction
	 * 
	 * @since 0.1
	 * 
	 * @param yOffset the offset to set
	 */
	public void setyOffset(int yOffset)
	{
		this.yOffset = yOffset;
	}
	
	/**
	 * gives the outline
	 * 
	 * @since 0.1
	 * 
	 * @return the outline or <code>null</code>, this {@link SimpleMarkerSymbol}
	 *         has no outline
	 */
	public Outline getOutline()
	{
		return this.outline;
	}
	
	/**
	 * sets the outline
	 * 
	 * @since 0.1
	 * 
	 * @param outline the outline to set or <code>null</code>, set this
	 *        {@link SimpleMarkerSymbol} to have no outline
	 */
	public void setOutline(Outline outline)
	{
		this.outline = outline;
	}
}