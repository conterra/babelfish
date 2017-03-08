package de.conterra.babelfish.plugin.v10_02.feature.wrapper;

import de.conterra.babelfish.plugin.v10_02.feature.Attachment;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * defines a class
 *
 * @param <T> the feature type
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class FeatureWrapper<T extends FeatureObject> {
	/**
	 * the {@link Feature} to get the information from
	 *
	 * @since 0.1.0
	 */
	private final Feature<? extends T> feature;
	
	/**
	 * constructor, with given {@link Feature}
	 *
	 * @param feature the {@link Feature} to get the additional functions from
	 * @since 0.1.0
	 */
	public FeatureWrapper(Feature<? extends T> feature) {
		this.feature = feature;
	}
	
	/**
	 * gives the {@link Attachment} of an id
	 *
	 * @param id the {@link Attachment} identifier
	 * @return the {@link Attachment} with id {@code id}
	 *
	 * @since 0.1.0
	 */
	public Attachment getAttachment(int id) {
		int i = 0;
		for (Attachment attachment : this.feature.getAttachments()) {
			if (i == id)
				return attachment;
			
			i++;
		}
		
		return null;
	}
}