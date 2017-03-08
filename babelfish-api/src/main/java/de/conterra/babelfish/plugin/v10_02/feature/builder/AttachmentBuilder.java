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
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class AttachmentBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private AttachmentBuilder() {
	}
	
	/**
	 * creates an {@link ObjectValue} with the overview over all {@link Attachment}s of a {@link Feature}
	 *
	 * @param feature the {@link Feature} to get the {@link Attachment}s of
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	public static ObjectValue build(Feature<? extends FeatureObject> feature) {
		ObjectValue result = new ObjectValue();
		
		ArrayValue attas = new ArrayValue();
		for (Attachment atta : feature.getAttachments()) {
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