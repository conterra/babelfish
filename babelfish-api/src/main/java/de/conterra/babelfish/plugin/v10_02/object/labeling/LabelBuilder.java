package de.conterra.babelfish.plugin.v10_02.object.labeling;

import de.conterra.babelfish.interchange.*;
import de.conterra.babelfish.plugin.v10_02.object.symbol.SymbolBuilder;

/**
 * defines a class, which creates an {@link ObjectValue} of a {@link LabelClass} and an {@link ArrayValue} of a {@link LabelingInfo}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class LabelBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private LabelBuilder() {
	}
	
	/**
	 * builds an {@link ObjectValue} of a given {@link LabelClass}
	 *
	 * @param lblClass the {@link LabelClass} to build the {@link ObjectValue} from
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	public static ObjectValue build(LabelClass lblClass) {
		ObjectValue result = new ObjectValue();
		
		result.addContent("labelPlacement", new StringValue(lblClass.getPlacement().toString()));
		result.addContentNotEmpty("labelExpression", new StringValue(lblClass.getExpression()));
		result.addContentNotEmpty("useCodedValues", new BooleanValue(lblClass.usesCodedValues()));
		result.addContent("symbol", SymbolBuilder.buildNonColor(lblClass.getSymbol()));
		result.addContentNotEmpty("minScale", new NumberValue(lblClass.getMinScale()));
		result.addContentNotEmpty("maxScale", new NumberValue(lblClass.getMaxScale()));
		
		return result;
	}
	
	/**
	 * build an {@link ArrayValue} of a given {@link LabelingInfo}
	 *
	 * @param info the {@link LabelingInfo} to build
	 * @return the created {@link ArrayValue}
	 *
	 * @since 0.1.0
	 */
	public static ArrayValue build(LabelingInfo info) {
		if (info == null)
			return null;
		
		ArrayValue result = new ArrayValue();
		
		for (LabelClass lblClass : info.getClasses())
			result.addValueNotNull(LabelBuilder.build(lblClass));
		
		return result;
	}
}