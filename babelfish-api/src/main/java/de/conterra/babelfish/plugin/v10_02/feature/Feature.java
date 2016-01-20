package de.conterra.babelfish.plugin.v10_02.feature;

import java.util.Set;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * defines a {@link FeatureObject} with {@link Attachment}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the type of the {@link FeatureObject}
 */
public interface Feature<T extends FeatureObject>
{
	/**
	 * gives the {@link FeatureObject}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link FeatureObject}
	 */
	public T getFeature();
	
	/**
	 * gives a {@link Set} of all {@link Attachment}s
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all {@link Attachment}s
	 */
	public Set<? extends Attachment> getAttachments();
	
	/**
	 * gives the popup with meta information
	 * 
	 * @since 0.1
	 * 
	 * @return the popup
	 */
	public Popup getPopup();
}