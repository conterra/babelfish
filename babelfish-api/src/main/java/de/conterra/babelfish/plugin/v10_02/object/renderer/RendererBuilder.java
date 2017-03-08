package de.conterra.babelfish.plugin.v10_02.object.renderer;

import de.conterra.babelfish.interchange.*;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.object.symbol.SymbolBuilder;

import java.util.LinkedList;
import java.util.List;

/**
 * defines a class, which creates an {@link ObjectValue} from a {@link RendererObject}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class RendererBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private RendererBuilder() {
	}
	
	/**
	 * creates an {@link ObjectValue} from a given {@link RendererObject}
	 *
	 * @param renderer the {@link RendererObject} to build
	 * @return the create {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	public static ObjectValue build(RendererObject renderer) {
		if (renderer == null)
			return new NullValue();
		
		ObjectValue result = new ObjectValue();
		
		result.addContent("type", new StringValue(renderer.getType()));
		
		if (renderer instanceof SimpleRenderer) {
			SimpleRenderer subRenderer = (SimpleRenderer) renderer;
			
			result.addContent("symbol", SymbolBuilder.build(subRenderer.getSymbol()));
			result.addContent("label", new StringValue(subRenderer.getLabel()));
			result.addContentNotEmpty("description", new StringValue(subRenderer.getDescription()));
		} else if (renderer instanceof UniqueValueRenderer) {
			UniqueValueRenderer subRenderer = (UniqueValueRenderer) renderer;
			
			List<? extends Field> fields = new LinkedList<>(subRenderer.getFields());
			for (int i = 0; i < fields.size(); i++)
				result.addContent("field" + (i + 1), new StringValue(fields.get(i).getName()));
			
			result.addContent("fieldDelimiter", new StringValue(subRenderer.getFieldDelimiter()));
			result.addContentNotEmpty("defaultSymbol", SymbolBuilder.build(subRenderer.getDefaultSymbol()));
			result.addContentNotEmpty("defaultLabel", new StringValue(subRenderer.getDefaultLabel()));
			
			ArrayValue values = new ArrayValue();
			for (UniqueValue value : subRenderer.getUniqueValues()) {
				ObjectValue valueObject = new ObjectValue();
				
				valueObject.addContent("value", new StringValue(value.getValue()));
				valueObject.addContent("label", new StringValue(value.getLabel()));
				valueObject.addContentNotEmpty("description", new StringValue(value.getDescription()));
				valueObject.addContent("symbol", SymbolBuilder.build(value.getSymbol()));
				
				values.addValueNotNull(valueObject);
			}
			result.addContent("uniqueValueInfos", values);
		} else if (renderer instanceof ClassBreaksRenderer) {
			ClassBreaksRenderer subRenderer = (ClassBreaksRenderer) renderer;
			
			result.addContent("field", new StringValue(subRenderer.getField()));
			result.addContent("minValue", new NumberValue(subRenderer.getMinValue()));
			
			ArrayValue infos = new ArrayValue();
			for (ClassBreak classBreak : subRenderer.getClassBreaks()) {
				ObjectValue info = new ObjectValue();
				
				info.addContent("classMaxValue", new NumberValue(classBreak.getMaxValue()));
				info.addContent("label", new StringValue(classBreak.getLabel()));
				info.addContentNotEmpty("description", new StringValue(classBreak.getDescription()));
				info.addContent("symbol", SymbolBuilder.build(classBreak.getSymbol()));
				
				infos.addValueNotNull(info);
			}
			result.addContent("classBreakInfos", infos);
		}
		
		return result;
	}
}