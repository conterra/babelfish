package de.conterra.babelfish.plugin.v10_02.object.symbol.style;

/**
 * defines a Simple Marker Symbol style
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/symbol.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public enum SMSStyle
		implements SymbolStyle {
	/**
	 * a circle
	 *
	 * @since 0.1.0
	 */
	Circle,
	/**
	 * a cross
	 *
	 * @since 0.1.0
	 */
	Cross,
	/**
	 * a diamond symbol
	 *
	 * @since 0.1.0
	 */
	Diamond,
	/**
	 * a square
	 *
	 * @since 0.1.0
	 */
	Square,
	X;
	
	@Override
	public String toString() {
		return "esriSMS" + this.name();
	}
}