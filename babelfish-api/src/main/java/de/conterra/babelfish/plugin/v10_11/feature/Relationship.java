package de.conterra.babelfish.plugin.v10_11.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_11.Cardinality;

/**
 * describes a {@link Relationship} on REST API version 10.11
 * 
 * @version 0.2
 * @author chwe
 * @since 0.1
 * 
 * @param <O> the {@link FeatureObject} type of the origin {@link Layer}
 * @param <D> the {@link FeatureObject} type of the destination {@link Layer}
 * @see <a
 *      href="http://resources.arcgis.com/en/help/rest/apiref/fslayer.html">ArcGIS
 *      REST API</a>
 */
public interface Relationship<O extends FeatureObject, D extends FeatureObject>
extends de.conterra.babelfish.plugin.v10_02.feature.Relationship<O, D>
{
	/**
	 * gives the {@link Cardinality} of the {@link Relationship}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Cardinality}
	 */
	public Cardinality getCardinality();
	
	/**
	 * gives the key field
	 * 
	 * @since 0.1
	 * 
	 * @return the key field
	 */
	public String getKeyField();
	
	/**
	 * if the {@link Relationship} composite?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if the {@link Relationship} is composite
	 */
	public boolean isComposite();
	
	// "relationshipTableId": <attributedRelationshipClassTableId>, //Added in
	// 10.1. Returned only for attributed relationships
	
	// "keyFieldInRelationshipTable":
	// "<key field in AttributedRelationshipClass table that matches keyField>"
	// //Added in 10.1. Returned only for attributed relationships
}