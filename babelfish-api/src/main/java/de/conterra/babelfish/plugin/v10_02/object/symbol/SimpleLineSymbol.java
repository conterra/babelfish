package de.conterra.babelfish.plugin.v10_02.object.symbol;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SLSStyle;

import java.awt.*;

/**
 * defines a Simple Line Symbol
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class SimpleLineSymbol
		extends SimpleSymbol {
	/**
	 * the style
	 *
	 * @since 0.1.0
	 */
	private final SLSStyle style;
	/**
	 * the color
	 *
	 * @since 0.1.0
	 */
	private ColorSymbol color;
	/**
	 * the width
	 *
	 * @since 0.1.0
	 */
	private int width;
	
	/**
	 * constructor, with given {@link ColorSymbol}
	 *
	 * @param style the style
	 * @param color the {@link ColorSymbol}
	 * @param width the width
	 * @since 0.1.0
	 */
	public SimpleLineSymbol(SLSStyle style, ColorSymbol color, int width) {
		this.style = style;
		this.color = color;
		this.width = width;
	}
	
	/**
	 * constructor, width given {@link Color}
	 *
	 * @param style the style
	 * @param color the color
	 * @param width the width
	 * @since 0.1.0
	 */
	public SimpleLineSymbol(SLSStyle style, Color color, int width) {
		this(style, new ColorSymbol(color), width);
	}
	
	@Override
	public String getType() {
		return super.getType() + "SLS";
	}
	
	/**
	 * gives the color
	 *
	 * @return the color
	 *
	 * @since 0.1.0
	 */
	@Override
	public ColorSymbol getColor() {
		return this.color;
	}
	
	/**
	 * sets the color
	 *
	 * @param color the color to set
	 * @since 0.1.0
	 */
	@Override
	public void setColor(ColorSymbol color) {
		this.color = color;
	}
	
	/**
	 * gives the width
	 *
	 * @return the width
	 *
	 * @since 0.1.0
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * sets the width
	 *
	 * @param width the width to set
	 * @since 0.1.0
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * gives the style
	 *
	 * @return the style
	 *
	 * @since 0.1.0
	 */
	@Override
	public SLSStyle getStyle() {
		return this.style;
	}
}