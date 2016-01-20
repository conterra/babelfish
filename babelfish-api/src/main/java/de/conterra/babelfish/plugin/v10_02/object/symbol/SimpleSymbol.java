package de.conterra.babelfish.plugin.v10_02.object.symbol;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SymbolStyle;

/**
 * superclass of simple {@link SymbolObject}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public abstract class SimpleSymbol
extends BaseSymbol
{
	/**
	 * gives the {@link SymbolStyle}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link SymbolStyle}
	 */
	public abstract SymbolStyle getStyle();
}