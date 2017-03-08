package de.conterra.babelfish.plugin.v10_02.object.symbol;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SFSStyle;

import java.awt.*;

/**
 * defines a Simple Fill Symbol
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class SimpleFillSymbol
		extends SimpleSymbol {
	/**
	 * the style
	 *
	 * @since 0.1.0
	 */
	private final SFSStyle style;
	/**
	 * the color
	 *
	 * @since 0.1.0
	 */
	private ColorSymbol color;
	/**
	 * the outline
	 *
	 * @since 0.1.0
	 */
	private SimpleLineSymbol outline;
	
	/**
	 * constructor, with given {@link ColorSymbol}
	 *
	 * @param style   the style
	 * @param color   the {@link ColorSymbol}
	 * @param outline the outline
	 * @since 0.1.0
	 */
	public SimpleFillSymbol(SFSStyle style, ColorSymbol color, SimpleLineSymbol outline) {
		this.style = style;
		this.color = color;
		this.outline = outline;
	}
	
	/**
	 * constructor, with given {@link Color}
	 *
	 * @param style   the style
	 * @param color   the {@link Color}
	 * @param outline the outline
	 * @since 0.1.0
	 */
	public SimpleFillSymbol(SFSStyle style, Color color, SimpleLineSymbol outline) {
		this(style, new ColorSymbol(color), outline);
	}
	
	@Override
	public String getType() {
		return super.getType() + "SFS";
	}
	
	/**
	 * gives the style
	 *
	 * @return the style
	 *
	 * @since 0.1.0
	 */
	@Override
	public SFSStyle getStyle() {
		return this.style;
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
	 * gives the outline
	 *
	 * @return the outline
	 *
	 * @since 0.1.0
	 */
	public SimpleLineSymbol getOutline() {
		return this.outline;
	}
	
	/**
	 * sets the outline
	 *
	 * @param outline the outline to set
	 * @since 0.1.0
	 */
	public void setOutline(SimpleLineSymbol outline) {
		this.outline = outline;
	}
}