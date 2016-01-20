package de.conterra.babelfish.plugin.v10_02.feature.builder;

import java.util.Map;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

import de.conterra.babelfish.interchange.ArrayValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.feature.Template;
import de.conterra.babelfish.plugin.v10_02.feature.Type;
import de.conterra.babelfish.plugin.v10_02.object.domain.DomainBuilder;
import de.conterra.babelfish.plugin.v10_02.object.domain.DomainObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * defines a class, which creates an {@link ObjectValue} from a {@link Type}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class TypeBuilder
{
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private TypeBuilder()
	{
	}
	
	/**
	 * creates an {@link ObjectValue} from a given {@link Type}
	 * 
	 * @since 0.1
	 * 
	 * @param <T> the {@link FeatureObject} type
	 * @param type the {@link Type} to build
	 * @param crs the target {@link CoordinateReferenceSystem} or
	 *        <code>null</code>, if the {@link CoordinateReferenceSystem}s of
	 *        the {@link Template}s should be used
	 * @param objectIdField the {@link Field} within the object identifier or
	 *        <code>null</code>, if no field is available of this usage
	 * @return the created {@link ObjectValue}
	 */
	public static <T extends FeatureObject> ObjectValue build(Type<T> type, CoordinateReferenceSystem crs, Field objectIdField)
	{
		ObjectValue result = new ObjectValue();
		
		result.addContent("id", new StringValue(type.getId()));
		result.addContent("name", new StringValue(type.getName()));
		
		ObjectValue domainsObject = new ObjectValue();
		Map<? extends String, ? extends DomainObject> domains = type.getDomains();
		for (String domainField : domains.keySet())
			domainsObject.addContent(domainField, DomainBuilder.build(domains.get(domainField)));
		result.addContent("domains", domainsObject);
		
		ArrayValue templates = new ArrayValue();
		for (Template<? extends T> template : type.getTemplates())
			templates.addValue(TemplateBuilder.build(template, crs, objectIdField));
		result.addContent("templates", templates);
		
		return result;
	}
}