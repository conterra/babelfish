package de.conterra.babelfish.plugin.v10_02.object.labeling;

import de.conterra.babelfish.plugin.v10_02.object.labeling.placement.LabelingPlacement;
import de.conterra.babelfish.plugin.v10_02.object.symbol.TextSymbol;

/**
 * defines a {@link LabelClass}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/label.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public class LabelClass {
	/**
	 * the {@link LabelingPlacement}
	 *
	 * @since 0.1.0
	 */
	private LabelingPlacement placement;
	/**
	 * the expression
	 *
	 * @since 0.1.0
	 */
	private String expression;
	/**
	 * uses this {@link LabelClass} coded values?
	 *
	 * @since 0.1.0
	 */
	private boolean codedValues = false;
	/**
	 * the {@link TextSymbol}
	 *
	 * @since 0.1.0
	 */
	private TextSymbol symbol;
	/**
	 * the minimum scale to show this label
	 *
	 * @since 0.1.0
	 */
	private double minScale = 0;
	/**
	 * the maximum scale to show this label
	 *
	 * @since 0.1.0
	 */
	private double maxScale = 0;
	
	/**
	 * constructor, with {@link LabelingPlacement}, expression and {@link TextSymbol}
	 *
	 * @param placement  the {@link LabelingPlacement}
	 * @param expression the expression
	 * @param symbol     the {@link TextSymbol}
	 * @since 0.1.0
	 */
	public LabelClass(LabelingPlacement placement, String expression, TextSymbol symbol) {
		this.placement = placement;
		this.expression = expression;
		this.symbol = symbol;
	}
	
	/**
	 * gives the {@link LabelingPlacement}
	 *
	 * @return the {@link LabelingPlacement}
	 *
	 * @since 0.1.0
	 */
	public LabelingPlacement getPlacement() {
		return this.placement;
	}
	
	/**
	 * sets the {@link LabelingPlacement}
	 *
	 * @param placement the {@link LabelingPlacement} to set
	 * @since 0.1.0
	 */
	public void setPlacement(LabelingPlacement placement) {
		this.placement = placement;
	}
	
	/**
	 * gives the expression
	 *
	 * @return the expression
	 *
	 * @since 0.1.0
	 */
	public String getExpression() {
		return this.expression;
	}
	
	/**
	 * sets the expression
	 *
	 * @param expression the expression to set
	 * @since 0.1.0
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	/**
	 * is this {@link LabelClass} using coded values?
	 *
	 * @return {@code true}, if this {@link LabelClass} using coded values
	 *
	 * @since 0.1.0
	 */
	public boolean usesCodedValues() {
		return this.codedValues;
	}
	
	/**
	 * sets if this {@link LabelClass} using coded values
	 *
	 * @param codedValues {@code true}, if this {@link LabelClass} using coded values
	 * @since 0.1.0
	 */
	public void setCodedValues(boolean codedValues) {
		this.codedValues = codedValues;
	}
	
	/**
	 * gives the {@link TextSymbol}
	 *
	 * @return the {@link TextSymbol}
	 *
	 * @since 0.1.0
	 */
	public TextSymbol getSymbol() {
		return this.symbol;
	}
	
	/**
	 * sets the {@link TextSymbol}
	 *
	 * @param symbol the {@link TextSymbol} to set
	 * @since 0.1.0
	 */
	public void setSymbol(TextSymbol symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * gives the minimum scale to show this label
	 *
	 * @return the minimum scale
	 *
	 * @since 0.1.0
	 */
	public double getMinScale() {
		return this.minScale;
	}
	
	/**
	 * sets the minimum scale to show this label
	 *
	 * @param minScale the minimum scale to set
	 * @since 0.1.0
	 */
	public void setMinScale(double minScale) {
		this.minScale = minScale;
	}
	
	/**
	 * gives the maximum scale to show this label
	 *
	 * @return the maximum scale
	 *
	 * @since 0.1.0
	 */
	public double getMaxScale() {
		return this.maxScale;
	}
	
	/**
	 * sets the maximum scale to show this label
	 *
	 * @param maxScale the the maximum scale to set
	 * @since 0.1.0
	 */
	public void setMaxScale(double maxScale) {
		this.maxScale = maxScale;
	}
}