package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.domain.DomainObject;

/**
 * defines a field of {@link Layer} meta-data
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS
 *      REST API</a>
 */
public interface Field
{
	/**
	 * gives the name
	 * 
	 * @since 0.1
	 * 
	 * @return the name
	 */
	public String getName();
	
	/**
	 * gives the {@link FieldType}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link FieldType}
	 */
	public FieldType getType();
	
	/**
	 * gives the alias, which is shown to the user
	 * 
	 * @since 0.1
	 * 
	 * @return the alias
	 */
	public String getAlias();
	
	/**
	 * gives the field length
	 * 
	 * @since 0.1
	 * 
	 * @return the field length
	 */
	public int getLength();
	
	/**
	 * could the {@link Field} edited?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if the {@link Field} could edited
	 */
	public boolean isEditable();
	
	/**
	 * gives the {@link DomainObject}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link DomainObject}
	 */
	public DomainObject getDomain();
}