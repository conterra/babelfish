package de.conterra.babelfish.plugin.v10_02.object.renderer;

import de.conterra.babelfish.plugin.v10_02.object.symbol.SymbolObject;
import de.conterra.babelfish.util.StringUtils;

/**
 * defines a Class Break
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/renderer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public class ClassBreak {
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
	private String description = StringUtils.EMPTY;
	/**
	 * the maximum value
	 *
	 * @since 0.1.0
	 */
	private double       maxValue;
	/**
	 * the {@link SymbolObject}
	 *
	 * @since 0.1.0
	 */
	private SymbolObject symbol;
	
	/**
	 * constructor, with all necessary attributes
	 *
	 * @param label    the label
	 * @param maxValue the maximum value
	 * @param symbol   the {@link SymbolObject}
	 * @since 0.1.0
	 */
	public ClassBreak(String label, double maxValue, SymbolObject symbol) {
		this.label = label;
		this.maxValue = maxValue;
		this.symbol = symbol;
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
	 * gives the maximum value
	 *
	 * @return the maximum value
	 *
	 * @since 0.1.0
	 */
	public double getMaxValue() {
		return this.maxValue;
	}
	
	/**
	 * sets the maximum value
	 *
	 * @param maxValue the maximum value to set
	 * @since 0.1.0
	 */
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
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
