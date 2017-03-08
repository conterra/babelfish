package de.conterra.babelfish.plugin.v10_02.feature.builder;

import de.conterra.babelfish.interchange.BooleanValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.object.domain.DomainBuilder;
import de.conterra.babelfish.util.StringUtils;

/**
 * defines a class, which creates an {@link ObjectValue} from a {@link Field}
 *
 * @author ChrissW-R1
 * @version 0.2.0
 * @since 0.1.0
 */
public class FieldBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private FieldBuilder() {
	}
	
	/**
	 * creates an {@link ObjectValue} of a {@link Field}
	 *
	 * @param field the {@link Field} to build
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	public static ObjectValue build(Field field) {
		ObjectValue result = new ObjectValue();
		
		result.addContent("name", new StringValue(StringUtils.replaceAllNonAlphaNum(field.getName(), "_")));
		result.addContent("type", new StringValue(field.getType().toString()));
		result.addContentNotEmpty("length", new NumberValue(field.getLength()));
		result.addContent("editable", new BooleanValue(field.isEditable()));
		result.addContent("domain", DomainBuilder.build(field.getDomain()));
		
		if (field instanceof de.conterra.babelfish.plugin.v10_11.feature.Field)
			result.addContent("nullable", new BooleanValue(((de.conterra.babelfish.plugin.v10_11.feature.Field) field).isNullable()), "domain", false);
		
		return result;
	}
}