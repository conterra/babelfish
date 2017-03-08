package de.conterra.babelfish.plugin.v10_02.object.symbol;

/**
 * defines the base version of a {@link SymbolObject}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class BaseSymbol
		extends SymbolObject {
	/**
	 * the {@link ColorSymbol}
	 *
	 * @since 0.1.0
	 */
	private ColorSymbol color = null;
	
	/**
	 * gives the ESRI type
	 *
	 * @return the ESRI type
	 *
	 * @since 0.1.0
	 */
	public String getType() {
		return "esri";
	}
	
	/**
	 * gives the {@link ColorSymbol}
	 *
	 * @return the {@link ColorSymbol}
	 *
	 * @since 0.1.0
	 */
	public ColorSymbol getColor() {
		return this.color;
	}
	
	/**
	 * sets the {@link ColorSymbol}
	 *
	 * @param color the {@link ColorSymbol} to set
	 * @since 0.1.0
	 */
	public void setColor(ColorSymbol color) {
		this.color = color;
	}
}