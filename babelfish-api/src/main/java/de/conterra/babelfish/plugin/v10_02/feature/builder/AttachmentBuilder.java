package de.conterra.babelfish.plugin.v10_02.feature.builder;

import de.conterra.babelfish.interchange.ArrayValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.feature.Attachment;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.util.MimeUtils;

/**
 * defines a builder, which creates an overview of all {@link Attachment}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class AttachmentBuilder
{
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private AttachmentBuilder()
	{
	}
	
	/**
	 * creates an {@link ObjectValue} with the overview over all
	 * {@link Attachment}s of a {@link Feature}
	 * 
	 * @since 0.1
	 * 
	 * @param feature the {@link Feature} to get the {@link Attachment}s of
	 * @return the created {@link ObjectValue}
	 */
	public static ObjectValue build(Feature<? extends FeatureObject> feature)
	{
		ObjectValue result = new ObjectValue();
		
		ArrayValue attas = new ArrayValue();
		for (Attachment atta : feature.getAttachments())
		{
			ObjectValue attaValue = new ObjectValue();
			byte[] data = atta.getData();
			
			attaValue.addContent("id", new NumberValue(atta.getId()));
			attaValue.addContent("contentType", new StringValue(MimeUtils.getType(data)));
			attaValue.addContent("size", new NumberValue(data.length));
			attaValue.addContent("name", new StringValue(atta.getName()));
			
			attas.addValue(attaValue);
		}
		result.addContent("attachmentInfos", attas);
		
		return result;
	}
}