package de.conterra.babelfish.plugin.v10_02.object.domain;

/**
 * defines any kind of {@link DomainObject}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/domain.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public abstract class DomainObject {
	/**
	 * gives the domain type
	 *
	 * @return the domain type
	 *
	 * @since 0.1.0
	 */
	public abstract String getType();
}