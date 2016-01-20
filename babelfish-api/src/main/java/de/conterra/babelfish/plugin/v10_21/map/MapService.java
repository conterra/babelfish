package de.conterra.babelfish.plugin.v10_21.map;

import de.conterra.babelfish.plugin.v10_21.RestService;

/**
 * describes an {@link RestService}, which implements a Map Service of the
 * ArcGIS-REST interface
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.3.1
 * 
 * @see <a
 *      href="http://resources.arcgis.com/en/help/arcgis-rest-api/index.html#/Map_Service/02r3000000w2000000/">ArcGIS
 *      REST API</a>
 */
public interface MapService
extends de.conterra.babelfish.plugin.v10_11.map.MapService, RestService
{
	/**
	 * the official REST API name on version 10.21
	 * 
	 * @since 0.3.1
	 */
	public static final String REST_NAME = de.conterra.babelfish.plugin.v10_11.map.MapService.REST_NAME;
}