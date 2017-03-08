package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

import java.util.Set;

/**
 * describes a {@link Relationship} between two {@link Layer}
 *
 * @param <O> the {@link FeatureObject} type of the origin {@link Layer}
 * @param <D> the {@link FeatureObject} type of the destination {@link Layer}
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public interface Relationship<O extends FeatureObject, D extends FeatureObject> {
	/**
	 * gives the unique id of the {@link Relationship}
	 *
	 * @return the unique id of the {@link Relationship}
	 *
	 * @since 0.1.0
	 */
	public int getId();
	
	/**
	 * gives the name
	 *
	 * @return the name
	 *
	 * @since 0.1.0
	 */
	public String getName();
	
	/**
	 * gives the origin {@link Layer}
	 *
	 * @return the origin {@link Layer}
	 *
	 * @since 0.1.0
	 */
	public Layer<O> getOriginLayer();
	
	/**
	 * gives the destination {@link Layer}
	 *
	 * @return the destination {@link Layer}
	 *
	 * @since 0.1.0
	 */
	public Layer<D> getDestinationLayer();
	
	/**
	 * gives a {@link Set} of all related {@link Feature}s on the destination {@link Layer} of a given {@link Feature} of the origin {@link Layer}
	 *
	 * @param originFeature the {@link Feature} of the origin {@link Layer} to get all related {@link Feature}s of
	 * @return a {@link Set} of all related {@link Feature}s of the given {@code originFeature}
	 *
	 * @since 0.1.0
	 */
	public Set<? extends Feature<D>> getRelatedFeatures(Feature<? extends O> originFeature);
}