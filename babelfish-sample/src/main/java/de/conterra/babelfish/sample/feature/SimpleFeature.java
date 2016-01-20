package de.conterra.babelfish.sample.feature;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import de.conterra.babelfish.plugin.v10_02.feature.Attachment;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * defines a very simple {@link Feature}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the type of the {@link FeatureObject}
 */
public class SimpleFeature<T extends FeatureObject>
implements Feature<T>
{
	/**
	 * the {@link FeatureObject}
	 * 
	 * @since 0.1
	 */
	private final T feature;
	/**
	 * the {@link Attachment}s
	 * 
	 * @since 0.1
	 */
	private final Set<Attachment> attachments = new LinkedHashSet<>();
	
	/**
	 * constructor, with given {@link FeatureObject}
	 * 
	 * @since 0.1
	 * 
	 * @param feature the {@link FeatureObject}
	 */
	public SimpleFeature(T feature)
	{
		this.feature = feature;
	}
	
	/**
	 * constructor, with given {@link FeatureObject} an {@link Attachment}s
	 * 
	 * @since 0.1
	 * 
	 * @param feature the {@link FeatureObject}
	 * @param attachments the {@link Attachment}s
	 */
	public SimpleFeature(T feature, Set<? extends Attachment> attachments)
	{
		this(feature);
		
		this.attachments.addAll(attachments);
	}
	
	@Override
	public T getFeature()
	{
		return this.feature;
	}
	
	@Override
	public Set<? extends Attachment> getAttachments()
	{
		return new LinkedHashSet<>(this.attachments);
	}
	
	@Override
	public SimplePopup getPopup()
	{
		T feature = this.getFeature();
		
		Map<? extends Field, ? extends Object> attr = feature.getAttributes();
		Map<String, String> attrString = new LinkedHashMap<>();
		for (Field key : attr.keySet())
			attrString.put(key.getName(), attr.get(key).toString());
		
		return new SimplePopup(feature.toString(), attrString);
	}
}