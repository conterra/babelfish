package de.conterra.babelfish.plugin.v10_02.object.symbol;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SLSStyle;

import java.awt.*;

/**
 * defines Simple Line Symbol as an outline for other {@link SymbolObject}s
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class Outline
		extends SimpleLineSymbol {
	/**
	 * the style of outlines
	 *
	 * @since 0.1.0
	 */
	private static final SLSStyle STYLE = SLSStyle.Solid;
	
	/**
	 * constructor, with given {@link ColorSymbol}
	 *
	 * @param color the {@link ColorSymbol}
	 * @param width the width
	 * @see Outline#Outline(Color, int)
	 * @since 0.1.0
	 */
	public Outline(ColorSymbol color, int width) {
		super(Outline.STYLE, color, width);
	}
	
	/**
	 * constructor, with given {@link Color}
	 *
	 * @param color the {@link Color}
	 * @param width the width
	 * @see Outline#Outline(ColorSymbol, int)
	 * @since 0.1.0
	 */
	public Outline(Color color, int width) {
		super(Outline.STYLE, color, width);
	}
	
	/**
	 * standard constructor, which sets default values<br>
	 * default color: {@link Color#BLACK}, default width: {@code 1}
	 *
	 * @since 0.1.0
	 */
	public Outline() {
		this(Color.BLACK, 1);
	}
}