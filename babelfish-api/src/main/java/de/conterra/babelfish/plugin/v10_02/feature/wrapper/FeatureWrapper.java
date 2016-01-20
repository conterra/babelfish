package de.conterra.babelfish.plugin.v10_02.feature.wrapper;

import de.conterra.babelfish.plugin.v10_02.feature.Attachment;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * defines a class
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the feature type
 */
public class FeatureWrapper<T extends FeatureObject>
{
	/**
	 * the {@link Feature} to get the information from
	 * 
	 * @since 0.1
	 */
	private final Feature<? extends T> feature;
	
	/**
	 * constructor, with given {@link Feature}
	 * 
	 * @since 0.1
	 * 
	 * @param feature the {@link Feature} to get the additional functions from
	 */
	public FeatureWrapper(Feature<? extends T> feature)
	{
		this.feature = feature;
	}
	
	/**
	 * gives the {@link Attachment} of an id
	 * 
	 * @since 0.1
	 * 
	 * @param id the {@link Attachment} identifier
	 * @return the {@link Attachment} with id <code>id</code>
	 */
	public Attachment getAttachment(int id)
	{
		int i = 0;
		for (Attachment attachment : this.feature.getAttachments())
		{
			if (i == id)
				return attachment;
			
			i++;
		}
		
		return null;
	}
}