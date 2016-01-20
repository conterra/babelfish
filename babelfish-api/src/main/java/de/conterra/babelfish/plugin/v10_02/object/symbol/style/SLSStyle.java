package de.conterra.babelfish.plugin.v10_02.object.symbol.style;

/**
 * defines a Simple Line Symbol style
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/symbol.html">ArcGIS
 *      REST API</a>
 */
public enum SLSStyle
implements SymbolStyle
{
	/**
	 * non-visible line
	 * 
	 * @since 0.1
	 */
	Null,
	/**
	 * dotted line
	 * 
	 * @since 0.1
	 */
	Dot,
	/**
	 * line with a sequence of one dash and two points
	 * 
	 * @since 0.1
	 */
	DashDotDot,
	/**
	 * dashed line
	 * 
	 * @since 0.1
	 */
	Dash,
	/**
	 * line without interruptions
	 * 
	 * @since 0.1
	 */
	Solid;
	
	@Override
	public String toString()
	{
		return "esriSLS" + this.name();
	}
}