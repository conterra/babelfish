package de.conterra.babelfish.plugin.v10_02.feature.builder;

import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.feature.FeatureEditTool;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.feature.Template;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureBuilder;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * defines a class, which creates an {@link ObjectValue} from a {@link Template}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class TemplateBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private TemplateBuilder() {
	}
	
	/**
	 * creates an {@link ObjectValue} from a given {@link Template}
	 *
	 * @param template      the {@link Template} to build
	 * @param crs           the target {@link CoordinateReferenceSystem} or {@code null}, if the {@link CoordinateReferenceSystem} of the prototype should be used
	 * @param objectIdField the {@link Field} within the object identifier or {@code null}, if no field is available of this usage
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	public static ObjectValue build(Template<? extends FeatureObject> template, CoordinateReferenceSystem crs, Field objectIdField) {
		ObjectValue result = new ObjectValue();
		
		result.addContent("name", new StringValue(template.getName()));
		result.addContent("description", new StringValue(template.getDescription()));
		result.addContent("prototype", FeatureBuilder.build(template.getPrototype(), crs, objectIdField));
		FeatureEditTool tool = template.getFeatureEditTool();
		if (tool != null)
			result.addContentNotEmpty("drawingTool", new StringValue(tool.toString()));
		
		return result;
	}
}