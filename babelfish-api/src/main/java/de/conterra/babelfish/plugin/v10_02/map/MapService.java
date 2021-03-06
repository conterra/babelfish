package de.conterra.babelfish.plugin.v10_02.map;

import de.conterra.babelfish.plugin.v10_02.RestService;

/**
 * describes an {@link RestService}, which implements a Map Service of the ArcGIS-REST interface
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/mapserver.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public interface MapService
		extends de.conterra.babelfish.plugin.v9_3.map.MapService, RestService {
	/**
	 * the official REST API name on version 10.02
	 *
	 * @since 0.1.0
	 */
	public static final String REST_NAME = de.conterra.babelfish.plugin.v9_3.map.MapService.REST_NAME;
}