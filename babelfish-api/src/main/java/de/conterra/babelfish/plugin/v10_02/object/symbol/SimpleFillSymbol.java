package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.Color;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SFSStyle;

/**
 * defines a Simple Fill Symbol
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class SimpleFillSymbol
extends SimpleSymbol
{
	/**
	 * the style
	 * 
	 * @since 0.1
	 */
	private final SFSStyle style;
	/**
	 * the color
	 * 
	 * @since 0.1
	 */
	private ColorSymbol color;
	/**
	 * the outline
	 * 
	 * @since 0.1
	 */
	private SimpleLineSymbol outline;
	
	/**
	 * constructor, with given {@link ColorSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @param style the style
	 * @param color the {@link ColorSymbol}
	 * @param outline the outline
	 */
	public SimpleFillSymbol(SFSStyle style, ColorSymbol color, SimpleLineSymbol outline)
	{
		this.style = style;
		this.color = color;
		this.outline = outline;
	}
	
	/**
	 * constructor, with given {@link Color}
	 * 
	 * @since 0.1
	 * 
	 * @param style the style
	 * @param color the {@link Color}
	 * @param outline the outline
	 */
	public SimpleFillSymbol(SFSStyle style, Color color, SimpleLineSymbol outline)
	{
		this(style, new ColorSymbol(color), outline);
	}
	
	@Override
	public String getType()
	{
		return super.getType() + "SFS";
	}
	
	/**
	 * gives the style
	 * 
	 * @since 0.1
	 * 
	 * @return the style
	 */
	@Override
	public SFSStyle getStyle()
	{
		return this.style;
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
	 * gives the outline
	 * 
	 * @since 0.1
	 * 
	 * @return the outline
	 */
	public SimpleLineSymbol getOutline()
	{
		return this.outline;
	}
	
	/**
	 * sets the outline
	 * 
	 * @since 0.1
	 * 
	 * @param outline the outline to set
	 */
	public void setOutline(SimpleLineSymbol outline)
	{
		this.outline = outline;
	}
}