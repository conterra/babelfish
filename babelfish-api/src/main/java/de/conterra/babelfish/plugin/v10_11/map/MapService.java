package de.conterra.babelfish.plugin.v10_11.map;

import de.conterra.babelfish.plugin.v10_11.RestService;

/**
 * describes an {@link RestService}, which implements a Map Service of the
 * ArcGIS-REST interface
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://resources.arcgis.com/en/help/rest/apiref/mapserver.html">ArcGIS
 *      REST API</a>
 */
public interface MapService
extends de.conterra.babelfish.plugin.v10_02.map.MapService, RestService
{
	/**
	 * the official REST API name on version 10.11
	 * 
	 * @since 0.1
	 */
	public static final String REST_NAME = de.conterra.babelfish.plugin.v10_02.map.MapService.REST_NAME;
}