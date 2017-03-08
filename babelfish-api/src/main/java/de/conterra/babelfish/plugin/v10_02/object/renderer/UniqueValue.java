package de.conterra.babelfish.plugin.v10_02.object.renderer;

import de.conterra.babelfish.plugin.v10_02.object.symbol.SymbolObject;

/**
 * defines a Unique Value
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/renderer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public class UniqueValue {
	/**
	 * the value
	 *
	 * @since 0.1.0
	 */
	private String value;
	/**
	 * the label
	 *
	 * @since 0.1.0
	 */
	private String label;
	/**
	 * a description
	 *
	 * @since 0.1.0
	 */
	private String description = "";
	/**
	 * the {@link SymbolObject}
	 *
	 * @since 0.1.0
	 */
	private SymbolObject symbol;
	
	/**
	 * constructor, with all necessary attributes
	 *
	 * @param value  the value
	 * @param label  the label
	 * @param symbol the {@link SymbolObject}
	 * @since 0.1.0
	 */
	public UniqueValue(String value, String label, SymbolObject symbol) {
		this.value = value;
		this.label = label;
		this.symbol = symbol;
	}
	
	/**
	 * gives the value
	 *
	 * @return the value
	 *
	 * @since 0.1.0
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * sets the value
	 *
	 * @param value the value to set
	 * @since 0.1.0
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * gives the label
	 *
	 * @return the label
	 *
	 * @since 0.1.0
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * sets the label
	 *
	 * @param label the label to set
	 * @since 0.1.0
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * gives the description
	 *
	 * @return the description
	 *
	 * @since 0.1.0
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * sets a description
	 *
	 * @param description a description to set
	 * @since 0.1.0
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * gives the {@link SymbolObject}
	 *
	 * @return the {@link SymbolObject}
	 *
	 * @since 0.1.0
	 */
	public SymbolObject getSymbol() {
		return this.symbol;
	}
	
	/**
	 * sets the {@link SymbolObject}
	 *
	 * @param symbol the {@link SymbolObject} to set
	 * @since 0.1.0
	 */
	public void setSymbol(SymbolObject symbol) {
		this.symbol = symbol;
	}
}