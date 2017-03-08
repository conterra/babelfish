package de.conterra.babelfish.sample.feature;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.Layer;
import de.conterra.babelfish.plugin.v10_02.feature.Relationship;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.Polygon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * defines a very simple {@link Relationship}
 *
 * @author ChrissW-R1
 * @version 0.1.1
 * @since 0.1.0
 */
public class SimpleRelationship
		implements Relationship<GeometryFeatureObject<Polygon>, FeatureObject> {
	/**
	 * the {@link Logger} of this class
	 *
	 * @since 0.1.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(SimpleRelationship.class);
	
	/**
	 * the unique identifier
	 *
	 * @since 0.1.0
	 */
	private final int id;
	/**
	 * the user-friendly name
	 *
	 * @since 0.1.0
	 */
	private final String name;
	/**
	 * the origin {@link Layer}
	 *
	 * @since 0.1.0
	 */
	private final Layer<GeometryFeatureObject<Polygon>> originLayer;
	/**
	 * the destination {@link Layer}
	 *
	 * @since 0.1.0
	 */
	private final Layer<FeatureObject> destinationLayer;
	
	/**
	 * constructor, with all necessary attributes
	 *
	 * @param id     the unique identifier
	 * @param name   the user-friendly name
	 * @param origin the origin {@link Layer}
	 * @param dest   the destination {@link Layer}
	 * @since 0.1.0
	 */
	public SimpleRelationship(int id, String name, Layer<GeometryFeatureObject<Polygon>> origin, Layer<FeatureObject> dest) {
		this.id = id;
		this.name = name;
		this.originLayer = origin;
		this.destinationLayer = dest;
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public Layer<GeometryFeatureObject<Polygon>> getOriginLayer() {
		return this.originLayer;
	}
	
	@Override
	public Layer<FeatureObject> getDestinationLayer() {
		return this.destinationLayer;
	}
	
	@Override
	public Set<? extends Feature<FeatureObject>> getRelatedFeatures(Feature<? extends GeometryFeatureObject<Polygon>> originFeature) {
		Set<Feature<FeatureObject>> result = new LinkedHashSet<>();
		
		if (originFeature == SimplePolygonLayer.DEILMANN_PARK) {
			SimpleRelationship.LOGGER.debug("Requested related features of the Deilmann Park.");
			
			result.add(SimpleTable.CON_TERRA);
			result.add(SimpleTable.ESRI);
		} else if (originFeature == SimplePolygonLayer.AGENTUR_FUER_ARBEIT) {
			SimpleRelationship.LOGGER.debug("Requested related features of the Agentur f\u00FCr Arbeit.");
			
			result.add(SimpleTable.AGENTUR_FUER_ARBEIT);
		} else
			SimpleRelationship.LOGGER.debug("No linked feature was requested: " + originFeature.getFeature().getAttribute("name"));
		
		return result;
	}
}