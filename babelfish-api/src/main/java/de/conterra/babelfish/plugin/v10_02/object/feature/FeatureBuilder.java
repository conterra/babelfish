package de.conterra.babelfish.plugin.v10_02.object.feature;

import org.joda.time.DateTime;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import de.conterra.babelfish.interchange.BooleanValue;
import de.conterra.babelfish.interchange.DateValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.interchange.Value;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryBuilder;
import de.conterra.babelfish.util.StringUtils;

/**
 * defines a class, which creates an {@link ObjectValue} from a
 * {@link FeatureObject}
 * 
 * @version 0.2.4
 * @author chwe
 * @since 0.1
 */
public class FeatureBuilder
{
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private FeatureBuilder()
	{
	}
	
	/**
	 * creates a {@link Value} of a given {@link FeatureObject} attribute
	 * 
	 * @since 0.1.1
	 * 
	 * @param fieldValue the attribute value to get the {@link Value} of
	 * @return the generated {@link Value}
	 */
	private static Value createValueFromField(Object fieldValue)
	{
		if (fieldValue == null)
			return null;
		
		if (fieldValue instanceof Number)
			return new NumberValue((Number)fieldValue);
		else if (fieldValue instanceof String)
			return new StringValue((String)fieldValue);
		else if (fieldValue instanceof Boolean)
			return new BooleanValue((Boolean)fieldValue);
		else if (fieldValue instanceof DateTime)
			return new DateValue((DateTime)fieldValue);
		
		return null;
	}
	
	/**
	 * gives a {@link Value} with the {@link FeatureObject} identifier
	 * 
	 * @since 0.1.1
	 * 
	 * @param object the {@link FeatureObject} to get the identifier of
	 * @param objectIdField the field to store the {@link FeatureObject}
	 *        identifier
	 * @return a {@link Value} with the identifier
	 */
	public static Value getObjectId(FeatureObject object, Field objectIdField)
	{
		Value result = FeatureBuilder.createValueFromField(object.getAttribute(objectIdField.getName()));
		
		if (result == null)
		{
			int value = object.hashCode();
			
			if (value == Integer.MIN_VALUE)
				value = 0;
			
			result = new NumberValue(Math.abs(value));
		}
		
		return result;
	}
	
	/**
	 * builds an {@link ObjectValue} from a {@link FeatureObject}
	 * 
	 * @since 0.1
	 * 
	 * @param object the {@link FeatureObject} to build
	 * @param crs the target {@link CoordinateReferenceSystem} or
	 *        <code>null</code>, if the {@link CoordinateReferenceSystem} of the
	 *        given {@link FeatureObject} should be used
	 * @param objectIdField the {@link Field} of the object id or
	 *        <code>null</code>, if the request have no object identifiers
	 * @return the {@link ObjectValue} representation of <code>object</code>
	 */
	public static ObjectValue build(FeatureObject object, CoordinateReferenceSystem crs, Field objectIdField)
	{
		ObjectValue result = new ObjectValue();
		
		if (object instanceof GeometryFeatureObject)
			result.addContent("geometry", GeometryBuilder.build( ((GeometryFeatureObject<?>)object).getGeometry(), crs));
		
		boolean noId = objectIdField != null;
		ObjectValue attrsValue = new ObjectValue();
		for (Field key : object.getAttributes().keySet())
		{
			Object attr = object.getAttribute(key);
			
			if (noId && key.equals(objectIdField))
				noId = false;
			
			attrsValue.addContentNotEmpty(StringUtils.replaceAllNonAlphaNum(key.getName(), "_"), FeatureBuilder.createValueFromField(attr));
		}
		
		if (noId)
			attrsValue.addContentNotEmpty(StringUtils.replaceAllNonAlphaNum(objectIdField.getName(), "_"), FeatureBuilder.getObjectId(object, objectIdField));
		
		result.addContent("attributes", attrsValue);
		
		return result;
	}
}