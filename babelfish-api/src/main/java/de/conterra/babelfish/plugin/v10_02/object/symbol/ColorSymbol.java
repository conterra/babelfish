package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.*;

/**
 * defines a {@link ColorSymbol}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class ColorSymbol
		extends SymbolObject {
	/**
	 * the {@link Color}
	 *
	 * @since 0.1.0
	 */
	private final Color color;
	
	/**
	 * constructor, with given {@link Color}
	 *
	 * @param color the {@link Color}
	 * @since 0.1.0
	 */
	public ColorSymbol(Color color) {
		this.color = color;
	}
	
	/**
	 * gives the {@link Color}
	 *
	 * @return the {@link Color}
	 *
	 * @since 0.1.0
	 */
	public java.awt.Color getColor() {
		return this.color;
	}
}