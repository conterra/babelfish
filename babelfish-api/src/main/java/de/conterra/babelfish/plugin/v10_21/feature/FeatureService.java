package de.conterra.babelfish.plugin.v10_21.feature;

import de.conterra.babelfish.plugin.v10_21.RestService;

import java.util.Set;

/**
 * describes an {@link RestService}, which implements a Feature Service of the ArcGIS-REST interface
 * 
 * @version 0.3.1
 * @author ChrissW-R1
 * @since 0.3.1
 * 
 * @see <a href="http://resources.arcgis.com/en/help/arcgis-rest-api/index.html#/Feature_Service/02r3000000z2000000/">ArcGIS REST API</a>
 */
public interface FeatureService
extends de.conterra.babelfish.plugin.v10_11.feature.FeatureService, RestService
{
	/**
	 * the official REST API name
	 * 
	 * @since 0.3.1
	 */
	public static final String REST_NAME = de.conterra.babelfish.plugin.v10_11.feature.FeatureService.REST_NAME;
	
	/**
	 * does this {@link FeatureService} contains static data?
	 * 
	 * @since 0.3.1
	 * 
	 * @return {@code true}, if this {@link FeatureService} contains static data
	 */
	public boolean hasStaticData();
	
	/**
	 * is this {@link FeatureService} synchronized?
	 * 
	 * @since 0.3.1
	 * 
	 * @return {@code true}, if this {@link FeatureService} is synchronized
	 */
	public boolean syncEnabled();
	
	/**
	 * gives a {@link Set} of all supported {@link SyncCapability}
	 * 
	 * @since 0.3.1
	 * 
	 * @return a set with all supported {@link SyncCapability}
	 */
	public Set<SyncCapability> getSyncCapabilities();
	
	// ToDo editorTrackingInfo
}