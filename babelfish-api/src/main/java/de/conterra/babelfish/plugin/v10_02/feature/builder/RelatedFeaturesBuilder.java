package de.conterra.babelfish.plugin.v10_02.feature.builder;

import de.conterra.babelfish.interchange.ArrayValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.feature.*;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureBuilder;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryBuilder;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.SpatialReference;
import lombok.extern.slf4j.Slf4j;
import org.josql.QueryParseException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * defines a builder of a list of all related {@link Feature}s
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class RelatedFeaturesBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	public RelatedFeaturesBuilder() {
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
	 * @since 0.1.0
	 */
	public static <O extends FeatureObject, D extends FeatureObject> ObjectValue build(Relationship<O, D> relationship, CoordinateReferenceSystem crs, Set<? extends Integer> featureIds, String whereClause) {
		ObjectValue result = new ObjectValue();
		
		Layer<O> originLayer = relationship.getOriginLayer();
		Layer<D> destLayer = relationship.getDestinationLayer();
		FeatureLayer<?, ?> featureLayer = null;
		if (destLayer instanceof FeatureLayer<?, ?>) {
			featureLayer = (FeatureLayer<?, ?>) originLayer;
			
			result.addContent("geometryType", new StringValue(GeometryObject.getType(featureLayer.getGeometryType())));
		}
		
		Set<Feature<O>> features = new LinkedHashSet<>();
		if (featureIds == null || featureIds.isEmpty())
			features.addAll(originLayer.getFeatures());
		else {
			for (int featureId : featureIds) {
				log.debug("Add feature with id: " + featureId);
				
				features.add((new LayerWrapper<O>(originLayer)).getFeature(featureId));
			}
		}
		
		CoordinateReferenceSystem usedCrs = crs;
		Map<Feature<O>, Set<Feature<? extends D>>> relatedFeaturesMap = new HashMap<>();
		for (Feature<O> feature : features) {
			if (feature != null) {
				try {
					Set<Feature<? extends D>> relatedFeatures = new LinkedHashSet<>();
					
					Query<D> query = destLayer.getQuery();
					if (query == null)
						query = new DefaultQuery<>();
					
					log.debug("Execute query to get related features.");
					
					for (Feature<? extends D> destFeature : query.execute(relationship.getRelatedFeatures(feature), null, whereClause)) {
						if (usedCrs == null && destFeature instanceof GeometryFeatureObject<?>)
							usedCrs = ((GeometryFeatureObject<?>) destFeature).getGeometry().getCoordinateReferenceSystem();
						
						relatedFeatures.add(destFeature);
					}
					
					relatedFeaturesMap.put(feature, relatedFeatures);
				} catch (QueryParseException e) {
					log.warn("The given where clause (" + whereClause + ") wasn't valid!", e);
				}
			}
		}
		
		if (usedCrs != null)
			result.addContent("spatialReference", GeometryBuilder.build(new SpatialReference(usedCrs), usedCrs));
		
		ArrayValue relatedRecordGroups = new ArrayValue();
		for (Feature<O> feature : relatedFeaturesMap.keySet()) {
			ObjectValue group = new ObjectValue();
			
			Field objectIdField = originLayer.getObjectIdField();
			if (objectIdField == null)
				objectIdField = LayerWrapper.DEFAULT_OBJECT_ID_FIELD;
			group.addContent("objectId", FeatureBuilder.getObjectId(feature.getFeature(), objectIdField));
			
			ArrayValue relatedRecords = new ArrayValue();
			for (Feature<? extends D> destFeature : relatedFeaturesMap.get(feature))
				relatedRecords.addValue(FeatureBuilder.build(destFeature.getFeature(), usedCrs, destLayer.getObjectIdField()));
			group.addContent("relatedRecords", relatedRecords);
			
			relatedRecordGroups.addValue(group);
		}
		result.addContent("relatedRecordGroups", relatedRecordGroups);
		
		return result;
	}
}