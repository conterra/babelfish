package de.conterra.babelfish.plugin.v10_11.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_11.Cardinality;

/**
 * describes a {@link Relationship} on REST API version 10.11
 *
 * @param <O> the {@link FeatureObject} type of the origin {@link Layer}
 * @param <D> the {@link FeatureObject} type of the destination {@link Layer}
 * @author ChrissW-R1
 * @version 0.2.0
 * @see <a href="http://resources.arcgis.com/en/help/rest/apiref/fslayer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public interface Relationship<O extends FeatureObject, D extends FeatureObject>
		extends de.conterra.babelfish.plugin.v10_02.feature.Relationship<O, D> {
	/**
	 * gives the {@link Cardinality} of the {@link Relationship}
	 *
	 * @return the {@link Cardinality}
	 *
	 * @since 0.1.0
	 */
	public Cardinality getCardinality();
	
	/**
	 * gives the key field
	 *
	 * @return the key field
	 *
	 * @since 0.1.0
	 */
	public String getKeyField();
	
	/**
	 * if the {@link Relationship} composite?
	 *
	 * @return {@code true}, if the {@link Relationship} is composite
	 *
	 * @since 0.1.0
	 */
	public boolean isComposite();
	
	// ToDo add relationshipTableId
	// "relationshipTableId": <attributedRelationshipClassTableId>,
	// Added in 10.1. Returned only for attributed relationships
	
	// ToDo add keyFieldInRealtionTable
	// "keyFieldInRelationshipTable": "<key field in AttributedRelationshipClass table that matches keyField>"
	// Added in 10.1. Returned only for attributed relationships
}