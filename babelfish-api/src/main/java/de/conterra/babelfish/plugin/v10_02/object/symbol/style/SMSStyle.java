package de.conterra.babelfish.plugin.v10_02.object.symbol.style;

/**
 * defines a Simple Marker Symbol style
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/symbol.html">ArcGIS
 *      REST API</a>
 */
public enum SMSStyle
implements SymbolStyle
{
	/**
	 * a circle
	 * 
	 * @since 0.1
	 */
	Circle,
	/**
	 * a cross
	 * 
	 * @since 0.1
	 */
	Cross,
	/**
	 * a diamond symbol
	 * 
	 * @since 0.1
	 */
	Diamond,
	/**
	 * a square
	 * 
	 * @since 0.1
	 */
	Square,
	X;
	
	@Override
	public String toString()
	{
		return "esriSMS" + this.name();
	}
}