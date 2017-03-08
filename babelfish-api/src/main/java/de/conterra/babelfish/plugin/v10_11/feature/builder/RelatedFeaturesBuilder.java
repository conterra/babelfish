package de.conterra.babelfish.plugin.v10_11.feature.builder;

import de.conterra.babelfish.interchange.BooleanValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.Layer;
import de.conterra.babelfish.plugin.v10_02.feature.Relationship;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.util.Set;

/**
 * defines a builder of a list of all related {@link Feature}s
 *
 * @author ChrissW-R1
 * @version 0.2.0
 * @since 0.2.0
 */
public class RelatedFeaturesBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.2.0
	 */
	private RelatedFeaturesBuilder() {
	}
	
	/**
	 * creates an {@link ObjectValue}, which contains all related features of a {@link Relationship}
	 *
	 * @param <O>          the {@link FeatureObject} type of the origin {@link Layer}
	 * @param <D>          the {@link FeatureObject} type of the destination {@link Layer}
	 * @param crs          the {@link CoordinateReferenceSystem} to use or {@code null}, if the {@link CoordinateReferenceSystem} of the given {@link Layer} should be used
	 * @param relationship the {@link Relationship} to get the related {@link Feature}s from
	 * @param featureIds   the object identifiers of the {@link Feature}s of the origin {@link Layer} to be queried
	 * @param whereClause  the SQL valid WHERE clause to filter the related {@link Feature}s
	 * @return the {@link ObjectValue}, which contains all {@link Feature}s of the origin {@link Layer} with its related {@link Feature}s
	 *
	 * @see de.conterra.babelfish.plugin.v10_02.feature.builder.RelatedFeaturesBuilder#build(Relationship, CoordinateReferenceSystem, Set, String)
	 * @since 0.1.0
	 */
	public static <O extends FeatureObject, D extends FeatureObject> ObjectValue build(Relationship<O, D> relationship, CoordinateReferenceSystem crs, Set<? extends Integer> featureIds, String whereClause) {
		ObjectValue result = de.conterra.babelfish.plugin.v10_02.feature.builder.RelatedFeaturesBuilder.build(relationship, crs, featureIds, whereClause);
		
		result.addContent("hasZ", new BooleanValue((new LayerWrapper<>(relationship.getDestinationLayer())).getEnvelope().getDimension() >= 3), "fields", false);
		result.addContent("hasM", new BooleanValue(false), "fields", false);
		
		return result;
	}
}