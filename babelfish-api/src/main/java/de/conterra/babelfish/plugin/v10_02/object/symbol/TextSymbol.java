package de.conterra.babelfish.plugin.v10_02.object.symbol;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.HorizontalAlignment;
import de.conterra.babelfish.plugin.v10_02.object.symbol.style.VerticalAlignment;

import java.awt.*;

/**
 * defines a Text Symbol
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class TextSymbol
		extends MovableSymbol {
	/**
	 * the text color
	 *
	 * @since 0.1.0
	 */
	private ColorSymbol textColor = new ColorSymbol(Color.BLACK);
	/**
	 * the background color
	 *
	 * @since 0.1.0
	 */
	private ColorSymbol bgColor = null;
	/**
	 * the color of the border line
	 *
	 * @since 0.1.0
	 */
	private ColorSymbol borderLineColor = null;
	/**
	 * the {@link VerticalAlignment}
	 *
	 * @since 0.1.0
	 */
	private VerticalAlignment vAlign = VerticalAlignment.BASELINE;
	/**
	 * the {@link HorizontalAlignment}
	 *
	 * @since 0.1.0
	 */
	private HorizontalAlignment hAlign = HorizontalAlignment.LEFT;
	/**
	 * should the text written from right left?
	 *
	 * @since 0.1.0
	 */
	private boolean rightToLeft = false;
	/**
	 * should a kerning applied to the text?
	 *
	 * @since 0.1.0
	 */
	private boolean kerning = true;
	/**
	 * the {@link Font}
	 *
	 * @since 0.1.0
	 */
	private Font font;
	
	/**
	 * constructor, with given {@link Font}
	 *
	 * @param font the {@link Font}
	 * @since 0.1.0
	 */
	public TextSymbol(Font font) {
		this.font = font;
	}
	
	@Override
	public String getType() {
		return super.getType() + "TS";
	}
	
	/**
	 * gives the text color
	 *
	 * @return the text color
	 *
	 * @since 0.1.0
	 */
	public ColorSymbol getTextColor() {
		return this.textColor;
	}
	
	/**
	 * sets the text color
	 *
	 * @param textColor the text color to set
	 * @since 0.1.0
	 */
	public void setTextColor(ColorSymbol textColor) {
		this.textColor = textColor;
	}
	
	/**
	 * gives the background color
	 *
	 * @return the background color
	 *
	 * @since 0.1.0
	 */
	public ColorSymbol getBgColor() {
		return this.bgColor;
	}
	
	/**
	 * sets the background color
	 *
	 * @param bgColor the background color to set
	 * @since 0.1.0
	 */
	public void setBgColor(ColorSymbol bgColor) {
		this.bgColor = bgColor;
	}
	
	/**
	 * gives the color of the border line
	 *
	 * @return the color of the border line
	 *
	 * @since 0.1.0
	 */
	public ColorSymbol getBorderLineColor() {
		return this.borderLineColor;
	}
	
	/**
	 * sets the color of the border line
	 *
	 * @param borderLineColor the color to set
	 * @since 0.1.0
	 */
	public void setBorderLineColor(ColorSymbol borderLineColor) {
		this.borderLineColor = borderLineColor;
	}
	
	/**
	 * gives the {@link VerticalAlignment}
	 *
	 * @return the {@link VerticalAlignment}
	 *
	 * @since 0.1.0
	 */
	public VerticalAlignment getvAlign() {
		return this.vAlign;
	}
	
	/**
	 * sets the {@link VerticalAlignment}
	 *
	 * @param vAlign the {@link VerticalAlignment} to set
	 * @since 0.1.0
	 */
	public void setvAlign(VerticalAlignment vAlign) {
		this.vAlign = vAlign;
	}
	
	/**
	 * gives the {@link HorizontalAlignment}
	 *
	 * @return the {@link HorizontalAlignment}
	 *
	 * @since 0.1.0
	 */
	public HorizontalAlignment gethAlign() {
		return this.hAlign;
	}
	
	/**
	 * sets the {@link HorizontalAlignment}
	 *
	 * @param hAlign the {@link HorizontalAlignment} to set
	 * @since 0.1.0
	 */
	public void sethAlign(HorizontalAlignment hAlign) {
		this.hAlign = hAlign;
	}
	
	/**
	 * should the text written from right left?
	 *
	 * @return {@code true}, if the text should be written from right left
	 *
	 * @since 0.1.0
	 */
	public boolean isRightToLeft() {
		return this.rightToLeft;
	}
	
	/**
	 * sets if the text should be written from right left
	 *
	 * @param rightToLeft {@code true}, if the text should be written from right to left
	 * @since 0.1.0
	 */
	public void setRightToLeft(boolean rightToLeft) {
		this.rightToLeft = rightToLeft;
	}
	
	/**
	 * should a kerning applied to the text?
	 *
	 * @return {@code true}, if a kerning should applied to the text
	 *
	 * @since 0.1.0
	 */
	public boolean isKerning() {
		return this.kerning;
	}
	
	/**
	 * sets if a kerning should applied to the text
	 *
	 * @param kerning {@code true}, if a kerning should applied to the text
	 * @since 0.1.0
	 */
	public void setKerning(boolean kerning) {
		this.kerning = kerning;
	}
	
	/**
	 * gives the font
	 *
	 * @return the font
	 *
	 * @since 0.1.0
	 */
	public Font getFont() {
		return this.font;
	}
	
	/**
	 * sets the {@link Font}
	 *
	 * @param font the {@link Font} to set
	 * @since 0.1.0
	 */
	public void setFont(Font font) {
		this.font = font;
	}
}