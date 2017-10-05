package de.conterra.babelfish.plugin.v10_02.object.symbol;

import lombok.Getter;
import lombok.Setter;

/**
 * defines the base version of a {@link SymbolObject}
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public abstract class BaseSymbol
		extends SymbolObject {
	/**
	 * prefix of a symbol type
	 *
	 * @since 0.4.0
	 */
	public static final String TYPE_PREFIX = "esri";
	
	/**
	 * the {@link ColorSymbol}
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private ColorSymbol color = null;
	
	/**
	 * gives the ESRI type
	 *
	 * @return the ESRI type
	 *
	 * @since 0.1.0
	 */
	public String getType() {
		return BaseSymbol.TYPE_PREFIX;
	}
}
