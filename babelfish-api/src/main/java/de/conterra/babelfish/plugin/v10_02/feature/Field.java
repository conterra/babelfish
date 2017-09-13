package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.domain.DomainObject;

/**
 * defines a field of {@link Layer} meta-data
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public interface Field {
	/**
	 * gives the name
	 *
	 * @return the name
	 *
	 * @since 0.1.0
	 */
	public String getName();
	
	/**
	 * gives the {@link FieldType}
	 *
	 * @return the {@link FieldType}
	 *
	 * @since 0.1.0
	 */
	public FieldType getType();
	
	/**
	 * gives the alias, which is shown to the user
	 *
	 * @return the alias
	 *
	 * @since 0.1.0
	 */
	public String getAlias();
	
	/**
	 * gives the field length
	 *
	 * @return the field length as a positive integer
	 *
	 * @since 0.1.0
	 */
	public int getLength();
	
	/**
	 * could the {@link Field} edited?
	 *
	 * @return {@code true}, if the {@link Field} could edited
	 *
	 * @since 0.1.0
	 */
	public boolean isEditable();
	
	/**
	 * gives the {@link DomainObject}
	 *
	 * @return the {@link DomainObject}
	 *
	 * @since 0.1.0
	 */
	public DomainObject getDomain();
}
