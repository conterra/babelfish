package de.conterra.babelfish.plugin.v10_02.feature;

import java.util.Set;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a {@link Relationship} between two {@link Layer}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <O> the {@link FeatureObject} type of the origin {@link Layer}
 * @param <D> the {@link FeatureObject} type of the destination {@link Layer}
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS
 *      REST API</a>
 */
public interface Relationship<O extends FeatureObject, D extends FeatureObject>
{
	/**
	 * gives the unique id of the {@link Relationship}
	 * 
	 * @since 0.1
	 * 
	 * @return the unique id of the {@link Relationship}
	 */
	public int getId();
	
	/**
	 * gives the name
	 * 
	 * @since 0.1
	 * 
	 * @return the name
	 */
	public String getName();
	
	/**
	 * gives the origin {@link Layer}
	 * 
	 * @since 0.1
	 * 
	 * @return the origin {@link Layer}
	 */
	public Layer<O> getOriginLayer();
	
	/**
	 * gives the destination {@link Layer}
	 * 
	 * @since 0.1
	 * 
	 * @return the destination {@link Layer}
	 */
	public Layer<D> getDestinationLayer();
	
	/**
	 * gives a {@link Set} of all related {@link Feature}s on the destination
	 * {@link Layer} of a given {@link Feature} of the origin {@link Layer}
	 * 
	 * @since 0.1
	 * 
	 * @param originFeature the {@link Feature} of the origin {@link Layer} to
	 *        get all related {@link Feature}s of
	 * @return a {@link Set} of all related {@link Feature}s of the given
	 *         <code>originFeature</code>
	 */
	public Set<? extends Feature<D>> getRelatedFeatures(Feature<? extends O> originFeature);
}