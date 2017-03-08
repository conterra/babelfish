package de.conterra.babelfish.plugin.v10_11.feature;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;

/**
 * describes a feature layer
 *
 * @param <G> the geometry type
 * @param <F> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public interface FeatureLayer<G extends GeometryObject, F extends GeometryFeatureObject<G>>
		extends de.conterra.babelfish.plugin.v10_02.feature.FeatureLayer<G, F>,
		Layer<F> {
	/**
	 * gives the effective minimum scale
	 *
	 * @return the effective minimum scale
	 *
	 * @since 0.1.0
	 */
	public int getEffectiveMinScale();
	
	/**
	 * gives the effective maximum scale
	 *
	 * @return the effective maximum scale
	 *
	 * @since 0.1.0
	 */
	public int getEffectiveMaxScale();
	
	/**
	 * gives the default z-ordinate (if a {@link Feature} have only two dimensions)
	 *
	 * @return the default z-ordinate or {@link Double#NaN}, if the {@link FeatureLayer} doesn't provide default z-ordinates or {@code null}, to use the {@link FeatureService} default
	 *
	 * @since 0.2.0
	 */
	public Double defaultZValue();
	
	/**
	 * allows this {@link FeatureLayer} changes on geometries?
	 *
	 * @return {@code true}, if this {@link FeatureLayer} allows changes on geometries
	 *
	 * @since 0.1.0
	 */
	public boolean allowGeometryUpdates();
}