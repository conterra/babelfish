package de.conterra.babelfish.plugin.v10_02.object.renderer;

import de.conterra.babelfish.plugin.v10_02.object.symbol.SymbolObject;

/**
 * defines a Unique Value
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/renderer.html">ArcGIS
 *      REST API</a>
 */
public class UniqueValue
{
	/**
	 * the value
	 * 
	 * @since 0.1
	 */
	private String value;
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
	 * @param value the value
	 * @param label the label
	 * @param symbol the {@link SymbolObject}
	 */
	public UniqueValue(String value, String label, SymbolObject symbol)
	{
		this.value = value;
		this.label = label;
		this.symbol = symbol;
	}
	
	/**
	 * gives the value
	 * 
	 * @since 0.1
	 * 
	 * @return the value
	 */
	public String getValue()
	{
		return this.value;
	}
	
	/**
	 * sets the value
	 * 
	 * @since 0.1
	 * 
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
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