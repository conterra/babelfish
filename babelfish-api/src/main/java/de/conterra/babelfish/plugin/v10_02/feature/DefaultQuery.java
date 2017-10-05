package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.util.SqlUtils;
import de.conterra.babelfish.util.StringUtils;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * implements a feature {@link Query} request, which is used, if no {@link Query} was specified
 *
 * @param <T> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.3.0
 * @since 0.1.0
 */
public class DefaultQuery<T extends FeatureObject>
		implements Query<T> {
	@Override
	public Iterable<? extends Feature<? extends T>> execute(Iterable<? extends Feature<? extends T>> features, GeometryObject geometry, String whereClause)
	throws QueryParseException {
		Set<Feature<? extends T>> result = new HashSet<>();
		
		Set<Feature<? extends T>> geoResult = new HashSet<>();
		for (Feature<? extends T> feature : features) {
			if (geometry != null) {
				FeatureObject featureObject = feature.getFeature();
				
				if (featureObject instanceof GeometryFeatureObject) {
					GeometryFeatureObject<?> geometryFeature = (GeometryFeatureObject<?>) featureObject;
					
					if (!(geometry.overlaps(geometryFeature.getGeometry()))) {
						continue;
					}
				}
			}
			
			geoResult.add(feature);
		}
		
		double whereNumber;
		try {
			whereNumber = Double.parseDouble(whereClause);
		} catch (NumberFormatException | NullPointerException e) {
			whereNumber = Double.NaN;
		}
		
		if (!(whereClause == null
		      || whereClause.isEmpty()
		      || "1=1".equalsIgnoreCase(whereClause.replaceAll("\\s", StringUtils.EMPTY))
		      || Boolean.parseBoolean(whereClause)
		      || ((!(Double.isNaN(whereNumber))) && whereNumber > 0)
		      || "yes".equalsIgnoreCase(whereClause))) {
			org.josql.Query sqlQuery = new org.josql.Query();
			sqlQuery.parse(SqlUtils.replaceColumn("SELECT * FROM " + FeatureObject.class.getName() + " WHERE " + whereClause, "get"));
			
			for (Feature<? extends T> feature : geoResult) {
				try {
					List<FeatureObject> featureObject = new LinkedList<>();
					featureObject.add(feature.getFeature());
					
					if (sqlQuery.execute(featureObject).getResults().isEmpty()) {
						continue;
					}
				} catch (QueryExecutionException e) {
					throw new QueryParseException(e.getMessage(), e);
				}
				
				result.add(feature);
			}
		} else {
			result.addAll(geoResult);
		}
		
		return result;
	}
}
