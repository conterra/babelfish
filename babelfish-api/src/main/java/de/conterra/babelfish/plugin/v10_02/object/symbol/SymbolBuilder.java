package de.conterra.babelfish.plugin.v10_02.object.symbol;

import de.conterra.babelfish.interchange.*;
import de.conterra.babelfish.util.DataUtils;
import de.conterra.babelfish.util.MimeUtils;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

/**
 * defines a class, which creates a {@link Value} of any kind of {@link SymbolObject}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class SymbolBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private SymbolBuilder() {
	}
	
	/**
	 * creates an {@link ArrayValue} of a given {@link ColorSymbol}
	 *
	 * @param symbol the {@link ColorSymbol} to build the {@link ArrayValue} from
	 * @return the created {@link ArrayValue}
	 *
	 * @since 0.1.0
	 */
	public static ArrayValue buildColor(ColorSymbol symbol) {
		ArrayValue result = new ArrayValue();
		
		Color color;
		if (symbol != null) {
			color = symbol.getColor();
		} else {
			color = Color.BLACK;
		}
		
		result.addValue(new NumberValue(color.getRed()));
		result.addValue(new NumberValue(color.getGreen()));
		result.addValue(new NumberValue(color.getBlue()));
		result.addValue(new NumberValue(color.getAlpha()));
		
		return result;
	}
	
	/**
	 * creates an {@link ObjectValue}, only with the attributes of a {@link BaseSymbol}
	 *
	 * @param symbol the {@link BaseSymbol} to build
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	private static ObjectValue buildBase(BaseSymbol symbol) {
		ObjectValue result = new ObjectValue();
		
		result.addContent("type", new StringValue(symbol.getType()));
		result.addContentNotEmpty("color", SymbolBuilder.buildColor(symbol.getColor()));
		
		return result;
	}
	
	/**
	 * creates an {@link ObjectValue}, only with the attributes of a {@link SimpleSymbol}
	 *
	 * @param symbol the {@link SimpleSymbol} to build
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	private static ObjectValue buildSimple(SimpleSymbol symbol) {
		ObjectValue result = SymbolBuilder.buildBase(symbol);
		
		result.addContent("style", new StringValue(symbol.getStyle().toString()), "color", false);
		
		return result;
	}
	
	/**
	 * creates an {@link ObjectValue}, only with the attributes of a {@link MovableSymbol}
	 *
	 * @param symbol the {@link MovableSymbol} to build
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	private static ObjectValue buildMoveable(MovableSymbol symbol) {
		ObjectValue result = SymbolBuilder.buildBase(symbol);
		
		result.addContentNotEmpty("angle", new NumberValue(symbol.getAngle()));
		result.addContentNotEmpty("xoffset", new NumberValue(symbol.getxOffset()));
		result.addContentNotEmpty("yoffset", new NumberValue(symbol.getyOffset()));
		
		return result;
	}
	
	/**
	 * creates an {@link ObjectValue}, only with the attributes of a {@link PictureSymbol}
	 *
	 * @param symbol the {@link PictureSymbol} to build
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	private static ObjectValue buildPicture(PictureSymbol symbol) {
		ObjectValue result = SymbolBuilder.buildMoveable(symbol);
		
		byte[] imgData = DataUtils.toByteArray(symbol.getImage());
		result.addContent("imageData", new StringValue(DataUtils.encodeBase64(imgData)), "color", false);
		result.addContent("contentType", new StringValue(MimeUtils.getType(imgData)), "color", false);
		result.addContent("width", new NumberValue(symbol.getWidth()), "angle", true);
		result.addContent("height", new NumberValue(symbol.getHeight()), "angle", true);
		
		return result;
	}
	
	/**
	 * creates an {@link ObjectValue} of a {@link BaseSymbol}
	 *
	 * @param symbol the {@link BaseSymbol} to build
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	public static ObjectValue buildNonColor(BaseSymbol symbol) {
		ObjectValue result;
		if (symbol instanceof SimpleSymbol) {
			result = SymbolBuilder.buildSimple((SimpleSymbol) symbol);
		} else if (symbol instanceof MovableSymbol) {
			if (symbol instanceof PictureSymbol) {
				result = SymbolBuilder.buildPicture((PictureSymbol) symbol);
			} else {
				result = SymbolBuilder.buildMoveable((MovableSymbol) symbol);
			}
		} else {
			result = SymbolBuilder.buildBase(symbol);
		}
		
		if (symbol instanceof SimpleMarkerSymbol) {
			SimpleMarkerSymbol subSymbol = (SimpleMarkerSymbol) symbol;
			
			result.addContentNotEmpty("size", new NumberValue(subSymbol.getSize()));
			result.addContentNotEmpty("angle", new NumberValue(subSymbol.getAngle()));
			result.addContentNotEmpty("xoffset", new NumberValue(subSymbol.getxOffset()));
			result.addContentNotEmpty("yoffset", new NumberValue(subSymbol.getyOffset()));
			result.addContentNotEmpty("outline", SymbolBuilder.buildNonColor(subSymbol.getOutline()));
		} else if (symbol instanceof SimpleLineSymbol) {
			SimpleLineSymbol subSymbol = (SimpleLineSymbol) symbol;
			
			result.addContent("width", new NumberValue(subSymbol.getWidth()));
		} else if (symbol instanceof SimpleFillSymbol) {
			SimpleFillSymbol subSymbol = (SimpleFillSymbol) symbol;
			
			result.addContentNotEmpty("outline", SymbolBuilder.buildNonColor(subSymbol.getOutline()));
		} else if (symbol instanceof PictureFillSymbol) {
			PictureFillSymbol subSymbol = (PictureFillSymbol) symbol;
			
			result.addContent("outline", SymbolBuilder.buildNonColor(subSymbol.getOutline()), "width", true);
			result.addContentNotEmpty("xscale", new NumberValue(subSymbol.getxScale()));
			result.addContentNotEmpty("yscale", new NumberValue(subSymbol.getyScale()));
		} else if (symbol instanceof TextSymbol) {
			TextSymbol subSymbol = (TextSymbol) symbol;
			
			result.addContent("backgroundColor", SymbolBuilder.buildColor(subSymbol.getBgColor()), "angle", false);
			result.addContent("borderLineColor", SymbolBuilder.buildColor(subSymbol.getBorderLineColor()), "angle", false);
			result.addContent("verticalAlignment", new StringValue(subSymbol.getvAlign().toString()), "angle", false);
			result.addContent("horizontalAlignment", new StringValue(subSymbol.gethAlign().toString()), "angle", false);
			result.addContent("rightToLeft", new BooleanValue(subSymbol.isRightToLeft()), "angle", false);
			result.addContent("kerning", new BooleanValue(subSymbol.isKerning()));
			
			ObjectValue fontValue = new ObjectValue();
			Font        font      = subSymbol.getFont();
			fontValue.addContent("family", new StringValue(font.getFamily()));
			fontValue.addContent("size", new NumberValue(font.getSize()));
			
			Map<? extends TextAttribute, ?> attr = font.getAttributes();
			
			String style;
			if (TextAttribute.POSTURE_OBLIQUE.equals(attr.get(TextAttribute.POSTURE))) {
				style = "oblique";
			} else if (font.isItalic()) {
				style = "italic";
			} else {
				style = "normal";
			}
			fontValue.addContent("style", new StringValue(style));
			
			// ToDo add support of bolder and lighter fonts (not supported natively by Java)
			String weight;
			if (font.isBold()) {
				weight = "bold";
			} else {
				weight = "normal";
			}
			fontValue.addContent("weight", new StringValue(weight));
			
			String deco;
			if (TextAttribute.STRIKETHROUGH_ON.equals(attr.get(TextAttribute.STRIKETHROUGH))) {
				deco = "line-through";
			} else if (!((Integer.valueOf(-1)).equals(attr.get(TextAttribute.UNDERLINE)))) {
				deco = "underline";
			} else {
				deco = "none";
			}
			fontValue.addContent("decoration", new StringValue(deco));
			
			result.addContent("font", fontValue);
		}
		
		return result;
	}
	
	/**
	 * creates an {@link ObjectValue} from any kind of a {@link SymbolObject}
	 *
	 * @param symbol the {@link SymbolObject} to build
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	public static Value build(SymbolObject symbol) {
		if (symbol instanceof BaseSymbol) {
			return SymbolBuilder.buildNonColor((BaseSymbol) symbol);
		} else if (symbol instanceof ColorSymbol) {
			return SymbolBuilder.buildColor((ColorSymbol) symbol);
		} else {
			return new ObjectValue();
		}
	}
}
