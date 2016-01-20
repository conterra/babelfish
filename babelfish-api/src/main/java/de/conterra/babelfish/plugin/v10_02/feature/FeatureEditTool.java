package de.conterra.babelfish.plugin.v10_02.feature;

import org.opengis.geometry.primitive.Ring;

import de.conterra.babelfish.plugin.v10_02.object.geometry.Polyline;

/**
 * defines a Feature Edit Tool
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS
 *      REST API</a>
 */
public enum FeatureEditTool
{
	/**
	 * an empty tool
	 * 
	 * @since 0.1
	 */
	None,
	/**
	 * a tool to create
	 * {@link de.conterra.babelfish.plugin.v10_02.object.geometry.Point}s
	 * 
	 * @since 0.1
	 */
	Point,
	/**
	 * a tool to create {@link Polyline}s
	 * 
	 * @since 0.1
	 */
	Line,
	/**
	 * a tool to create
	 * {@link de.conterra.babelfish.plugin.v10_02.object.geometry.Polygon}s
	 * 
	 * @since 0.1
	 */
	Polygon,
	/**
	 * a tool to create {@link Ring}s
	 * 
	 * @since 0.1
	 */
	AutoCompletePolygon,
	/**
	 * a tool to create {@link org.opengis.geometry.coordinate.Circle}s
	 * 
	 * @since 0.1
	 */
	Circle,
	/**
	 * a tool to create ellipses
	 * 
	 * @since 0.1
	 */
	Ellipse,
	/**
	 * a tool to create rectangles
	 * 
	 * @since 0.1
	 */
	Rectangle,
	/**
	 * a tool to draw geometries by hand
	 * 
	 * @since 0.1
	 */
	Freehand;
	
	@Override
	public String toString()
	{
		return "esriFeatureEditTool" + this.name();
	}
}