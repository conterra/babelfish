package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import org.josql.QueryParseException;

import java.util.Collection;

/**
 * describes an interface, which filters a {@link Collection} of {@link Feature}s
 *
 * @param <T> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.3.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fsquery.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public interface Query<T extends FeatureObject> {
	/**
	 * filters all {@link Feature}s by geometric overlapping
	 *
	 * @param features    a {@link Iterable} of the {@link Feature}s to be queried
	 * @param geometry    the {@link GeometryObject} to filter, or {@code null} to disable geometric filtering
	 * @param whereClause the SQL WHERE clause to filter the {@link FeatureObject}s by the meta data, or {@code null} to disable meta filtering
	 * @return all {@link Feature}s, which overlapping the given {@link GeometryObject}
	 *
	 * @throws QueryParseException if a non valid WHERE clause was given
	 * @since 0.1.0
	 */
	public Iterable<? extends Feature<? extends T>> execute(Iterable<? extends Feature<? extends T>> features, GeometryObject geometry, String whereClause)
			throws QueryParseException;
}