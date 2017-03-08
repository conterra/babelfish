package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a {@link Template}
 *
 * @param <T> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public interface Template<T extends FeatureObject> {
	/**
	 * gives the name
	 *
	 * @return the name
	 *
	 * @since 0.1.0
	 */
	public String getName();
	
	/**
	 * gives the description
	 *
	 * @return the description
	 *
	 * @since 0.1.0
	 */
	public String getDescription();
	
	/**
	 * gives the prototyping {@link FeatureObject}
	 *
	 * @return the prototyping {@link FeatureObject}
	 *
	 * @since 0.1.0
	 */
	public T getPrototype();
	
	/**
	 * gives the {@link FeatureEditTool}
	 *
	 * @return the {@link FeatureEditTool} or {@code null} if non tool is set
	 *
	 * @since 0.1.0
	 */
	public FeatureEditTool getFeatureEditTool();
}