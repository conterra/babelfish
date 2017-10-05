package de.conterra.babelfish.plugin.v10_02.object.symbol;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.HorizontalAlignment;
import de.conterra.babelfish.plugin.v10_02.object.symbol.style.VerticalAlignment;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * defines a Text Symbol
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public class TextSymbol
		extends MovableSymbol {
	/**
	 * the text color
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private ColorSymbol         textColor       = new ColorSymbol(Color.BLACK);
	/**
	 * the background color
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private ColorSymbol         bgColor         = null;
	/**
	 * the color of the border line
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private ColorSymbol         borderLineColor = null;
	/**
	 * the {@link VerticalAlignment}
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private VerticalAlignment   vAlign          = VerticalAlignment.BASELINE;
	/**
	 * the {@link HorizontalAlignment}
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private HorizontalAlignment hAlign          = HorizontalAlignment.LEFT;
	/**
	 * should the text written from right left?
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private boolean             rightToLeft     = false;
	/**
	 * should a kerning applied to the text?
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private boolean             kerning         = true;
	/**
	 * the {@link Font}
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
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
}
