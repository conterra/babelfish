package de.conterra.babelfish.plugin.v10_11.feature;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_11.RestService;
import org.opengis.geometry.Envelope;

import java.util.Map;
import java.util.Set;

/**
 * describes an {@link RestService}, which implements a Feature Service of the ArcGIS-REST interface
 *
 * @author ChrissW-R1
 * @version 0.2.0
 * @see <a href="http://resources.arcgis.com/en/help/rest/apiref/featureserver.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public interface FeatureService
		extends de.conterra.babelfish.plugin.v10_02.feature.FeatureService, RestService {
	/**
	 * the official REST API name
	 *
	 * @since 0.1.0
	 */
	public static final String REST_NAME = de.conterra.babelfish.plugin.v10_02.feature.FeatureService.REST_NAME;
	
	/**
	 * are the data in this {@link FeatureService} versioned?
	 *
	 * @return {@code true}, if the {@link FeatureService} supports versioned data
	 *
	 * @see Layer#isDataVersioned()
	 * @since 0.1.0
	 */
	public boolean hasVersionedData();
	
	/**
	 * gives the maximum count of records
	 *
	 * @return the maximum record count
	 *
	 * @since 0.1.0
	 */
	public int getMaxRecordCount();
	
	/**
	 * gives a text with copyright information
	 *
	 * @return a text with copyright information
	 *
	 * @since 0.1.0
	 */
	public String getCopyrightText();
	
	/**
	 * gives the {@link Envelope} show to the user on start
	 *
	 * @return the {@link Envelope} to show on start or {@code null}, to generate automatically an {@link Envelope} from all {@link Feature}s in all {@link Layer}s
	 *
	 * @since 0.2.0
	 */
	public Envelope getInitialExtent();
	
	// ToDo "units": "<units>", //Added at 10.1
	
	/**
	 * gives a {@link Map} with meta data
	 *
	 * @return a {@link Map} with meta data
	 *
	 * @since 0.1.0
	 */
	public Map<? extends String, ? extends String> getDocumentInfo();
	
	@Override
	public Set<? extends Layer<? extends FeatureObject>> getLayers();
	
	@Override
	public Set<? extends FeatureLayer<?, ? extends GeometryFeatureObject<?>>> getFeatureLayers();
	
	@Override
	public Set<? extends Table<? extends FeatureObject>> getTables();
	
	@Override
	public Set<? extends Relationship<? extends FeatureObject, ? extends FeatureObject>> getRelationships();
	
	/**
	 * gives the default z-ordinate (if a {@link Feature} have only two dimensions)
	 *
	 * @return the default z-ordinate or {@link Double#NaN}, if the {@link FeatureService} doesn't provide default z-ordinates
	 *
	 * @since 0.2.0
	 */
	public Double defaultZValue();
}