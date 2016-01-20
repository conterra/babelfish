package de.conterra.babelfish.plugin.v10_02.feature.builder;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.feature.FeatureEditTool;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.feature.Template;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureBuilder;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * defines a class, which creates an {@link ObjectValue} from a {@link Template}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class TemplateBuilder
{
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private TemplateBuilder()
	{
	}
	
	/**
	 * creates an {@link ObjectValue} from a given {@link Template}
	 * 
	 * @since 0.1
	 * 
	 * @param template the {@link Template} to build
	 * @param crs the target {@link CoordinateReferenceSystem} or
	 *        <code>null</code>, if the {@link CoordinateReferenceSystem} of the
	 *        prototype should be used
	 * @param objectIdField the {@link Field} within the object identifier or
	 *        <code>null</code>, if no field is available of this usage
	 * @return the created {@link ObjectValue}
	 */
	public static ObjectValue build(Template<? extends FeatureObject> template, CoordinateReferenceSystem crs, Field objectIdField)
	{
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