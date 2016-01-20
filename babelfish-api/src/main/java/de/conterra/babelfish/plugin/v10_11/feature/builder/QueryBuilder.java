package de.conterra.babelfish.plugin.v10_11.feature.builder;

import java.util.Set;

import org.josql.QueryParseException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import de.conterra.babelfish.interchange.BooleanValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.Layer;
import de.conterra.babelfish.plugin.v10_02.feature.Query;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;

/**
 * defines a builder of feature {@link Query} requests
 * 
 * @version 0.2
 * @author chwe
 * @since 0.2
 */
public class QueryBuilder
{
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.2
	 */
	private QueryBuilder()
	{
	}
	
	/**
	 * creates an {@link ObjectValue}, which contains all features of an
	 * executed {@link Query}
	 * 
	 * @since 0.1
	 * 
	 * @param <T> the {@link FeatureObject} type
	 * @param layer the {@link Layer} to get the header information from
	 * @param crs the target {@link CoordinateReferenceSystem} or
	 *        <code>null</code>, if the {@link CoordinateReferenceSystem} of the
	 *        given {@link Layer} should be used
	 * @param geometry the {@link GeometryObject} to be queried or
	 *        <code>null</code> to disable geometric filtering
	 * @param whereClause the SQL WHERE clause to filter the
	 *        {@link FeatureObject}s by the meta data, or <code>null</code> to
	 *        disable meta filtering
	 * @param featureIds a {@link Set} of the {@link Feature}s identifiers to be
	 *        queried
	 * @param idsOnly <code>true</code>, if only the object identifiers should
	 *        be returned
	 * @param countOnly <code>true</code>, if only the count of objects should
	 *        be returned
	 * @return the {@link ObjectValue}, which contains all {@link Feature}s
	 *         filtered by <code>query</code>
	 * @throws QueryParseException if an {@link Exception} occurred, while
	 *         execute the {@link Query}
	 */
	public static <T extends FeatureObject> ObjectValue build(Layer<T> layer, CoordinateReferenceSystem crs, GeometryObject geometry, String whereClause, Set<? extends Integer> featureIds, boolean idsOnly, boolean countOnly)
	throws QueryParseException
	{
		ObjectValue result = de.conterra.babelfish.plugin.v10_02.feature.builder.QueryBuilder.build(layer, crs, geometry, whereClause, featureIds, idsOnly, countOnly);
		
		result.addContent("hasZ", new BooleanValue( (new LayerWrapper<>(layer)).getEnvelope().getDimension() >= 3), "fields", false);
		result.addContent("hasM", new BooleanValue(false), "fields", false);
		
		return result;
	}
}