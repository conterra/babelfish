package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.Set;

/**
 * describes a {@link Layer}, which supports querying based on time
 *
 * @param <T> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public interface TimeLayer<T extends FeatureObject>
		extends Layer<T> {
	/**
	 * gives the field name of the start time
	 *
	 * @return the field name of the start time
	 *
	 * @since 0.1.0
	 */
	public String getStartTimeField();
	
	/**
	 * gives the field name of the end time
	 *
	 * @return the field name of the end time
	 *
	 * @since 0.1.0
	 */
	public String getEndTimeField();
	
	/**
	 * gives the field name of the track id
	 *
	 * @return the field name of the track id
	 *
	 * @since 0.1.0
	 */
	public String getTrackIdField();
	
	/**
	 * gives the start time
	 *
	 * @return the start time
	 *
	 * @since 0.1.0
	 */
	public DateTime getStartTime();
	
	/**
	 * gives the end time
	 *
	 * @return the end time
	 *
	 * @since 0.1.0
	 */
	public DateTime getEndTime();
	
	/**
	 * gives a {@link Map} of all time related {@link Feature}s<br>
	 * <b>Attention</b>: {@link Feature}s in {@link Set} of {@link Layer#getFeatures()}, will be returned on any time stamp
	 *
	 * @return a {@link Map}, which contains all time related {@link Feature}s with its time stamp
	 *
	 * @since 0.1.0
	 */
	public Map<? extends Feature<T>, ? extends DateTime> getTimeFeatures();
}