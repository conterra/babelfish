package de.conterra.babelfish.plugin.v10_02.feature;

import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a {@link Layer}, which supports querying based on time
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the {@link FeatureObject} type
 */
public interface TimeLayer<T extends FeatureObject>
extends Layer<T>
{
	/**
	 * gives the field name of the start time
	 * 
	 * @since 0.1
	 * 
	 * @return the field name of the start time
	 */
	public String getStartTimeField();
	
	/**
	 * gives the field name of the end time
	 * 
	 * @since 0.1
	 * 
	 * @return the field name of the end time
	 */
	public String getEndTimeField();
	
	/**
	 * gives the field name of the track id
	 * 
	 * @since 0.1
	 * 
	 * @return the field name of the track id
	 */
	public String getTrackIdField();
	
	/**
	 * gives the start time
	 * 
	 * @since 0.1
	 * 
	 * @return the start time
	 */
	public DateTime getStartTime();
	
	/**
	 * gives the end time
	 * 
	 * @since 0.1
	 * 
	 * @return the end time
	 */
	public DateTime getEndTime();
	
	/**
	 * gives a {@link Map} of all time related {@link Feature}s<br>
	 * <b>Attention</b>: {@link Feature}s in {@link Set} of
	 * {@link Layer#getFeatures()}, will be returned on any time stamp
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Map}, which contains all time related {@link Feature}s
	 *         with its time stamp
	 */
	public Map<? extends Feature<T>, ? extends DateTime> getTimeFeatures();
}