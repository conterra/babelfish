package de.conterra.babelfish.plugin.v10_02.object.domain;

import de.conterra.babelfish.interchange.*;

/**
 * defines an object builder, which creates an {@link ObjectValue} from a {@link DomainObject}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class DomainBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private DomainBuilder() {
	}
	
	/**
	 * creates an {@link ObjectValue} from a {@link DomainObject}
	 *
	 * @param domain the {@link DomainObject} to build
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	public static ObjectValue build(DomainObject domain) {
		if (domain == null)
			return new NullValue();
		
		ObjectValue result = new ObjectValue();
		
		result.addContent("type", new StringValue(domain.getType()));
		
		if (domain instanceof RangeDomain) {
			RangeDomain rangeDomain = (RangeDomain) domain;
			
			result.addContentNotEmpty("name", new StringValue(rangeDomain.getName()));
			
			ArrayValue range = new ArrayValue();
			range.addValueNotNull(new NumberValue(rangeDomain.getMin()));
			range.addValueNotNull(new NumberValue(rangeDomain.getMax()));
			result.addContentNotEmpty("range", range);
		} else if (domain instanceof CodedValueDomain) {
			CodedValueDomain valueDomain = (CodedValueDomain) domain;
			
			result.addContentNotEmpty("name", new StringValue(valueDomain.getName()));
			
			ArrayValue values = new ArrayValue();
			for (String name : valueDomain.getCodedValues().keySet()) {
				ObjectValue value = new ObjectValue();
				
				Value codeValue;
				Object code = valueDomain.getCode(name);
				if (code instanceof String)
					codeValue = new StringValue((String) code);
				else if (code instanceof Number)
					codeValue = new NumberValue((Number) code);
				else
					codeValue = null;
				
				if (codeValue != null) {
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