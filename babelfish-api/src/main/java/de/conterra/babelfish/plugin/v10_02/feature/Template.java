package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a {@link Template}
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
public interface Template<T extends FeatureObject>
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
	 * gives the description
	 * 
	 * @since 0.1
	 * 
	 * @return the description
	 */
	public String getDescription();
	
	/**
	 * gives the prototyping {@link FeatureObject}
	 * 
	 * @since 0.1
	 * 
	 * @return the prototyping {@link FeatureObject}
	 */
	public T getPrototype();
	
	/**
	 * gives the {@link FeatureEditTool}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link FeatureEditTool} or <code>null</code> if non tool is
	 *         set
	 */
	public FeatureEditTool getFeatureEditTool();
}