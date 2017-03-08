package de.conterra.babelfish.plugin.v10_02.object.symbol;

import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SymbolStyle;

/**
 * superclass of simple {@link SymbolObject}s
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class SimpleSymbol
		extends BaseSymbol {
	/**
	 * gives the {@link SymbolStyle}
	 *
	 * @return the {@link SymbolStyle}
	 *
	 * @since 0.1.0
	 */
	public abstract SymbolStyle getStyle();
}