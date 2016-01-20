package de.conterra.babelfish.plugin.v10_02.object.symbol;

/**
 * defines the base version of a {@link SymbolObject}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public abstract class BaseSymbol
extends SymbolObject
{
	/**
	 * the {@link ColorSymbol}
	 * 
	 * @since 0.1
	 */
	private ColorSymbol color = null;
	
	/**
	 * gives the ESRI type
	 * 
	 * @since 0.1
	 * 
	 * @return the ESRI type
	 */
	public String getType()
	{
		return "esri";
	}
	
	/**
	 * gives the {@link ColorSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link ColorSymbol}
	 */
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
	public void setColor(ColorSymbol color)
	{
		this.color = color;
	}
}