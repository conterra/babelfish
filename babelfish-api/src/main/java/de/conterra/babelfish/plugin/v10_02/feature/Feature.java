package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

import java.util.Set;

/**
 * defines a {@link FeatureObject} with {@link Attachment}s
 *
 * @param <T> the type of the {@link FeatureObject}
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public interface Feature<T extends FeatureObject> {
	/**
	 * gives the {@link FeatureObject}
	 *
	 * @return the {@link FeatureObject}
	 *
	 * @since 0.1.0
	 */
	public T getFeature();
	
	/**
	 * gives a {@link Set} of all {@link Attachment}s
	 *
	 * @return a {@link Set} of all {@link Attachment}s
	 *
	 * @since 0.1.0
	 */
	public Set<? extends Attachment> getAttachments();
	
	/**
	 * gives the popup with meta information
	 *
	 * @return the popup
	 *
	 * @since 0.1.0
	 */
	public Popup getPopup();
}