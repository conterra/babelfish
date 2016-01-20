package de.conterra.babelfish.plugin.v10_02.feature;

import java.util.Collection;
import java.util.Map;

import de.conterra.babelfish.plugin.v10_02.object.domain.DomainObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a {@link Layer} sub-type
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the {@link FeatureObject} type
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS
 *      REST API</a>
 */
public interface Type<T extends FeatureObject>
{
	/**
	 * gives the unique identifier
	 * 
	 * @since 0.1
	 * 
	 * @return the unique id
	 */
	public String getId();
	
	/**
	 * gives the name
	 * 
	 * @since 0.1
	 * 
	 * @return the name
	 */
	public String getName();
	
	/**
	 * gives a {@link Map} of all {@link DomainObject}s
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Map} of all {@link DomainObject}s
	 */
	public Map<? extends String, ? extends DomainObject> getDomains();
	
	/**
	 * gives a {@link Collection} of all {@link Template}s
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Collection} of all {@link Template}s
	 */
	public Collection<? extends Template<T>> getTemplates();
}