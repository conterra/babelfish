package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.Color;

/**
 * defines a {@link ColorSymbol}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class ColorSymbol
extends SymbolObject
{
	/**
	 * the {@link Color}
	 * 
	 * @since 0.1
	 */
	private final Color color;
	
	/**
	 * constructor, with given {@link Color}
	 * 
	 * @since 0.1
	 * 
	 * @param color the {@link Color}
	 */
	public ColorSymbol(Color color)
	{
		this.color = color;
	}
	
	/**
	 * gives the {@link Color}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Color}
	 */
	public java.awt.Color getColor()
	{
		return this.color;
	}
}