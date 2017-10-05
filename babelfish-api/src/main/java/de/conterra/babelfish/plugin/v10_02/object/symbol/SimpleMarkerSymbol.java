package de.conterra.babelfish.plugin.v10_02.object.symbol;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SMSStyle;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * defines a Simple Marker Symbol
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public class SimpleMarkerSymbol
		extends SimpleSymbol {
	/**
	 * the style
	 *
	 * @since 0.1.0
	 */
	@Getter
	private final SMSStyle    style;
	/**
	 * the color
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private       ColorSymbol color;
	/**
	 * the size
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private       int         size;
	/**
	 * the angle
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private double  angle   = 0;
	/**
	 * the offset in x-direction
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private int     xOffset = 0;
	/**
	 * the offset in y-direction
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private int     yOffset = 0;
	/**
	 * the outline
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private Outline outline = null;
	
	/**
	 * constructor, with given style, size and {@link ColorSymbol}
	 *
	 * @param style the style
	 * @param size  the size
	 * @param color the {@link ColorSymbol}
	 * @see SimpleMarkerSymbol#SimpleMarkerSymbol(SMSStyle, int, Color)
	 * @since 0.1.0
	 */
	public SimpleMarkerSymbol(SMSStyle style, int size, ColorSymbol color) {
		this.style = style;
		this.size = size;
		this.color = color;
	}
	
	/**
	 * constructor, with given style, size and {@link Color}
	 *
	 * @param style the style
	 * @param size  the size
	 * @param color the {@link Color}
	 * @see SimpleMarkerSymbol#SimpleMarkerSymbol(SMSStyle, int, ColorSymbol)
	 * @since 0.1.0
	 */
	public SimpleMarkerSymbol(SMSStyle style, int size, Color color) {
		this(style, size, new ColorSymbol(color));
	}
	
	/**
	 * constructor, with given style and {@link ColorSymbol}
	 *
	 * @param style the style
	 * @param color the {@link ColorSymbol}
	 * @see SimpleMarkerSymbol#SimpleMarkerSymbol(SMSStyle, Color)
	 * @since 0.1.0
	 */
	public SimpleMarkerSymbol(SMSStyle style, ColorSymbol color) {
		this(style, 8, color);
	}
	
	/**
	 * constructor, with given style and {@link Color}
	 *
	 * @param style the style
	 * @param color the {@link Color}
	 * @see SimpleMarkerSymbol#SimpleMarkerSymbol(SMSStyle, ColorSymbol)
	 * @since 0.1.0
	 */
	public SimpleMarkerSymbol(SMSStyle style, Color color) {
		this(style, new ColorSymbol(color));
	}
	
	@Override
	public String getType() {
		return super.getType() + "SMS";
	}
}
