package de.conterra.babelfish.plugin.v10_02.object.renderer;

import de.conterra.babelfish.plugin.v10_02.object.symbol.SymbolObject;

/**
 * defines a Simple Renderer
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class SimpleRenderer
extends RendererObject
{
	/**
	 * the {@link SymbolObject} to use
	 * 
	 * @since 0.1
	 */
	private SymbolObject symbol;
	/**
	 * the label
	 * 
	 * @since 0.1
	 */
	private String label;
	/**
	 * a description
	 * 
	 * @since 0.1
	 */
	private String description = "";
	
	/**
	 * constructor, with given label
	 * 
	 * @since 0.1
	 * 
	 * @param symbol the {@link SymbolObject} to use
	 * @param label the label
	 */
	public SimpleRenderer(SymbolObject symbol, String label)
	{
		this.symbol = symbol;
		this.label = label;
	}
	
	@Override
	public String getType()
	{
		return "simple";
	}
	
	/**
	 * gives the {@link SymbolObject} to use
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link SymbolObject} to use
	 */
	public SymbolObject getSymbol()
	{
		return this.symbol;
	}
	
	/**
	 * sets the {@link SymbolObject} to use
	 * 
	 * @since 0.1
	 * 
	 * @param symbol the {@link SymbolObject} to set
	 */
	public void setSymbol(SymbolObject symbol)
	{
		this.symbol = symbol;
	}
	
	/**
	 * gives the label
	 * 
	 * @since 0.1
	 * 
	 * @return the label
	 */
	public String getLabel()
	{
		return this.label;
	}
	
	/**
	 * sets the label
	 * 
	 * @since 0.1
	 * 
	 * @param label the label to set
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	/**
	 * gives the description
	 * 
	 * @since 0.1
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * sets a description
	 * 
	 * @since 0.1
	 * 
	 * @param description a description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
}