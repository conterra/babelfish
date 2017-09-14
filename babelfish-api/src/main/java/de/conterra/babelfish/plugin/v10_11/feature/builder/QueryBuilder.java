package de.conterra.babelfish.plugin.v10_11.feature.builder;

import de.conterra.babelfish.interchange.BooleanValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.Layer;
import de.conterra.babelfish.plugin.v10_02.feature.Query;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import org.josql.QueryParseException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.util.Set;

/**
 * defines a builder of feature {@link Query} requests
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.2.0
 */
public class QueryBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.2.0
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
	 * @since 0.1.0
	 */
	public static <T extends FeatureObject> ObjectValue build(Layer<T> layer, CoordinateReferenceSystem crs, GeometryObject geometry, String whereClause, Set<? extends Long> featureIds, boolean idsOnly, boolean countOnly)
	throws QueryParseException {
		ObjectValue result = de.conterra.babelfish.plugin.v10_02.feature.builder.QueryBuilder.build(layer, crs, geometry, whereClause, featureIds, idsOnly, countOnly);
		
		result.addContent("hasZ", new BooleanValue((new LayerWrapper<>(layer)).getEnvelope().getDimension() >= 3), "fields", false);
		result.addContent("hasM", new BooleanValue(false), "fields", false);
		
		return result;
	}
}
