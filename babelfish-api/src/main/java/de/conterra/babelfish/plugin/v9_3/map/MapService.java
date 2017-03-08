package de.conterra.babelfish.plugin.v9_3.map;

import de.conterra.babelfish.plugin.v9_3.RestService;

/**
 * describes an {@link RestService}, which implements a Map Service of the ArcGIS-REST interface
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @see <a href="http://resources.esri.com/help/9.3/arcgisserver/apis/rest/mapserver.html">ArcGIS REST API</a>
 * @since 0.3.1
 */
public interface MapService
		extends RestService {
	/**
	 * the official REST API name on version 9.3
	 *
	 * @since 0.3.1
	 */
	public static final String REST_NAME = "MapServer";
}