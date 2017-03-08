package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.domain.DomainObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

import java.util.Collection;
import java.util.Map;

/**
 * describes a {@link Layer} sub-type
 *
 * @param <T> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public interface Type<T extends FeatureObject> {
	/**
	 * gives the unique identifier
	 *
	 * @return the unique id
	 *
	 * @since 0.1.0
	 */
	public String getId();
	
	/**
	 * gives the name
	 *
	 * @return the name
	 *
	 * @since 0.1.0
	 */
	public String getName();
	
	/**
	 * gives a {@link Map} of all {@link DomainObject}s
	 *
	 * @return a {@link Map} of all {@link DomainObject}s
	 *
	 * @since 0.1.0
	 */
	public Map<? extends String, ? extends DomainObject> getDomains();
	
	/**
	 * gives a {@link Collection} of all {@link Template}s
	 *
	 * @return a {@link Collection} of all {@link Template}s
	 *
	 * @since 0.1.0
	 */
	public Collection<? extends Template<T>> getTemplates();
}