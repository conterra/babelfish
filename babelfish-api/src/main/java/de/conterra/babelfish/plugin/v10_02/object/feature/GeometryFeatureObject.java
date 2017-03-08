package de.conterra.babelfish.plugin.v10_02.object.feature;

import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;

import java.util.Map;

/**
 * defines a georeferenced {@link FeatureObject}
 *
 * @param <T> the geometry type
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class GeometryFeatureObject<T extends GeometryObject>
		extends FeatureObject {
	/**
	 * the geometry of this Feature Object
	 *
	 * @since 0.1.0
	 */
	private final T geometry;
	
	/**
	 * constructor, with given geometry
	 *
	 * @param geometry the geometry of this Feature Object
	 * @since 0.1.0
	 */
	public GeometryFeatureObject(T geometry) {
		this.geometry = geometry;
	}
	
	/**
	 * constructor, with given attributes
	 *
	 * @param geometry   the geometry of this Feature Object
	 * @param attributes the meta-attributes
	 * @since 0.1.0
	 */
	public GeometryFeatureObject(T geometry, Map<? extends Field, ? extends Object> attributes) {
		super(attributes);
		
		this.geometry = geometry;
	}
	
	/**
	 * gives the geometry
	 *
	 * @return the geometry
	 *
	 * @since 0.1.0
	 */
	public T getGeometry() {
		return this.geometry;
	}
}