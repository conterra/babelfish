package de.conterra.babelfish.plugin.v10_02.object.domain;

import de.conterra.babelfish.interchange.ArrayValue;
import de.conterra.babelfish.interchange.NullValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.interchange.Value;

/**
 * defines an object builder, which creates an {@link ObjectValue} from a
 * {@link DomainObject}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class DomainBuilder
{
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private DomainBuilder()
	{
	}
	
	/**
	 * creates an {@link ObjectValue} from a {@link DomainObject}
	 * 
	 * @since 0.1
	 * 
	 * @param domain the {@link DomainObject} to build
	 * @return the created {@link ObjectValue}
	 */
	public static ObjectValue build(DomainObject domain)
	{
		if (domain == null)
			return new NullValue();
		
		ObjectValue result = new ObjectValue();
		
		result.addContent("type", new StringValue(domain.getType()));
		
		if (domain instanceof RangeDomain)
		{
			RangeDomain rangeDomain = (RangeDomain)domain;
			
			result.addContentNotEmpty("name", new StringValue(rangeDomain.getName()));
			
			ArrayValue range = new ArrayValue();
			range.addValueNotNull(new NumberValue(rangeDomain.getMin()));
			range.addValueNotNull(new NumberValue(rangeDomain.getMax()));
			result.addContentNotEmpty("range", range);
		}
		else if (domain instanceof CodedValueDomain)
		{
			CodedValueDomain valueDomain = (CodedValueDomain)domain;
			
			result.addContentNotEmpty("name", new StringValue(valueDomain.getName()));
			
			ArrayValue values = new ArrayValue();
			for (String name : valueDomain.getCodedValues().keySet())
			{
				ObjectValue value = new ObjectValue();
				
				Value codeValue;
				Object code = valueDomain.getCode(name);
				if (code instanceof String)
					codeValue = new StringValue((String)code);
				else if (code instanceof Number)
					codeValue = new NumberValue((Number)code);
				else
					codeValue = null;
				
				if (codeValue != null)
				{
					value.addContent("name", new StringValue(name));
					value.addContent("code", codeValue);
					values.addValueNotNull(value);
				}
			}
			result.addContent("codedValues", values);
		}
		
		return result;
	}
}