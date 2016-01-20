package de.conterra.babelfish.plugin.v10_11.feature;

import java.util.Map;
import java.util.Set;

import org.opengis.geometry.Envelope;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_11.RestService;

/**
 * describes an {@link RestService}, which implements a Feature Service of the
 * ArcGIS-REST interface
 * 
 * @version 0.2
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://resources.arcgis.com/en/help/rest/apiref/featureserver.html">ArcGIS
 *      REST API</a>
 */
public interface FeatureService
extends de.conterra.babelfish.plugin.v10_02.feature.FeatureService, RestService
{
	/**
	 * the official REST API name
	 * 
	 * @since 0.1
	 */
	public static final String REST_NAME = de.conterra.babelfish.plugin.v10_02.feature.FeatureService.REST_NAME;
	
	/**
	 * are the data in this {@link FeatureService} versioned?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if the {@link FeatureService} supports
	 *         versioned data
	 * @see Layer#isDataVersioned()
	 */
	public boolean hasVersionedData();
	
	/**
	 * gives the maximum count of records
	 * 
	 * @since 0.1
	 * 
	 * @return the maximum record count
	 */
	public int getMaxRecordCount();
	
	/**
	 * gives a text with copyright information
	 * 
	 * @since 0.1
	 * 
	 * @return a text with copyright information
	 */
	public String getCopyrightText();
	
	/**
	 * gives the {@link Envelope} show to the user on start
	 * 
	 * @since 0.2
	 * 
	 * @return the {@link Envelope} to show on start or <code>null</code>, to
	 *         generate automatically an {@link Envelope} from all
	 *         {@link Feature}s in all {@link Layer}s
	 */
	public Envelope getInitialExtent();
	
	// TODO "units": "<units>", //Added at 10.1
	
	/**
	 * gives a {@link Map} with meta data
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Map} with meta data
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
	 * gives the default z-ordinate (if a {@link Feature} have only two
	 * dimensions)
	 * 
	 * @since 0.2
	 * 
	 * @return the default z-ordinate or {@link Double#NaN}, if the
	 *         {@link FeatureService} doesn't provide default z-ordinates
	 */
	public Double defaultZValue();
}