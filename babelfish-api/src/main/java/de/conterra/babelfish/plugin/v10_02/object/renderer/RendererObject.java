package de.conterra.babelfish.plugin.v10_02.object.renderer;

/**
 * defines a Renderer Object
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/renderer.html">ArcGIS
 *      REST API</a>
 */
public abstract class RendererObject
{
	/**
	 * gives the ESRI type
	 * 
	 * @since 0.1
	 * 
	 * @return the ESRI type
	 */
	public abstract String getType();
}