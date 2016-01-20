package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.Color;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SLSStyle;

/**
 * defines Simple Line Symbol as an outline for other {@link SymbolObject}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class Outline
extends SimpleLineSymbol
{
	/**
	 * the style of outlines
	 * 
	 * @since 0.1
	 */
	private static final SLSStyle STYLE = SLSStyle.Solid;
	
	/**
	 * constructor, with given {@link ColorSymbol}
	 * 
	 * @since 0.1
	 * 
	 * @param color the {@link ColorSymbol}
	 * @param width the width
	 * @see Outline#Outline(Color, int)
	 */
	public Outline(ColorSymbol color, int width)
	{
		super(Outline.STYLE, color, width);
	}
	
	/**
	 * constructor, with given {@link Color}
	 * 
	 * @since 0.1
	 * 
	 * @param color the {@link Color}
	 * @param width the width
	 * @see Outline#Outline(ColorSymbol, int)
	 */
	public Outline(Color color, int width)
	{
		super(Outline.STYLE, color, width);
	}
	
	/**
	 * standard constructor, which sets default values<br>
	 * default color: {@link Color#BLACK}, default width: <code>1</code>
	 * 
	 * @since 0.1
	 */
	public Outline()
	{
		this(Color.BLACK, 1);
	}
}