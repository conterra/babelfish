package de.conterra.babelfish.plugin.v10_02.object.labeling;

import de.conterra.babelfish.plugin.v10_02.object.labeling.placement.LabelingPlacement;
import de.conterra.babelfish.plugin.v10_02.object.symbol.TextSymbol;

/**
 * defines a {@link LabelClass}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/label.html">ArcGIS
 *      REST API</a>
 */
public class LabelClass
{
	/**
	 * the {@link LabelingPlacement}
	 * 
	 * @since 0.1
	 */
	private LabelingPlacement placement;
	/**
	 * the expression
	 * 
	 * @since 0.1
	 */
	private String expression;
	/**
	 * uses this {@link LabelClass} coded values?
	 * 
	 * @since 0.1
	 */
	private boolean codedValues = false;
	/**
	 * the {@link TextSymbol}
	 * 
	 * @since 0.1
	 */
	private TextSymbol symbol;
	/**
	 * the minimum scale to show this label
	 * 
	 * @since 0.1
	 */
	private double minScale = 0;
	/**
	 * the maximum scale to show this label
	 * 
	 * @since 0.1
	 */
	private double maxScale = 0;
	
	/**
	 * constructor, with {@link LabelingPlacement}, expression and
	 * {@link TextSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @param placement the {@link LabelingPlacement}
	 * @param expression the expression
	 * @param symbol the {@link TextSymbol}
	 */
	public LabelClass(LabelingPlacement placement, String expression, TextSymbol symbol)
	{
		this.placement = placement;
		this.expression = expression;
		this.symbol = symbol;
	}
	
	/**
	 * gives the {@link LabelingPlacement}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link LabelingPlacement}
	 */
	public LabelingPlacement getPlacement()
	{
		return this.placement;
	}
	
	/**
	 * sets the {@link LabelingPlacement}
	 * 
	 * @since 0.1
	 * 
	 * @param placement the {@link LabelingPlacement} to set
	 */
	public void setPlacement(LabelingPlacement placement)
	{
		this.placement = placement;
	}
	
	/**
	 * gives the expression
	 * 
	 * @since 0.1
	 * 
	 * @return the expression
	 */
	public String getExpression()
	{
		return this.expression;
	}
	
	/**
	 * sets the expression
	 * 
	 * @since 0.1
	 * 
	 * @param expression the expression to set
	 */
	public void setExpression(String expression)
	{
		this.expression = expression;
	}
	
	/**
	 * is this {@link LabelClass} using coded values?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if this {@link LabelClass} using coded values
	 */
	public boolean usesCodedValues()
	{
		return this.codedValues;
	}
	
	/**
	 * sets if this {@link LabelClass} using coded values
	 * 
	 * @since 0.1
	 * 
	 * @param codedValues <code>true</code>, if this {@link LabelClass} using
	 *        coded values
	 */
	public void setCodedValues(boolean codedValues)
	{
		this.codedValues = codedValues;
	}
	
	/**
	 * gives the {@link TextSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link TextSymbol}
	 */
	public TextSymbol getSymbol()
	{
		return this.symbol;
	}
	
	/**
	 * sets the {@link TextSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @param symbol the {@link TextSymbol} to set
	 */
	public void setSymbol(TextSymbol symbol)
	{
		this.symbol = symbol;
	}
	
	/**
	 * gives the minimum scale to show this label
	 * 
	 * @since 0.1
	 * 
	 * @return the minimum scale
	 */
	public double getMinScale()
	{
		return this.minScale;
	}
	
	/**
	 * sets the minimum scale to show this label
	 * 
	 * @since 0.1
	 * 
	 * @param minScale the minimum scale to set
	 */
	public void setMinScale(double minScale)
	{
		this.minScale = minScale;
	}
	
	/**
	 * gives the maximum scale to show this label
	 * 
	 * @since 0.1
	 * 
	 * @return the maximum scale
	 */
	public double getMaxScale()
	{
		return this.maxScale;
	}
	
	/**
	 * sets the maximum scale to show this label
	 * 
	 * @since 0.1
	 * 
	 * @param maxScale the the maximum scale to set
	 */
	public void setMaxScale(double maxScale)
	{
		this.maxScale = maxScale;
	}
}