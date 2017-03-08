package de.conterra.babelfish.plugin.v10_02.feature.builder;

import de.conterra.babelfish.interchange.*;
import de.conterra.babelfish.plugin.v10_02.feature.*;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureBuilder;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryBuilder;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.SpatialReference;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.iso.coordinate.EnvelopeImpl;
import org.josql.QueryParseException;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * defines a builder of feature {@link Query} requests
 *
 * @author ChrissW-R1
 * @version 0.2.0
 * @since 0.1.0
 */
public class QueryBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private QueryBuilder() {
	}
	
	/**
	 * creates an {@link ObjectValue}, which contains all features of an executed {@link Query}
	 *
	 * @param <T>         the {@link FeatureObject} type
	 * @param layer       the {@link Layer} to get the header information from
	 * @param crs         the target {@link CoordinateReferenceSystem} or {@code null}, if the {@link CoordinateReferenceSystem} of the given {@link Layer} should be used
	 * @param geometry    the {@link GeometryObject} to be queried or {@code null} to disable geometric filtering
	 * @param whereClause the SQL WHERE clause to filter the {@link FeatureObject}s by the meta data, or {@code null} to disable meta filtering
	 * @param featureIds  a {@link Set} of the {@link Feature}s identifiers to be queried
	 * @param idsOnly     {@code true}, if only the object identifiers should be returned
	 * @param countOnly   {@code true}, if only the count of objects should be returned
	 * @return the {@link ObjectValue}, which contains all {@link Feature}s filtered by {@code query}
	 *
	 * @throws QueryParseException if an {@link Exception} occurred, while execute the {@link Query}
	 * @since 0.2.0
	 */
	public static <T extends FeatureObject> ObjectValue build(Layer<T> layer, CoordinateReferenceSystem crs, GeometryObject geometry, String whereClause, Set<? extends Integer> featureIds, boolean idsOnly, boolean countOnly)
			throws QueryParseException {
		ObjectValue result = new ObjectValue();
		LayerWrapper<T> layerWrapper = new LayerWrapper<>(layer);
		
		Query<T> query = layer.getQuery();
		if (query == null)
			query = new DefaultQuery<T>();
		
		Set<Feature<T>> queryFeatures = new LinkedHashSet<>();
		if (featureIds == null || featureIds.isEmpty())
			queryFeatures.addAll(layer.getFeatures());
		else {
			for (int featureId : featureIds)
				queryFeatures.add(layerWrapper.getFeature(featureId));
		}
		
		Iterable<? extends Feature<? extends T>> queryResult = query.execute(queryFeatures, geometry, whereClause);
		
		if (countOnly) {
			int count = 0;
			for (@SuppressWarnings("unused")
					Feature<? extends T> feature : queryResult)
				count++;
			
			result.addContent("count", new NumberValue(count));
			
			if (!idsOnly)
				return result;
		}
		
		Field objectIdField = layer.getObjectIdField();
		if (objectIdField == null)
			objectIdField = LayerWrapper.DEFAULT_OBJECT_ID_FIELD;
		result.addContent("objectIdFieldName", new StringValue(objectIdField.getName()));
		
		ArrayValue features = new ArrayValue();
		ArrayValue queryIds = new ArrayValue();
		for (Feature<? extends FeatureObject> feature : queryResult) {
			FeatureObject object = feature.getFeature();
			
			queryIds.addValue(FeatureBuilder.getObjectId(object, objectIdField));
			features.addValue(FeatureBuilder.build(object, crs, objectIdField));
		}
		
		if (idsOnly)
			result.addContent("objectIds", queryIds);
		else {
			Field globalIdField = layer.getGlobalIdField();
			Value globalIdValue;
			if (globalIdField != null)
				globalIdValue = new StringValue(globalIdField.getName());
			else
				globalIdValue = new NullValue();
			result.addContent("globalIdFieldName", globalIdValue);
			
			if (layer instanceof FeatureLayer<?, ?>) {
				FeatureLayer<? extends GeometryObject, ? extends GeometryFeatureObject<? extends GeometryObject>> featureLayer = (FeatureLayer<?, ?>) layer;
				
				result.addContent("geometryType", new StringValue(GeometryObject.getType(featureLayer.getGeometryType())));
				
				Envelope envelope = layerWrapper.getEnvelope();
				if (envelope == null) {
					DirectPosition nullPos = new DirectPosition2D(crs);
					envelope = new EnvelopeImpl(nullPos, nullPos);
				}
				
				result.addContent("spatialReference", GeometryBuilder.build(new SpatialReference(envelope.getCoordinateReferenceSystem()), crs));
			}
			
			ArrayValue fields = new ArrayValue();
			for (Field field : layerWrapper.getFields())
				fields.addValue(FieldBuilder.build(field));
			result.addContent("fields", fields);
			
			result.addContent("features", features);
		}
		
		return result;
	}
}