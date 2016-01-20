package de.conterra.babelfish.plugin.v10_02.feature;

import java.util.Set;

import de.conterra.babelfish.plugin.v10_02.RestService;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;

/**
 * describes a feature service
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/featureserver.html">ArcGIS
 *      REST API</a>
 */
public interface FeatureService
extends RestService
{
	/**
	 * the official REST API name on version 10.02
	 * 
	 * @since 0.1
	 */
	public static final String REST_NAME = "FeatureServer";
	
	/**
	 * gives a {@link Set} of all {@link Layer}s in this {@link FeatureService}
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all {@link Layer}s in this
	 *         {@link FeatureService}
	 */
	public Set<? extends Layer<? extends FeatureObject>> getLayers();
	
	/**
	 * gives a {@link Set} of all {@link Layer}s, which are {@link FeatureLayer}
	 * s in this {@link FeatureService}
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all {@link FeatureLayer}s in this
	 *         {@link FeatureService}
	 */
	public Set<? extends FeatureLayer<?, ? extends GeometryFeatureObject<?>>> getFeatureLayers();
	
	/**
	 * gives a {@link Set} of all {@link Layer}s, which are {@link Table}s in
	 * this {@link FeatureService}
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all {@link Table}s in this
	 *         {@link FeatureService}
	 */
	public Set<? extends Table<? extends FeatureObject>> getTables();
	
	/**
	 * gives a {@link Set} of all {@link Relationship}s in this
	 * {@link FeatureService}
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all {@link Relationship}s in this
	 *         {@link FeatureService}
	 */
	public Set<? extends Relationship<? extends FeatureObject, ? extends FeatureObject>> getRelationships();
}