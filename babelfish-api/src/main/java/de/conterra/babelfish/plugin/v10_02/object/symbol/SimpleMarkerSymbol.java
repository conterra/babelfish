package de.conterra.babelfish.plugin.v10_02.object.symbol;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SMSStyle;

import java.awt.*;

/**
 * defines a Simple Marker Symbol
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class SimpleMarkerSymbol
		extends SimpleSymbol {
	/**
	 * the style
	 *
	 * @since 0.1.0
	 */
	private final SMSStyle style;
	/**
	 * the color
	 *
	 * @since 0.1.0
	 */
	private ColorSymbol color;
	/**
	 * the size
	 *
	 * @since 0.1.0
	 */
	private int size;
	/**
	 * the angle
	 *
	 * @since 0.1.0
	 */
	private double angle = 0;
	/**
	 * the offset in x-direction
	 *
	 * @since 0.1.0
	 */
	private int xOffset = 0;
	/**
	 * the offset in y-direction
	 *
	 * @since 0.1.0
	 */
	private int yOffset = 0;
	/**
	 * the outline
	 *
	 * @since 0.1.0
	 */
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
	
	/**
	 * gives the style
	 *
	 * @return the style
	 *
	 * @since 0.1.0
	 */
	@Override
	public SMSStyle getStyle() {
		return this.style;
	}
	
	/**
	 * gives the {@link ColorSymbol}
	 *
	 * @return the {@link ColorSymbol}
	 *
	 * @since 0.1.0
	 */
	@Override
	public ColorSymbol getColor() {
		return this.color;
	}
	
	/**
	 * sets the {@link ColorSymbol}
	 *
	 * @param color the {@link ColorSymbol} to set
	 * @since 0.1.0
	 */
	@Override
	public void setColor(ColorSymbol color) {
		this.color = color;
	}
	
	/**
	 * gives the size
	 *
	 * @return the size
	 *
	 * @since 0.1.0
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * sets the size
	 *
	 * @param size the size to set
	 * @since 0.1.0
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * gives the angle
	 *
	 * @return the angle
	 *
	 * @since 0.1.0
	 */
	public double getAngle() {
		return this.angle;
	}
	
	/**
	 * sets the angle
	 *
	 * @param angle the angle to set
	 * @since 0.1.0
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	/**
	 * gives the offset in x-direction
	 *
	 * @return the offset in x-direction
	 *
	 * @since 0.1.0
	 */
	public int getxOffset() {
		return this.xOffset;
	}
	
	/**
	 * sets the offset in x-direction
	 *
	 * @param xOffset the offset to set
	 * @since 0.1.0
	 */
	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}
	
	/**
	 * gives the offset in y-direction
	 *
	 * @return the offset in y-direction
	 *
	 * @since 0.1.0
	 */
	public int getyOffset() {
		return this.yOffset;
	}
	
	/**
	 * sets the offset in y-direction
	 *
	 * @param yOffset the offset to set
	 * @since 0.1.0
	 */
	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
	
	/**
	 * gives the outline
	 *
	 * @return the outline or {@code null}, this {@link SimpleMarkerSymbol} has no outline
	 *
	 * @since 0.1.0
	 */
	public Outline getOutline() {
		return this.outline;
	}
	
	/**
	 * sets the outline
	 *
	 * @param outline the outline to set or {@code null}, set this {@link SimpleMarkerSymbol} to have no outline
	 * @since 0.1.0
	 */
	public void setOutline(Outline outline) {
		this.outline = outline;
	}
}