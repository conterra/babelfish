package de.conterra.babelfish.plugin.v10_02.object.symbol.style;

/**
 * defines a Simple Line Symbol style
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/symbol.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public enum SLSStyle
		implements SymbolStyle {
	/**
	 * non-visible line
	 *
	 * @since 0.1.0
	 */
	Null,
	/**
	 * dotted line
	 *
	 * @since 0.1.0
	 */
	Dot,
	/**
	 * line with a sequence of one dash and two points
	 *
	 * @since 0.1.0
	 */
	DashDotDot,
	/**
	 * dashed line
	 *
	 * @since 0.1.0
	 */
	Dash,
	/**
	 * line without interruptions
	 *
	 * @since 0.1.0
	 */
	Solid;
	
	@Override
	public String toString() {
		return "esriSLS" + this.name();
	}
}