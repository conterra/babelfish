package de.conterra.babelfish.plugin.v10_02.object.renderer;

import de.conterra.babelfish.plugin.v10_02.object.symbol.SymbolObject;

/**
 * defines a Class Break
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/renderer.html">ArcGIS
 *      REST API</a>
 */
public class ClassBreak
{
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
	 * the maximum value
	 * 
	 * @since 0.1
	 */
	private double maxValue;
	/**
	 * the {@link SymbolObject}
	 * 
	 * @since 0.1
	 */
	private SymbolObject symbol;
	
	/**
	 * constructor, with all necessary attributes
	 * 
	 * @since 0.1
	 * 
	 * @param label the label
	 * @param maxValue the maximum value
	 * @param symbol the {@link SymbolObject}
	 */
	public ClassBreak(String label, double maxValue, SymbolObject symbol)
	{
		this.label = label;
		this.maxValue = maxValue;
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
	
	/**
	 * gives the maximum value
	 * 
	 * @since 0.1
	 * 
	 * @return the maximum value
	 */
	public double getMaxValue()
	{
		return this.maxValue;
	}
	
	/**
	 * sets the maximum value
	 * 
	 * @since 0.1
	 * 
	 * @param maxValue the maximum value to set
	 */
	public void setMaxValue(double maxValue)
	{
		this.maxValue = maxValue;
	}
	
	/**
	 * gives the {@link SymbolObject}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link SymbolObject}
	 */
	public SymbolObject getSymbol()
	{
		return this.symbol;
	}
	
	/**
	 * sets the {@link SymbolObject}
	 * 
	 * @since 0.1
	 * 
	 * @param symbol the {@link SymbolObject} to set
	 */
	public void setSymbol(SymbolObject symbol)
	{
		this.symbol = symbol;
	}
}