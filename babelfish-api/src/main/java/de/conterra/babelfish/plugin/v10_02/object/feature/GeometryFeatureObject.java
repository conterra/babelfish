package de.conterra.babelfish.plugin.v10_02.object.feature;

import java.util.Map;

import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;

/**
 * defines a georeferenced {@link FeatureObject}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the geometry type
 */
public class GeometryFeatureObject<T extends GeometryObject>
extends FeatureObject
{
	/**
	 * the geometry of this Feature Object
	 * 
	 * @since 0.1
	 */
	private final T geometry;
	
	/**
	 * constructor, with given geometry
	 * 
	 * @since 0.1
	 * 
	 * @param geometry the geometry of this Feature Object
	 */
	public GeometryFeatureObject(T geometry)
	{
		this.geometry = geometry;
	}
	
	/**
	 * constructor, with given attributes
	 * 
	 * @since 0.1
	 * 
	 * @param geometry the geometry of this Feature Object
	 * @param attributes the meta-attributes
	 */
	public GeometryFeatureObject(T geometry, Map<? extends Field, ? extends Object> attributes)
	{
		super(attributes);
		
		this.geometry = geometry;
	}
	
	/**
	 * gives the geometry
	 * 
	 * @since 0.1
	 * 
	 * @return the geometry
	 */
	public T getGeometry()
	{
		return this.geometry;
	}
}