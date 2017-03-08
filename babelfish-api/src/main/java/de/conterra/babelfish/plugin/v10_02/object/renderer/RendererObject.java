package de.conterra.babelfish.plugin.v10_02.object.renderer;

/**
 * defines a Renderer Object
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/renderer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public abstract class RendererObject {
	/**
	 * gives the ESRI type
	 *
	 * @return the ESRI type
	 *
	 * @since 0.1.0
	 */
	public abstract String getType();
}