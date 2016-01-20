package de.conterra.babelfish.plugin.v10_02.object.domain;

/**
 * defines any kind of {@link DomainObject}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/domain.html">ArcGIS
 *      REST API</a>
 */
public abstract class DomainObject
{
	/**
	 * gives the domain type
	 * 
	 * @since 0.1
	 * 
	 * @return the domain type
	 */
	public abstract String getType();
}